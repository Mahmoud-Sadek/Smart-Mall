package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;

public interface HomeOfferInterface {

    void onSuccess(OfferApiResponse offerApiResponse, HomeCategoryAdapter.OrdersVh holder, int position);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
