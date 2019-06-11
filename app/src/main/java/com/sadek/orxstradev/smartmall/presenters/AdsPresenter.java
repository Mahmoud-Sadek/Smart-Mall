package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.interfaces.AdsInterface;
import com.sadek.orxstradev.smartmall.interfaces.OfferInterface;
import com.sadek.orxstradev.smartmall.model.response.AdsApiResponse;
import com.sadek.orxstradev.smartmall.model.response.AdsRandomApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;
import com.sadek.orxstradev.smartmall.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdsPresenter {

    private final String TAG = "OfferPresenter";
    private final Context _Context;
    private final AdsInterface adsInterface;

    ApiService apiService;

    public AdsPresenter(Context _Context, AdsInterface adsInterface) {
        this._Context = _Context;
        this.adsInterface = adsInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }

    public void getAds() {
        String lang= "ar";
        if (Paper.book().contains(Common.language))
            lang = Paper.book().read(Common.language);
        adsInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<AdsApiResponse> call = apiInterface.getAdsApiResponse(lang);
        call.enqueue(new Callback<AdsApiResponse>() {
            @Override
            public void onResponse(Call<AdsApiResponse> call, Response<AdsApiResponse> response) {
                if (response.isSuccessful()) {
                    AdsApiResponse apiResponse = response.body();
                    adsInterface.onSuccess(apiResponse);
                }
//                else
//                    offerInterface.onFailure(_Context.getString(R.string.error)+"");
                adsInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<AdsApiResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
//                offerInterface.onFailure(_Context.getString(R.string.error) + " " );
                adsInterface.onProgressDialog(false);
            }
        });
    }
    public void getAdsRandom() {

        adsInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<AdsRandomApiResponse> call = apiInterface.getAdsRandomsApiResponse();
        call.enqueue(new Callback<AdsRandomApiResponse>() {
            @Override
            public void onResponse(Call<AdsRandomApiResponse> call, Response<AdsRandomApiResponse> response) {
                if (response.isSuccessful()) {
                    AdsRandomApiResponse apiResponse = response.body();
                    adsInterface.onSuccess(apiResponse);
                }
//                else
//                    offerInterface.onFailure(_Context.getString(R.string.error)+"");
                adsInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<AdsRandomApiResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
//                offerInterface.onFailure(_Context.getString(R.string.error) + " " );
                adsInterface.onProgressDialog(false);
            }
        });
    }

}
