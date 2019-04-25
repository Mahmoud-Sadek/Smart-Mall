package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProfileResponse;

public interface ProfileInterface {

    void onSuccess(ProfileResponse profileResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
