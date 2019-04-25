package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.body.AddFavoriteBody;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;
import com.sadek.orxstradev.smartmall.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter {

    private final String TAG = "ProductPresenter";
    private final Context _Context;
    private final ProductByCatInterface productByCatInterface;

    ApiService apiService;

    public ProductPresenter(Context _Context, ProductByCatInterface productByCatInterface) {
        this._Context = _Context;
        this.productByCatInterface = productByCatInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }

    public void getProduct() {
        String lang= "ar";
        if (Paper.book().contains(Common.language))
            lang = Paper.book().read(Common.language);
        productByCatInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<ProductApiResponse> call = apiInterface.getProductApiResponse(lang);
        call.enqueue(new Callback<ProductApiResponse>() {
            @Override
            public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {
                if (response.isSuccessful()) {
                    ProductApiResponse apiResponse = response.body();
                    productByCatInterface.onProductSuccess(apiResponse);
                }
//                else
//                    productByCatInterface.onFailure(_Context.getString(R.string.error)+"");
                productByCatInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<ProductApiResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
//                productByCatInterface.onFailure(_Context.getString(R.string.error) + " " );
                productByCatInterface.onProgressDialog(false);
            }
        });
    }

    public void getProductFavorite(AddFavoriteBody addFavoriteBody) {
        String lang= "ar";
        if (Paper.book().contains(Common.language))
            lang = Paper.book().read(Common.language);
        productByCatInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<CartModel> call = apiInterface.getFavouriteApiResponse(addFavoriteBody);
        call.enqueue(new Callback<CartModel>() {
            @Override
            public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                if (response.isSuccessful()) {
                    CartModel apiResponse = response.body();
                    productByCatInterface.onProductFavSuccess(apiResponse);
                }
//                else
//                    productByCatInterface.onFailure(_Context.getString(R.string.error)+"");
                productByCatInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<CartModel> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
//                productByCatInterface.onFailure(_Context.getString(R.string.error) + " " );
                productByCatInterface.onProgressDialog(false);
            }
        });
    }
}

