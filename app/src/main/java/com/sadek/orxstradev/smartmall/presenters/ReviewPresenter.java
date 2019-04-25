package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.interfaces.ReviewInterface;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ReviewsResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;
import com.sadek.orxstradev.smartmall.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewPresenter {

    private final String TAG = "ReviewPresenter";
    private final Context _Context;
    private final ReviewInterface reviewInterface;

    ApiService apiService;

    public ReviewPresenter(Context _Context, ReviewInterface reviewInterface) {
        this._Context = _Context;
        this.reviewInterface = reviewInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }

    public void getReview(int parent) {
        String lang= "ar";
        if (Paper.book().contains(Common.language))
            lang = Paper.book().read(Common.language);
        reviewInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<ReviewsResponse> call = apiInterface.getReviewsResponse(parent, lang);
        call.enqueue(new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                if (response.isSuccessful()) {
                    ReviewsResponse apiResponse = response.body();
                    reviewInterface.onSuccess(apiResponse);
                }
//                else
//                    reviewInterface.onFailure(_Context.getString(R.string.error)+"");
                reviewInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<ReviewsResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
//                reviewInterface.onFailure(_Context.getString(R.string.error) + " " );
                reviewInterface.onProgressDialog(false);
            }
        });
    }

}
