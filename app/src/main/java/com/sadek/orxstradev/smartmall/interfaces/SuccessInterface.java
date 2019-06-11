package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.body.SocialLoginBody;
import com.sadek.orxstradev.smartmall.model.response.LoginResponse;
import com.sadek.orxstradev.smartmall.model.response.SuccessResponse;

public interface SuccessInterface {

    void onSuccess(SuccessResponse successResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
