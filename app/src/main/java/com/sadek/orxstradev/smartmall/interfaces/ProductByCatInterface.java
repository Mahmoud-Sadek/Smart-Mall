package com.sadek.orxstradev.smartmall.interfaces;

import com.sadek.orxstradev.smartmall.activites.CheckOutActivity;
import com.sadek.orxstradev.smartmall.adapters.CartAdapter;
import com.sadek.orxstradev.smartmall.adapters.CheckOutAdapter;
import com.sadek.orxstradev.smartmall.adapters.ExploreOfferAdapter;
import com.sadek.orxstradev.smartmall.adapters.FavoriteAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;

public interface ProductByCatInterface {

    void onProductByCatSuccess(ProductApiResponse productApiResponse, HomeCategoryAdapter.OrdersVh holder, int postion);
    void onProductByCatSuccess(ProductApiResponse productApiResponse, CartAdapter.OrdersVh holder, int postion);
    void onProductByCatSuccess(ProductApiResponse productApiResponse, FavoriteAdapter.ViewHolder holder, int postion);
    void onProductByOfferSuccess(ProductApiResponse productApiResponse, ExploreOfferAdapter.OrdersVh holder, int postion);
    void onProductByOfferSuccess(ProductApiResponse productApiResponse, CheckOutAdapter.OrdersVh holder, int postion);
    void onProductSuccess(ProductApiResponse productApiResponse);
    void onProgressDialog(boolean status);
    void onFailure(String error);

    void onProductFavSuccess(CartModel apiResponse);

}
