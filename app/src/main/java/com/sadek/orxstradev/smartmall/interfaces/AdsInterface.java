package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.response.AdsApiResponse;
import com.sadek.orxstradev.smartmall.model.response.AdsRandomApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;

public interface AdsInterface {

    void onSuccess(AdsApiResponse adsApiResponse);
    void onSuccess(AdsRandomApiResponse adsApiResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
