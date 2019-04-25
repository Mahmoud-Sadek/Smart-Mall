package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;

public interface OfferInterface {

    void onSuccess(OfferApiResponse offerApiResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
