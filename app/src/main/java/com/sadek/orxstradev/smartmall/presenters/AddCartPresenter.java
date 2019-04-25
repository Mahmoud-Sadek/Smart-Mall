package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.AddFavoriteInterface;
import com.sadek.orxstradev.smartmall.model.body.AddCartBody;
import com.sadek.orxstradev.smartmall.model.body.ReviewBody;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCartPresenter {

    private final String TAG = "AddCartPresenter";
    private final Context _Context;
    private final AddFavoriteInterface addFavoriteInterface;

    ApiService apiService;

    public AddCartPresenter(Context _Context, AddFavoriteInterface addFavoriteInterface) {
        this._Context = _Context;
        this.addFavoriteInterface = addFavoriteInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }

    public void addCart(AddCartBody cartBody) {
        addFavoriteInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<DateResponse> call = apiInterface.addCartApiResponse(cartBody,"application/json; charset=utf-8");
        call.enqueue(new Callback<DateResponse>() {
            @Override
            public void onResponse(Call<DateResponse> call, Response<DateResponse> response) {
                if (response.isSuccessful()) {
                    DateResponse apiResponse = response.body();
                    addFavoriteInterface.onSuccess(apiResponse);
                } else
                    addFavoriteInterface.onFailure(_Context.getString(R.string.error)+"");
                addFavoriteInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<DateResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                addFavoriteInterface.onFailure(_Context.getString(R.string.error) + " " );
                addFavoriteInterface.onProgressDialog(false);
            }
        });
    }
}
