package com.sadek.orxstradev.smartmall.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.Toast;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.ProductSearchActivity;
import com.sadek.orxstradev.smartmall.activites.WebViewActivity;
import com.sadek.orxstradev.smartmall.adapters.CartAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategoryHomeAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategorySubAdapter;
import com.sadek.orxstradev.smartmall.adapters.CheckOutAdapter;
import com.sadek.orxstradev.smartmall.adapters.ExploreOfferAdapter;
import com.sadek.orxstradev.smartmall.adapters.FavoriteAdapter;
import com.sadek.orxstradev.smartmall.adapters.FlashSaleProductAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeAdsAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.adapters.OffersAdapter;
import com.sadek.orxstradev.smartmall.interfaces.AdsInterface;
import com.sadek.orxstradev.smartmall.interfaces.CategoryByParentInterface;
import com.sadek.orxstradev.smartmall.interfaces.CategoryInterface;
import com.sadek.orxstradev.smartmall.interfaces.OfferInterface;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.response.AdsApiResponse;
import com.sadek.orxstradev.smartmall.model.response.AdsRandomApiResponse;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.AdsPresenter;
import com.sadek.orxstradev.smartmall.presenters.CategoryByParentPresenter;
import com.sadek.orxstradev.smartmall.presenters.CategoryPresenter;
import com.sadek.orxstradev.smartmall.presenters.OfferPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.sadek.orxstradev.smartmall.view.CircleIndicator2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends Fragment implements CategoryInterface, OfferInterface, ProductByCatInterface, AdsInterface {

//    List<CategoryApiResponse.DataEntity> categorysList;
//    HomeCategoryAdapter homeCategoryAdapter;

    List<ProductApiResponse.DataEntity> produstsList;
    FlashSaleProductAdapter flashSaleProductAdapter;

    List<CategoryApiResponse.DataEntity> categoryList;
    CategoryHomeAdapter categoryAdapter;
    Unbinder unbinder;
    @BindView(R.id.recycler_category)
    RecyclerView recycler_category;
    @BindView(R.id.home_rcy)
    RecyclerView recycler_home;
    @BindView(R.id.home_product_rcy)
    RecyclerView home_product_rcy;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.searchView)
    View searchView;

    //init the offers Recycler
    @BindView(R.id.imagesRV)
    RecyclerView offersRV;
    //init the loader
//    @BindView(R.id.loading)
//    ProgressBar loading;
    @BindView(R.id.indicator)
    CircleIndicator2 indicator;
    OffersAdapter adapterOffer;
    List<OfferApiResponse.DataEntity> dataOffers;
    List<AdsApiResponse.DataBean> dataAds;
    HomeAdsAdapter homeAdsAdapter;

    CategoryPresenter categoryPresenter;
//    CategoryByParentPresenter categoryByParentPresenter;
    OfferPresenter offerPresenter;
    AdsPresenter adsPresenter;
    ProductPresenter productPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        hideStatusBar();
//        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);

    }

    public void hideStatusBar()
    {
        View decorView = getActivity().getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);


        categoryPresenter = new CategoryPresenter(getContext(), this);
        offerPresenter = new OfferPresenter(getContext(), this);
        adsPresenter = new AdsPresenter(getContext(), this);
//        categoryByParentPresenter = new CategoryByParentPresenter(getContext(), this);
        productPresenter = new ProductPresenter(getContext(), this);
        intUI();
        getData();
        adsPresenter.getAdsRandom();
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
/*
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler_home.setLayoutManager(layoutManager);
        categorysList = new ArrayList<>();
        homeCategoryAdapter = new HomeCategoryAdapter(getContext(), categorysList);
        recycler_home.setAdapter(homeCategoryAdapter);
        */

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler_home.setLayoutManager(layoutManager);
        dataAds = new ArrayList<>();
        homeAdsAdapter = new HomeAdsAdapter(getContext(), dataAds);
        recycler_home.setAdapter(homeAdsAdapter);


        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        //Load menu
        recycler_category.setLayoutManager(mLayoutManager);
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(recycler_category.getContext(),
                R.anim.layout_fall_down);
        recycler_category.setLayoutAnimation(controller);
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryHomeAdapter(getContext(), categoryList);
        recycler_category.setAdapter(categoryAdapter);

        //init the offers
        RecyclerView.LayoutManager
                layoutManager6 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        offersRV.setLayoutManager(layoutManager6);
        dataOffers = new ArrayList<>();
        adapterOffer = new OffersAdapter(dataOffers, getContext());
        offersRV.setAdapter(adapterOffer);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(offersRV);
        indicator.attachToRecyclerView(offersRV, pagerSnapHelper);
        adapterOffer.registerAdapterDataObserver(indicator.getAdapterDataObserver());


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

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serach = new Intent(getContext(), ProductSearchActivity.class);
                startActivity(serach);
            }
        });
    }



    private void getData() {

        swipeRefreshLayout.setRefreshing(true);
        categoryPresenter.getCategory(0);
        offerPresenter.getOffer();
        adsPresenter.getAds();
//        categoryByParentPresenter.getCategory(0);
        productPresenter.getFlashSale();
    }


    @Override
    public void onResume() {
        super.onResume();
        hideStatusBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        unbinder.unbind();
    }

    @Override
    public void onSuccess(CategoryApiResponse categoryApiResponse) {
        categoryList.clear();
        if (categoryList != null)
            categoryList.addAll(categoryApiResponse.getData());
        categoryAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSuccess(OfferApiResponse offerApiResponse) {
        if (offerApiResponse.getData().size() != 0) {
            dataOffers.clear();
            dataOffers.addAll(offerApiResponse.getData());
        }
        adapterOffer.notifyDataSetChanged();

        final int speedScroll = 8000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if (offersRV != null) {
                    if (count > produstsList.size() + 1) {
                        count = 0;
                    }
                    if (count <= produstsList.size() + 1) {
                        offersRV.scrollToPosition(count);
                        count++;
                        handler.postDelayed(this, speedScroll);
                    }
                }
            }
        };
        handler.postDelayed(runnable, speedScroll);


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
    public void onSuccess(AdsApiResponse adsApiResponse) {
        dataAds.clear();
        if (dataAds != null)
            dataAds.addAll(adsApiResponse.getData());
        homeAdsAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSuccess(final AdsRandomApiResponse adsApiResponse) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View viewImg = inflater.inflate(R.layout.image_dilog, null);
        builder.setView(viewImg);
        ImageView imgView = (ImageView) viewImg.findViewById(R.id.imageView);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlIntent = new Intent(getActivity(),WebViewActivity.class);
                urlIntent.putExtra("url",adsApiResponse.getData().get(adsApiResponse.getData().size()-1).getLink());
                startActivity(urlIntent);
            }
        });
        Picasso.with(getContext()).load(Common.BASE_IMAGE_URL+adsApiResponse.getData().get(adsApiResponse.getData().size()-1).getImage()).into(imgView);
        AlertDialog dialog = builder.create();
        dialog.show();

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
