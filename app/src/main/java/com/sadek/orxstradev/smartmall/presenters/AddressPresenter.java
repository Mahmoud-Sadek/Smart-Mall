package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.AddFavoriteInterface;
import com.sadek.orxstradev.smartmall.interfaces.AddressInterface;
import com.sadek.orxstradev.smartmall.model.body.AddCartBody;
import com.sadek.orxstradev.smartmall.model.response.AddressModel;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;
import com.sadek.orxstradev.smartmall.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressPresenter {

    private final String TAG = "AddressPresenter";
    private final Context _Context;
    AddressInterface addressInterface;

    ApiService apiService;
    public AddressPresenter(Context _Context, AddressInterface addressInterface) {
        this._Context = _Context;
        this.addressInterface = addressInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }


    public void getAddress(int userId) {
        addressInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<AddressModel> call = apiInterface.getAddressApiResponse(userId);
        call.enqueue(new Callback<AddressModel>() {
            @Override
            public void onResponse(Call<AddressModel> call, Response<AddressModel> response) {
                if (response.isSuccessful()) {
                    AddressModel apiResponse = response.body();
                    addressInterface.onSuccess(apiResponse);
                } else
                    addressInterface.onFailure(_Context.getString(R.string.error)+"");
                addressInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<AddressModel> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                addressInterface.onFailure(_Context.getString(R.string.error) + " " );
                addressInterface.onProgressDialog(false);
            }
        });
    }

    public void addAddress(AddressModel.DataEntity addressModel) {
        addressInterface.onProgressDialog(true);

        String api_token = "Bearer "+Paper.book().read(Common.api_token);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<AddressModel> call = apiInterface.getCreateAddresseApiResponse(addressModel, api_token);
        call.enqueue(new Callback<AddressModel>() {
            @Override
            public void onResponse(Call<AddressModel> call, Response<AddressModel> response) {
                if (response.isSuccessful()) {
                    AddressModel apiResponse = response.body();
                    addressInterface.onSuccess(apiResponse);
                } else
                    addressInterface.onFailure(_Context.getString(R.string.error)+"");
                addressInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<AddressModel> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                addressInterface.onFailure(_Context.getString(R.string.error) + " " );
                addressInterface.onProgressDialog(false);
            }
        });
    }
}
