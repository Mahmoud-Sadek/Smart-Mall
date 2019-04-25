package com.sadek.orxstradev.smartmall.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.CheckOutActivity;
import com.sadek.orxstradev.smartmall.activites.ProductSearchActivity;
import com.sadek.orxstradev.smartmall.adapters.CartAdapter;
import com.sadek.orxstradev.smartmall.interfaces.CartInterface;
import com.sadek.orxstradev.smartmall.model.body.CartBody;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.presenters.CartPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.paperdb.Paper;

public class CartFragment extends Fragment implements CartInterface {
    List<CartModel.DataEntity> cartList;
    CartAdapter cartAdapter;


    Unbinder unbinder;
    @BindView(R.id.tabTxt)
    TextView tabTxt;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.cart_rcy)
    RecyclerView cart_rcy;
    @BindView(R.id.cart_empty)
    View cart_empty;

    @BindView(R.id.btn_check_out)
    Button btn_check_out;

    public static CartPresenter cartPresenter;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        cartPresenter = new CartPresenter(getContext(), this);


        intUI(view);
        getData();
    }

    @OnClick(R.id.btn_check_out)
    public void btn_check_out(View view) {
        startActivity(new Intent(getContext(), CheckOutActivity.class));

    }

    private void getData() {
        if (Paper.book().read(Common.token) != null) {
            swipeRefreshLayout.setRefreshing(true);
            cartList.clear();
            cartPresenter.getCart(new CartBody(Paper.book().read(Common.token) + ""));
        }

    }

    @OnClick(R.id.btn_product)
    public void btn_product(View view) {
        getActivity().startActivity(new Intent(getActivity(), ProductSearchActivity.class));

    }

    private void intUI(View view) {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        //Load menu
        cart_rcy.setLayoutManager(mLinearLayoutManager);

        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(cart_rcy.getContext(),
                R.anim.layout_fall_down);
        cart_rcy.setLayoutAnimation(controller);
        cartList = new ArrayList<>();
        cartAdapter = new CartAdapter(getContext(), cartList);
        cart_rcy.setAdapter(cartAdapter);


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
    public void onSuccess(CartModel cartModel) {
        cartList.clear();
        if (cartList != null)
            cartList.addAll(cartModel.getData());

        cartAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);

        cart_empty.setVisibility(View.VISIBLE);
        if (cartModel.getData().size() != 0) {
            cart_empty.setVisibility(View.GONE);
            btn_check_out.setVisibility(View.VISIBLE);
        } else btn_check_out.setVisibility(View.GONE);

    }

    @Override
    public void onSuccess(DateResponse dateResponse) {

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
        } catch (Exception e) {

        }
    }


}
