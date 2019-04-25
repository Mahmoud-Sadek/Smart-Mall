package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.ReviewsResponse;

public interface CartInterface {

    void onSuccess(CartModel cartModel);
    void onSuccess(DateResponse dateResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
