package com.sadek.orxstradev.smartmall.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.sadek.orxstradev.smartmall.adapters.FashionCategoryAdapter;
import com.sadek.orxstradev.smartmall.adapters.FavoriteAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeProductAdapter;
import com.sadek.orxstradev.smartmall.adapters.OfferProductAdapter;
import com.sadek.orxstradev.smartmall.adapters.ProductHomeAdapter;
import com.sadek.orxstradev.smartmall.dialogs.FilterBottomDialogFragment;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.Category;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.OfferPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductByOfferPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class OfferActivity extends BaseActitvty implements ProductByCatInterface {

    ArrayList<ProductApiResponse.DataEntity> offerList;
    ProductHomeAdapter offerAdapter;

    @BindView(R.id.category_product_rcy)
    RecyclerView recycler_offer;
    @BindView(R.id.tabTxt)
    TextView tabTxt;
    /*@BindView(R.id.offer_img)
    ImageView offer_img;*/
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    GridLayoutManager mLayoutManager;
    @BindView(R.id.linearviewBtn)
    ImageView verticalView;
    @BindView(R.id.gridviewBtn)
    ImageView gridView;
    @BindView(R.id.searchView)
    View searchView;

    ProductByOfferPresenter productByOfferPresenter;
    int offerId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ButterKnife.bind(this);
        intUI();

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            tabTxt.setText(bundle.getString(Common.OfferName));
//            Picasso.with(getBaseContext()).load(bundle.getString(Common.OfferImage)).error(R.drawable.logo).into(offer_img);
            offerId = bundle.getInt(Common.OfferId, 0);
        }
        productByOfferPresenter = new ProductByOfferPresenter(this,this);
        getData();
    }

    private void intUI() {
        mLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        //Load menu
        recycler_offer.setLayoutManager(mLayoutManager);
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(recycler_offer.getContext(),
                R.anim.layout_fall_down);
        recycler_offer.setLayoutAnimation(controller);
        offerList = new ArrayList<>();
        offerAdapter = new ProductHomeAdapter(getBaseContext(), offerList);
        recycler_offer.setAdapter(offerAdapter);


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
                    Toast.makeText(getBaseContext(), R.string.check_network, Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                    return;
                }
            }
        });
        swipeRefreshLayout.setRefreshing(false);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serach = new Intent(getBaseContext(), ProductSearchActivity.class);
                startActivity(serach);
            }
        });
    }

    private void getData() {

        swipeRefreshLayout.setRefreshing(true);
        productByOfferPresenter.getProductOffer(0, null, offerId);
    }
    @OnClick(R.id.gridviewBtn)
    void gridView() {
        gridView.setVisibility(View.GONE);
        verticalView.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(OfferActivity.this, 2);
        recycler_offer.setLayoutManager(layoutManager);
        offerAdapter = new ProductHomeAdapter(OfferActivity.this, offerList);
        recycler_offer.setAdapter(offerAdapter);
        //todo

    }

    FilterBottomDialogFragment filterBottomDialogFragment;

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
            offerAdapter = new ProductHomeAdapter(getBaseContext(), offerList);
            recycler_offer.setAdapter(offerAdapter);
            offerAdapter.notifyDataSetChanged();
            return;
        }

        ArrayList<ProductApiResponse.DataEntity> temp1 = new ArrayList<>();
        temp1.addAll(offerList);
        ArrayList<ProductApiResponse.DataEntity> temp2 = new ArrayList<>();
        for (int i = 0; i < offerList.size(); i++) {
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
        offerAdapter = new ProductHomeAdapter(getBaseContext(), temp2);
        recycler_offer.setAdapter(offerAdapter);
        offerAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.linearviewBtn)
    void verticalView() {
        gridView.setVisibility(View.VISIBLE);
        verticalView.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(OfferActivity.this, 3);
        recycler_offer.setLayoutManager(layoutManager);
        offerAdapter = new ProductHomeAdapter(OfferActivity.this, offerList);
        recycler_offer.setAdapter(offerAdapter);
        //todo
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
        offerList.clear();
        if (offerList != null)
            offerList.addAll(productApiResponse.getData());
        offerAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
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

    }

}
