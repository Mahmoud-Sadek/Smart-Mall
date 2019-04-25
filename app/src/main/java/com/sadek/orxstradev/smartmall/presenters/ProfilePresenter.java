package com.sadek.orxstradev.smartmall.presenters;

import android.content.Context;
import android.util.Log;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.CategoryInterface;
import com.sadek.orxstradev.smartmall.interfaces.ProfileInterface;
import com.sadek.orxstradev.smartmall.model.body.ProfileBody;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProfileResponse;
import com.sadek.orxstradev.smartmall.service.ApiService;
import com.sadek.orxstradev.smartmall.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {

    private final String TAG = "ProfilePresenter";
    private final Context _Context;
    private final ProfileInterface profileInterface;

    ApiService apiService;

    public ProfilePresenter(Context _Context, ProfileInterface profileInterface) {
        this._Context = _Context;
        this.profileInterface = profileInterface;

        Paper.init(_Context);
        apiService = new ApiService();

    }

    public void getProfile(ProfileBody profileBody) {
        String lang= "ar";
        if (Paper.book().contains(Common.language))
            lang = Paper.book().read(Common.language);
        profileInterface.onProgressDialog(true);
        ApiService.ApiInterface apiInterface = apiService.getClient().create(ApiService.ApiInterface.class);
        Call<ProfileResponse> call = apiInterface.getProfileApiResponse(profileBody);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {
                    ProfileResponse apiResponse = response.body();
                    profileInterface.onSuccess(apiResponse);
                }
//                else
//                    profileInterface.onFailure(_Context.getString(R.string.error)+"");
                profileInterface.onProgressDialog(false);
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
//                profileInterface.onFailure(_Context.getString(R.string.error) + " " );
                profileInterface.onProgressDialog(false);
            }
        });
    }

}
