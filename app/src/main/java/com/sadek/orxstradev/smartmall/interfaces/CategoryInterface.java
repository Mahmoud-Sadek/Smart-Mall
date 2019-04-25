package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;

public interface CategoryInterface {

    void onSuccess(CategoryApiResponse categoryApiResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
