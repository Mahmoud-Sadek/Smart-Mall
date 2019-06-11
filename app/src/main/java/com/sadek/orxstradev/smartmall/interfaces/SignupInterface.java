package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.body.SocialLoginBody;
import com.sadek.orxstradev.smartmall.model.response.SignupResponse;

public interface SignupInterface {

    void onLoginSuccess(SignupResponse signupResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);
    void onLoginSuccess(SocialLoginBody loginResponse);
}
