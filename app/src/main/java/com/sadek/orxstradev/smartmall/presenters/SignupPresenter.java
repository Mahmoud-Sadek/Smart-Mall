package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.LoginInterface;
import com.sadek.orxstradev.smartmall.interfaces.SignupInterface;
import com.sadek.orxstradev.smartmall.model.body.LoginBody;
import com.sadek.orxstradev.smartmall.model.body.SignupBody;
import com.sadek.orxstradev.smartmall.model.response.LoginResponse;
import com.sadek.orxstradev.smartmall.model.response.SignupResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;
import com.sadek.orxstradev.smartmall.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPresenter {

    private final String TAG = "Login";
    private final Context _Context;
    private final SignupInterface signupInterface;

    ApiService apiService;

    public SignupPresenter(Context _Context, SignupInterface signupInterface) {
        this._Context = _Context;
        this.signupInterface = signupInterface;
        apiService = new ApiService();

    }

    public void registerUser(SignupBody signupBody) {

        signupInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<SignupResponse> call = apiInterface.getSignupResponse(signupBody);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) {
                    SignupResponse signupResponse = response.body();
                    signupInterface.onLoginSuccess(signupResponse);
                } else

                    signupInterface.onFailure(_Context.getString(R.string.error)+"");
                signupInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                signupInterface.onFailure(_Context.getString(R.string.error)+" ");
                signupInterface.onProgressDialog(false);
            }
        });
    }

}
