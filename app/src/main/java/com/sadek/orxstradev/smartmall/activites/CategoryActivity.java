package com.sadek.orxstradev.smartmall.activites;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.sadek.orxstradev.smartmall.adapters.CategoryOffersAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategoryProductAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategorySubAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategorySubItemAdapter;
import com.sadek.orxstradev.smartmall.adapters.CheckOutAdapter;
import com.sadek.orxstradev.smartmall.adapters.ExploreOfferAdapter;
import com.sadek.orxstradev.smartmall.adapters.FavoriteAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.interfaces.CategoryByParentInterface;
import com.sadek.orxstradev.smartmall.interfaces.HomeOfferInterface;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.CategoryByParentPresenter;
import com.sadek.orxstradev.smartmall.presenters.OfferByCatPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductByCatPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.sadek.orxstradev.smartmall.utils.LocaleUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryActivity extends BaseActitvty implements CategoryByParentInterface, HomeOfferInterface, ProductByCatInterface {

    List<CategoryApiResponse.DataEntity> categorySubList;
    CategorySubItemAdapter categorySubItemAdapter;

    List<OfferApiResponse.DataEntity> categoryOfferList;
    CategoryOffersAdapter categoryOfferAdapter;

    List<ProductApiResponse.DataEntity> categoryProductList;
    CategoryProductAdapter categoryProdctAdapter;

    @BindView(R.id.tabTxt)
    TextView tabTxt;
    @BindView(R.id.category_img)
    ImageView category_img;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.category_sub_rcy)
    RecyclerView category_sub_rcy;
    @BindView(R.id.category_offer_rcy)
    RecyclerView category_offer_rcy;
    @BindView(R.id.category_product_rcy)
    RecyclerView category_product_rcy;

    CategoryByParentPresenter categoryByParentPresenter;
    OfferByCatPresenter categoryOfferPresenter;
    ProductByCatPresenter categoryProductPresenter;

    int categoryId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
//        LocaleUtils.initialize(getBaseContext(), LocaleUtils.ARABIC);
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

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            tabTxt.setText(bundle.getString(Common.CategoryName));
            Picasso.with(getBaseContext()).load(Common.BASE_IMAGE_URL+bundle.getString(Common.CategoryImage)).error(R.drawable.logo).into(category_img);
            categoryId = bundle.getInt(Common.CategoryId, 0);
        }
        categoryByParentPresenter = new CategoryByParentPresenter(this, this);
        categoryOfferPresenter = new OfferByCatPresenter(this, this);
        categoryProductPresenter = new ProductByCatPresenter(this, this);
        getData();
    }

    private void intUI() {
        //subcategories
        GridLayoutManager mLayoutManager = new GridLayoutManager(getBaseContext(), 3);
        category_sub_rcy.setLayoutManager(mLayoutManager);
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(category_sub_rcy.getContext(),
                R.anim.layout_fall_down);
        category_sub_rcy.setLayoutAnimation(controller);
        categorySubList = new ArrayList<>();
        categorySubItemAdapter = new CategorySubItemAdapter(getBaseContext(), categorySubList);
        category_sub_rcy.setAdapter(categorySubItemAdapter);

        //offers
        GridLayoutManager mLayoutManager1 = new GridLayoutManager(getBaseContext(), 2);
        category_offer_rcy.setLayoutManager(mLayoutManager1);
        LayoutAnimationController controller1 = AnimationUtils.loadLayoutAnimation(category_offer_rcy.getContext(),
                R.anim.layout_fall_down);
        category_offer_rcy.setLayoutAnimation(controller1);
        categoryOfferList = new ArrayList<>();
        categoryOfferAdapter = new CategoryOffersAdapter(categoryOfferList, getBaseContext());
        category_offer_rcy.setAdapter(categoryOfferAdapter);

        //products
        GridLayoutManager mLayoutManager2 = new GridLayoutManager(getBaseContext(), 2);
        category_product_rcy.setLayoutManager(mLayoutManager2);
        LayoutAnimationController controller2 = AnimationUtils.loadLayoutAnimation(category_product_rcy.getContext(),
                R.anim.layout_fall_down);
        category_product_rcy.setLayoutAnimation(controller2);
        categoryProductList = new ArrayList<>();
        categoryProdctAdapter = new CategoryProductAdapter(getBaseContext(), categoryProductList);
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
        categoryByParentPresenter.getCategory(categoryId);
        categoryOfferPresenter.getOffer(0, null, categoryId);
        categoryProductPresenter.getProduct(0, null, categoryId);
    }

    @Override
    public void onCategoryByParentSuccess(CategoryApiResponse categoryApiResponse) {
        categorySubList.clear();
        if (categorySubList != null)
            categorySubList.addAll(categoryApiResponse.getData());
        categorySubItemAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onCategoryByParentSuccess(CategoryApiResponse categoryApiResponse, CategorySubAdapter.OrdersVh holder, int position) {

    }

    @Override
    public void onSuccess(OfferApiResponse offerApiResponse, HomeCategoryAdapter.OrdersVh holder, int position) {
        categoryOfferList.clear();
        if (categoryOfferList != null)
            categoryOfferList.addAll(offerApiResponse.getData());
        categoryOfferAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, HomeCategoryAdapter.OrdersVh holder, int postion) {
        categoryProductList.clear();
        if (categoryProductList != null)
            categoryProductList.addAll(productApiResponse.getData());
        categoryProdctAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
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

    }

}
