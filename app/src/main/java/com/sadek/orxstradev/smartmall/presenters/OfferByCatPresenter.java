package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.interfaces.HomeOfferInterface;
import com.sadek.orxstradev.smartmall.interfaces.OfferInterface;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;
import com.sadek.orxstradev.smartmall.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferByCatPresenter {

    private final String TAG = "OfferByCatPresenter";
    private final Context _Context;
    private final HomeOfferInterface offerInterface;

    ApiService apiService;

    public OfferByCatPresenter(Context _Context, HomeOfferInterface offerInterface) {
        this._Context = _Context;
        this.offerInterface = offerInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }

    public void getOffer(final int position, final HomeCategoryAdapter.OrdersVh holder, int id) {
        String lang= "ar";
        if (Paper.book().contains(Common.language))
            lang = Paper.book().read(Common.language);
        offerInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<OfferApiResponse> call = apiInterface.getOfferByCatIdResponse(id,lang);
        call.enqueue(new Callback<OfferApiResponse>() {
            @Override
            public void onResponse(Call<OfferApiResponse> call, Response<OfferApiResponse> response) {
                if (response.isSuccessful()) {
                    OfferApiResponse apiResponse = response.body();
                    offerInterface.onSuccess(apiResponse,holder,position);
                }
//                else
//                    offerInterface.onFailure(_Context.getString(R.string.error)+"");
                offerInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<OfferApiResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
//                offerInterface.onFailure(_Context.getString(R.string.error) + " " );
                offerInterface.onProgressDialog(false);
            }
        });
    }

}
