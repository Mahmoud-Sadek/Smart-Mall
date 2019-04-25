package com.sadek.orxstradev.smartmall.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.FashionActivity;
import com.sadek.orxstradev.smartmall.adapters.CartAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategoryHomeAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategorySubAdapter;
import com.sadek.orxstradev.smartmall.adapters.CheckOutAdapter;
import com.sadek.orxstradev.smartmall.adapters.ExploreOfferAdapter;
import com.sadek.orxstradev.smartmall.adapters.FavoriteAdapter;
import com.sadek.orxstradev.smartmall.adapters.FlashSaleProductAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.adapters.OffersAdapter;
import com.sadek.orxstradev.smartmall.interfaces.CategoryByParentInterface;
import com.sadek.orxstradev.smartmall.interfaces.CategoryInterface;
import com.sadek.orxstradev.smartmall.interfaces.OfferInterface;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.CategoryByParentPresenter;
import com.sadek.orxstradev.smartmall.presenters.CategoryPresenter;
import com.sadek.orxstradev.smartmall.presenters.OfferPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ExploreFragment extends Fragment implements  CategoryByParentInterface, ProductByCatInterface {


    List<ProductApiResponse.DataEntity> produstsList;
    FlashSaleProductAdapter flashSaleProductAdapter;

    List<CategoryApiResponse.DataEntity> categorysList;
    HomeCategoryAdapter homeCategoryAdapter;
    Unbinder unbinder;

    @BindView(R.id.home_rcy)
    RecyclerView recycler_home;
    @BindView(R.id.home_product_rcy)
    RecyclerView home_product_rcy;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    CategoryByParentPresenter categoryByParentPresenter;
    ProductPresenter productPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_ATTACHED_IN_DECOR);
//        getActivity().getWindow().setStatusBarColor(Color.parseColor("#861a72"));

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_explore, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);


        categoryByParentPresenter = new CategoryByParentPresenter(getContext(), this);
        productPresenter = new ProductPresenter(getContext(), this);
        intUI();
        getData();
    }

    private void intUI() {
        //flash sale produsts
        final RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        //Load menu
        home_product_rcy.setLayoutManager(mLayoutManager1);
        LayoutAnimationController controller1 = AnimationUtils.loadLayoutAnimation(home_product_rcy.getContext(),
                R.anim.layout_fall_down);
        home_product_rcy.setLayoutAnimation(controller1);
        produstsList = new ArrayList<>();
        flashSaleProductAdapter = new FlashSaleProductAdapter(getContext(), produstsList);
        home_product_rcy.setAdapter(flashSaleProductAdapter);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler_home.setLayoutManager(layoutManager);
        categorysList = new ArrayList<>();
        homeCategoryAdapter = new HomeCategoryAdapter(getContext(), categorysList);
        recycler_home.setAdapter(homeCategoryAdapter);




        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_bright);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Common.isConnectedToInternet(getContext())) {
                    getData();
                } else {
                    Toast.makeText(getContext(), "Please check your connection", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                    return;
                }
            }
        });
        swipeRefreshLayout.setRefreshing(false);
    }


    private void getData() {

        swipeRefreshLayout.setRefreshing(true);
        categoryByParentPresenter.getCategory(0);
        productPresenter.getProduct();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        unbinder.unbind();
    }


    @Override
    public void onCategoryByParentSuccess(CategoryApiResponse categoryApiResponse) {
        categorysList.clear();
        if (categorysList != null)
            categorysList.addAll(categoryApiResponse.getData());
        homeCategoryAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onCategoryByParentSuccess(CategoryApiResponse categoryApiResponse, CategorySubAdapter.OrdersVh holder, int position) {

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
    public void onProductByOfferSuccess(ProductApiResponse productApiResponse, ExploreOfferAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductByOfferSuccess(ProductApiResponse productApiResponse, CheckOutAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductSuccess(ProductApiResponse productApiResponse) {
        produstsList.clear();
        if (produstsList != null)
            produstsList.addAll(productApiResponse.getData());
        flashSaleProductAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onProgressDialog(boolean status) {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(status);
    }

    @Override
    public void onFailure(String error) {
        try {
            Toast.makeText(getContext(), error + "", Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }
    }

    @Override
    public void onProductFavSuccess(CartModel apiResponse) {

    }
}