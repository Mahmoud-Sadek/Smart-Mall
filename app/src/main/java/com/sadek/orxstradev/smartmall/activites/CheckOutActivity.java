package com.sadek.orxstradev.smartmall.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.adapters.CartAdapter;
import com.sadek.orxstradev.smartmall.adapters.CheckOutAdapter;
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

public class CheckOutActivity extends BaseActitvty implements CartInterface {
    List<CartModel.DataEntity> cartList;
    CheckOutAdapter cartAdapter;


    Unbinder unbinder;
    @BindView(R.id.cart_rcy)
    RecyclerView cart_rcy;
    @BindView(R.id.total_price_txt)
    TextView total_price_txt;
    @BindView(R.id.total_qty_txt)
    TextView total_qty_txt;


    public static CartPresenter cartPresenter;
    LinearLayoutManager mLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        unbinder = ButterKnife.bind(this);
        cartPresenter = new CartPresenter(getBaseContext(), this);


        intUI();
        getData();
    }

    private void getData() {
        cartList.clear();
        cartPresenter.getCart(new CartBody(Paper.book().read(Common.token) + ""));


    }

    private void intUI() {
        mLinearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        //Load menu
        cart_rcy.setLayoutManager(mLinearLayoutManager);

        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(cart_rcy.getContext(),
                R.anim.layout_fall_down);
        cart_rcy.setLayoutAnimation(controller);
        cartList = new ArrayList<>();
        cartAdapter = new CheckOutAdapter(getBaseContext(), cartList);
        cart_rcy.setAdapter(cartAdapter);

    }

    @OnClick(R.id.btn_check_out)
    public void btn_check_out(View view) {
        Toast.makeText(this, "Will be available soon", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.add_address_btn)
    public void add_address_btn(View view) {
        startActivity(new Intent(getBaseContext(), AddressActivity.class));

    }


    @Override
    public void onSuccess(CartModel cartModel) {
        cartList.clear();
        if (cartList != null)
            cartList.addAll(cartModel.getData());
        cartAdapter.notifyDataSetChanged();
        int totalPrice, totalQty=0;
        for (int i = 0; i < cartModel.getData().size(); i++) {
            totalQty +=new Integer(cartModel.getData().get(i).getQuantity());
        }
//        total_price_txt.setText("×" +);
        total_qty_txt.setText("×" +totalQty);
    }

    @Override
    public void onSuccess(DateResponse dateResponse) {

    }

    @Override
    public void onProgressDialog(boolean status) {
    }

    @Override
    public void onFailure(String error) {

    }


}
