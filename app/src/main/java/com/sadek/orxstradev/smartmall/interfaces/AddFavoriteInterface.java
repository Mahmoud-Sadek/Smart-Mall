package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.response.DateResponse;

public interface AddFavoriteInterface {

    void onSuccess(DateResponse dateResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
