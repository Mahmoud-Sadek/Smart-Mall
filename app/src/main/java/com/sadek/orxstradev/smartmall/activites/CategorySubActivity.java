package com.sadek.orxstradev.smartmall.activites;

import android.content.Intent;
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
import com.sadek.orxstradev.smartmall.adapters.CategoryOffersAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategoryProductAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategorySubAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategorySubItemAdapter;
import com.sadek.orxstradev.smartmall.adapters.ExploreOfferAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.interfaces.CategoryByParentInterface;
import com.sadek.orxstradev.smartmall.interfaces.HomeOfferInterface;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.CategoryByParentPresenter;
import com.sadek.orxstradev.smartmall.presenters.OfferByCatPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductByCatPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategorySubActivity extends BaseActitvty implements CategoryByParentInterface {

    List<CategoryApiResponse.DataEntity> categorySubList;
    CategorySubItemAdapter categorySubItemAdapter;



    @BindView(R.id.tabTxt)
    TextView tabTxt;
    @BindView(R.id.category_see_all_)
    View category_see_all_;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.category_sub_rcy)
    RecyclerView category_sub_rcy;


    CategoryByParentPresenter categoryByParentPresenter;


    int categoryId = 0;
    String categoryName ="";
    String categoryImage = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_sub);
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
            categoryName = bundle.getString(Common.CategoryName);
            tabTxt.setText(bundle.getString(Common.CategoryName));
            categoryImage = bundle.getString(Common.CategoryImage);
            categoryId = bundle.getInt(Common.CategoryId, 0);
        }
        categoryByParentPresenter = new CategoryByParentPresenter(this, this);
        getData();

        category_see_all_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(Common.CategoryId, categoryId);
                bundle.putString(Common.CategoryImage,categoryImage );
                bundle.putString(Common.CategoryName, categoryName);
                Intent intent = new Intent(getBaseContext(), ProductListActivity.class);
                // we will send food id to food detail class
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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
    public void onProgressDialog(boolean status) {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(status);
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(getBaseContext(), error + "", Toast.LENGTH_SHORT).show();
    }

}
