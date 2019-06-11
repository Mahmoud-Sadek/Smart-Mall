package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;


import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.LoginInterface;
import com.sadek.orxstradev.smartmall.model.body.LoginBody;
import com.sadek.orxstradev.smartmall.model.body.SignupBody;
import com.sadek.orxstradev.smartmall.model.body.SocialLoginBody;
import com.sadek.orxstradev.smartmall.model.response.LoginResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;
import com.sadek.orxstradev.smartmall.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private final String TAG = "Login";
    private final Context _Context;
    private final LoginInterface loginInterface;

    ApiService apiService;

    public LoginPresenter(Context _Context, LoginInterface loginInterface) {
        this._Context = _Context;
        this.loginInterface = loginInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }

    public void loginUser(LoginBody loginBody) {
        String lang= "ar";
        if (Paper.book().contains(Common.language))
            lang = Paper.book().read(Common.language);
        loginInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<LoginResponse> call = apiInterface.getLoginResponse(loginBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    loginInterface.onLoginSuccess(loginResponse);
                } else
                    loginInterface.onFailure(_Context.getString(R.string.error)+"");
                loginInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                loginInterface.onFailure(_Context.getString(R.string.error) + " " );
                loginInterface.onProgressDialog(false);
            }
        });
    }

    public void loginSocial(SignupBody loginBody) {
        String lang= "ar";
        if (Paper.book().contains(Common.language))
            lang = Paper.book().read(Common.language);
        loginInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<SocialLoginBody> call = apiInterface.getLoginSocialResponse(loginBody);
        call.enqueue(new Callback<SocialLoginBody>() {
            @Override
            public void onResponse(Call<SocialLoginBody> call, Response<SocialLoginBody> response) {
                if (response.isSuccessful()) {
                    SocialLoginBody loginResponse = response.body();
                    loginInterface.onLoginSuccess(loginResponse);
                } else
                    loginInterface.onFailure(_Context.getString(R.string.error)+"");
                loginInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<SocialLoginBody> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                loginInterface.onFailure(_Context.getString(R.string.error) + " " );
                loginInterface.onProgressDialog(false);
            }
        });
    }

}
