package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.adapters.CategorySubAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;

public interface CategoryByParentInterface {

    void onCategoryByParentSuccess(CategoryApiResponse categoryApiResponse);
    void onCategoryByParentSuccess(CategoryApiResponse categoryApiResponse, CategorySubAdapter.OrdersVh holder, int position);
    void onProgressDialog(boolean status);
    void onFailure(String error);

}
