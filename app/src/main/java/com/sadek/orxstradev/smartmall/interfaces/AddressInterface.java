package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.model.response.AddressModel;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;

public interface AddressInterface {

    void onSuccess(AddressModel addressModel);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
