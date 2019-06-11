package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.AddFavoriteInterface;
import com.sadek.orxstradev.smartmall.interfaces.SuccessInterface;
import com.sadek.orxstradev.smartmall.model.body.ChangeNameBody;
import com.sadek.orxstradev.smartmall.model.body.LoginBody;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.SuccessResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;
import com.sadek.orxstradev.smartmall.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeEmailPresenter {

    private final String TAG = "ChangeEmailPresenter";
    private final Context _Context;
    private final SuccessInterface successInterface;

    ApiService apiService;

    public ChangeEmailPresenter(Context _Context, SuccessInterface successInterface) {
        this._Context = _Context;
        this.successInterface = successInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }

    public void changeEmail(LoginBody loginBody) {
        successInterface.onProgressDialog(true);
        String api_token = "Bearer "+Paper.book().read(Common.api_token);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<SuccessResponse> call = apiInterface.getUpdateEmailApiResponse(loginBody,api_token);
        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                if (response.isSuccessful()) {
                    SuccessResponse apiResponse = response.body();
                    successInterface.onSuccess(apiResponse);
                } else
                    successInterface.onFailure(_Context.getString(R.string.error)+"");
                successInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                successInterface.onFailure(_Context.getString(R.string.error) + " " );
                successInterface.onProgressDialog(false);
            }
        });
    }
}
