package com.sadek.orxstradev.smartmall.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.adapters.CartAdapter;
import com.sadek.orxstradev.smartmall.adapters.CheckOutAdapter;
import com.sadek.orxstradev.smartmall.adapters.ExploreOfferAdapter;
import com.sadek.orxstradev.smartmall.adapters.FavoriteAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.adapters.ProductHomeAdapter;
import com.sadek.orxstradev.smartmall.dialogs.FilterBottomDialogFragment;
import com.sadek.orxstradev.smartmall.interfaces.CartInterface;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.body.AddFavoriteBody;
import com.sadek.orxstradev.smartmall.model.body.CartBody;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.CartPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductByCatPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;


public class FavoriteListActivity extends BaseActitvty implements ProductByCatInterface {


    ArrayList<CartModel.DataEntity> categoryProductList;
    FavoriteAdapter categoryProdctAdapter;

    @BindView(R.id.category_product_rcy)
    RecyclerView category_product_rcy;
    @BindView(R.id.tabTxt)
    TextView tabTxt;
//    @BindView(R.id.linearviewBtn)
//    ImageView verticalView;
//    @BindView(R.id.gridviewBtn)
//    ImageView gridView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    ProductPresenter categoryProductPresenter;
    int categoryId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        ButterKnife.bind(this);
        intUI();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tabTxt.setText(R.string.wishlist);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {

            categoryId = bundle.getInt(Common.CategoryId, 0);
        }
        categoryProductPresenter = new ProductPresenter(this, this);
        getData();
    }

    private void intUI() {
        //products
        GridLayoutManager mLayoutManager2 = new GridLayoutManager(getBaseContext(), 2);
        category_product_rcy.setLayoutManager(mLayoutManager2);
        LayoutAnimationController controller2 = AnimationUtils.loadLayoutAnimation(category_product_rcy.getContext(),
                R.anim.layout_fall_down);
        category_product_rcy.setLayoutAnimation(controller2);
        categoryProductList = new ArrayList<>();
        categoryProdctAdapter = new FavoriteAdapter(getBaseContext(), categoryProductList);
        category_product_rcy.setAdapter(categoryProdctAdapter);


        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_bright);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Common.isConnectedToInternet(getBaseContext())) {
                    getData();
                } else {
                    Toast.makeText(getBaseContext(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                    return;
                }
            }
        });
        swipeRefreshLayout.setRefreshing(false);

    }

    private void getData() {
        swipeRefreshLayout.setRefreshing(true);
        categoryProductPresenter.getProductFavorite(new AddFavoriteBody(Paper.book().read(Common.token)+"",""));
    }

//    @OnClick(R.id.gridviewBtn)
//    void gridView() {
//        gridView.setVisibility(View.GONE);
//        verticalView.setVisibility(View.VISIBLE);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(FavoriteListActivity.this, 2);
//        category_product_rcy.setLayoutManager(layoutManager);
//        categoryProdctAdapter = new FavoriteAdapter(FavoriteListActivity.this, categoryProductList);
//        category_product_rcy.setAdapter(categoryProdctAdapter);
//        //todo
//
//    }

    /*FilterBottomDialogFragment filterBottomDialogFragment;

    @OnClick(R.id.filterViewBtn)
    void filterViewBtn() {

        filterBottomDialogFragment = FilterBottomDialogFragment.newInstance(new FilterBottomDialogFragment.FilterAction() {
            @Override
            public void onGetFilterCode(int code) {
                filterBottomDialogFragment.dismiss();
                getFilterData(code);
            }
        });
        filterBottomDialogFragment.show(getSupportFragmentManager(),
                "filter_dialog_fragment");
    }

    private void getFilterData(int code) {
        if (code == 2) {
            getData();
            categoryProdctAdapter = new FavoriteAdapter(getBaseContext(), categoryProductList);
            category_product_rcy.setAdapter(categoryProdctAdapter);
            categoryProdctAdapter.notifyDataSetChanged();
            return;
        }
*//*
        ArrayList<CartModel.DataEntity> temp1 = new ArrayList<>();
        temp1.addAll(categoryProductList);
        ArrayList<CartModel.DataEntity> temp2 = new ArrayList<>();
        for (int i = 0; i < categoryProductList.size(); i++) {
            int maxPrice = new Integer(temp1.get(0).getPrice());
            int id = 0;
            for (int j = 0; j < temp1.size(); j++) {
                if (maxPrice < new Integer(temp1.get(j).getPrice())) {
                    maxPrice = new Integer(temp1.get(j).getPrice());
                    id = j;
                }
            }
            temp2.add(temp1.get(id));
            temp1.remove(id);
        }
        if (code == 0)
            Collections.reverse(temp2);
        categoryProdctAdapter = new FavoriteAdapter(getBaseContext(), temp2);
        category_product_rcy.setAdapter(categoryProdctAdapter);
        categoryProdctAdapter.notifyDataSetChanged();*//*
    }


    @OnClick(R.id.linearviewBtn)
    void verticalView() {
        gridView.setVisibility(View.VISIBLE);
        verticalView.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(FavoriteListActivity.this, 3);
        category_product_rcy.setLayoutManager(layoutManager);
        categoryProdctAdapter = new FavoriteAdapter(FavoriteListActivity.this, categoryProductList);
        category_product_rcy.setAdapter(categoryProdctAdapter);
        //todo
    }
*/


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

    }

    @Override
    public void onProgressDialog(boolean status) {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(status);
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(getBaseContext(), error + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProductFavSuccess(CartModel apiResponse) {
        categoryProductList.clear();
        if (categoryProductList != null)
            categoryProductList.addAll(apiResponse.getData());
        categoryProdctAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);

        categoryProdctAdapter.notifyDataSetChanged();
    }

}
