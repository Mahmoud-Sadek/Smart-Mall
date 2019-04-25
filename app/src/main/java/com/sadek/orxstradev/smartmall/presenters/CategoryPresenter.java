package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.CategoryInterface;
import com.sadek.orxstradev.smartmall.interfaces.LoginInterface;
import com.sadek.orxstradev.smartmall.model.body.LoginBody;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.LoginResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;
import com.sadek.orxstradev.smartmall.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    private final String TAG = "CategoryPresenter";
    private final Context _Context;
    private final CategoryInterface categoryInterface;

    ApiService apiService;

    public CategoryPresenter(Context _Context, CategoryInterface categoryInterface) {
        this._Context = _Context;
        this.categoryInterface = categoryInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }

    public void getCategory(int id) {
        String lang= "ar";
        if (Paper.book().contains(Common.language))
            lang = Paper.book().read(Common.language);
        categoryInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<CategoryApiResponse> call = apiInterface.getCategoryByParIdResponse(lang,id);
        call.enqueue(new Callback<CategoryApiResponse>() {
            @Override
            public void onResponse(Call<CategoryApiResponse> call, Response<CategoryApiResponse> response) {
                if (response.isSuccessful()) {
                    CategoryApiResponse apiResponse = response.body();
                    categoryInterface.onSuccess(apiResponse);
                }
//                else
//                    categoryInterface.onFailure(_Context.getString(R.string.error)+"");
                categoryInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<CategoryApiResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
//                categoryInterface.onFailure(_Context.getString(R.string.error) + " " );
                categoryInterface.onProgressDialog(false);
            }
        });
    }

}
