package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.response.ReviewsResponse;

public interface ReviewInterface {

    void onSuccess(ReviewsResponse reviewsResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
