package com.sadek.orxstradev.smartmall.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.adapters.AddressAdapter;
import com.sadek.orxstradev.smartmall.adapters.CartAdapter;
import com.sadek.orxstradev.smartmall.interfaces.AddressInterface;
import com.sadek.orxstradev.smartmall.model.response.AddressModel;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.presenters.AddressPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.paperdb.Paper;

public class AddressActivity extends BaseActitvty implements AddressInterface {
    List<AddressModel.DataEntity> addressList;
    AddressAdapter addressAdapter;


    Unbinder unbinder;
    @BindView(R.id.address_rcy)
    RecyclerView address_rcy;


        public static AddressPresenter addressInterface;
    LinearLayoutManager mLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        unbinder = ButterKnife.bind(this);
        addressInterface = new AddressPresenter(getBaseContext(), this);


        intUI();
        getData();
    }


    private void getData() {

        addressList.clear();
        addressInterface.getAddress(new  Integer(Paper.book().read(Common.token)+""));


    }

    private void intUI() {
        mLinearLayoutManager = new LinearLayoutManager(getBaseContext());
        //Load menu
        address_rcy.setLayoutManager(mLinearLayoutManager);

        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(address_rcy.getContext(),
                R.anim.layout_fall_down);
        address_rcy.setLayoutAnimation(controller);
        addressList = new ArrayList<>();
        addressAdapter = new AddressAdapter(getBaseContext(), addressList);
        address_rcy.setAdapter(addressAdapter);

    }

    @OnClick(R.id.btn_add_address)
    public void btn_product(View view) {
        startActivity(new Intent(this, AddAddressActivity.class));

    }


    @Override
    public void onSuccess(AddressModel addressModel) {
        addressList.clear();
        if (addressList != null)
//            addressList.addAll(addressModel.getData());
        addressAdapter.notifyDataSetChanged();
    }


    @Override
    public void onProgressDialog(boolean status) {
    }

    @Override
    public void onFailure(String error) {

    }

}
