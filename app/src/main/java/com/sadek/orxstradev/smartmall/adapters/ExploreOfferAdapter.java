package com.sadek.orxstradev.smartmall.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.ProductByOfferPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ExploreOfferAdapter extends RecyclerView.Adapter<ExploreOfferAdapter.OrdersVh> implements ProductByCatInterface {

    Context context;
    List<OfferApiResponse.DataEntity> offerList;

    public ExploreOfferAdapter(Context context, List<OfferApiResponse.DataEntity> offerList) {
        this.context = context;
        this.offerList = offerList;
    }

    @NonNull
    @Override
    public ExploreOfferAdapter.OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_fragment, parent, false);
        return new ExploreOfferAdapter.OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreOfferAdapter.OrdersVh holder, final int position) {
//        BaseActitvty.animate(holder.itemView);

        holder.category_txt.setText(offerList.get(position).getTitle());
        Picasso.with(context).load(Common.BASE_IMAGE_URL+offerList.get(position).getImage()).error(R.drawable.logo).into(holder.category_sub_img);

        ProductByOfferPresenter productByOfferPresenter = new ProductByOfferPresenter(context, this);
        productByOfferPresenter.getProductOffer(position, holder, offerList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }


    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, HomeCategoryAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, CartAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, FavoriteAdapter.ViewHolder holder, int postion) {

    }

    @Override
    public void onProductByOfferSuccess(ProductApiResponse productApiResponse, OrdersVh holder, int postion) {
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.categoryRCV.setLayoutManager(layoutManager);
        List<ProductApiResponse.DataEntity> productApiResponseList = new ArrayList<>();
        productApiResponseList.addAll(productApiResponse.getData());
        HomeProductAdapter homeProductAdapter = new HomeProductAdapter(context, productApiResponseList, offerList.get(postion));
        holder.categoryRCV.setAdapter(homeProductAdapter);
    }

    @Override
    public void onProductByOfferSuccess(ProductApiResponse productApiResponse, CheckOutAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductSuccess(ProductApiResponse productApiResponse) {

    }

    @Override
    public void onProgressDialog(boolean status) {

    }

    @Override
    public void onFailure(String error) {

    }

    @Override
    public void onProductFavSuccess(CartModel apiResponse) {

    }

    public static class OrdersVh extends RecyclerView.ViewHolder {

        private TextView category_txt;
        private RecyclerView categoryRCV;
        private ImageView category_sub_img;

        public OrdersVh(View itemView) {
            super(itemView);
            category_txt = itemView.findViewById(R.id.category_sub_txt);
            categoryRCV = itemView.findViewById(R.id.category_sub_rcy);
            category_sub_img = itemView.findViewById(R.id.category_sub_img);

        }
    }



}

