package com.sadek.orxstradev.smartmall.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.adapters.CategoryAdapter;
import com.sadek.orxstradev.smartmall.adapters.CategorySubAdapter;
import com.sadek.orxstradev.smartmall.interfaces.CategoryByParentInterface;
import com.sadek.orxstradev.smartmall.interfaces.CategoryInterface;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.presenters.CategoryByParentPresenter;
import com.sadek.orxstradev.smartmall.presenters.CategoryPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CategoryFragment extends Fragment implements CategoryInterface {

    List<CategoryApiResponse.DataEntity> categoryList;

    CategoryAdapter categoryAdapter;

    Unbinder unbinder;
    @BindView(R.id.category_txt_recycler)
    ShimmerRecyclerView category_txt_recycler;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    LinearLayoutManager mLinearLayoutManager;


    CategoryPresenter categoryPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_ATTACHED_IN_DECOR);
//        getActivity().getWindow().setStatusBarColor(Color.parseColor("#861a72"));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getActivity().getWindow().setStatusBarColor(Color.parseColor("#861a72"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_category, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        categoryPresenter = new CategoryPresenter(getContext(), this);


        intUI(view);
        getData();
    }

    private void getData() {
        category_txt_recycler.showShimmerAdapter();
        swipeRefreshLayout.setRefreshing(true);
        categoryList.clear();
        categoryPresenter.getCategory(0);



    }

    private void intUI(View view) {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        //Load menu
        category_txt_recycler.setLayoutManager(mLinearLayoutManager);

        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(category_txt_recycler.getContext(),
                R.anim.layout_fall_down);
        category_txt_recycler.setLayoutAnimation(controller);
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        category_txt_recycler.setAdapter(categoryAdapter);



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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        unbinder.unbind();
    }

    @Override
    public void onSuccess(CategoryApiResponse categoryApiResponse) {
        if (category_txt_recycler != null)
            category_txt_recycler.hideShimmerAdapter();
        categoryList.clear();
        if (categoryList != null)
            categoryList.addAll(categoryApiResponse.getData());
        categoryAdapter.notifyDataSetChanged();
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
}
