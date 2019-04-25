package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.CartInterface;
import com.sadek.orxstradev.smartmall.interfaces.ReviewInterface;
import com.sadek.orxstradev.smartmall.model.body.AddFavoriteBody;
import com.sadek.orxstradev.smartmall.model.body.CartBody;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.ReviewsResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter {

    private final String TAG = "CartPresenter";
    private final Context _Context;
    private final CartInterface cartInterface;

    ApiService apiService;

    public CartPresenter(Context _Context, CartInterface cartInterface) {
        this._Context = _Context;
        this.cartInterface = cartInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }

    public void getCart(CartBody cartBody) {
        cartInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<CartModel> call = apiInterface.getCartResponse(cartBody);
        call.enqueue(new Callback<CartModel>() {
            @Override
            public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                if (response.isSuccessful()) {
                    CartModel apiResponse = response.body();
                    cartInterface.onSuccess(apiResponse);
                }
//                else
//                    cartInterface.onFailure(_Context.getString(R.string.error)+"");
                cartInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<CartModel> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
//                cartInterface.onFailure(_Context.getString(R.string.error) + " " );
                cartInterface.onProgressDialog(false);
            }
        });
    }

    public void deleteCart(AddFavoriteBody cartBody) {
        cartInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<DateResponse> call = apiInterface.DeleteCartApiResponse(cartBody);
        call.enqueue(new Callback<DateResponse>() {
            @Override
            public void onResponse(Call<DateResponse> call, Response<DateResponse> response) {
                if (response.isSuccessful()) {
                    DateResponse apiResponse = response.body();
                    cartInterface.onSuccess(apiResponse);
                } else
                    cartInterface.onFailure(_Context.getString(R.string.error)+"");
                cartInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<DateResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                cartInterface.onFailure(_Context.getString(R.string.error) + " " );
                cartInterface.onProgressDialog(false);
            }
        });
    }

}
