package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.body.SocialLoginBody;
import com.sadek.orxstradev.smartmall.model.response.LoginResponse;

public interface LoginInterface {

    void onLoginSuccess(LoginResponse loginResponse);
    void onLoginSuccess(SocialLoginBody loginResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
