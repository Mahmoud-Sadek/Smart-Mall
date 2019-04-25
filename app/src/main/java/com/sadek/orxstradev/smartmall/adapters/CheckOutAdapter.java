package com.sadek.orxstradev.smartmall.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.fragments.CartFragment;
import com.sadek.orxstradev.smartmall.interfaces.CartInterface;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.body.AddFavoriteBody;
import com.sadek.orxstradev.smartmall.model.body.CartBody;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.CartPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductByCatPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.paperdb.Paper;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.OrdersVh> implements ProductByCatInterface, CartInterface {

    Context context;
    List<CartModel.DataEntity> cartList;

    ProductByCatPresenter productByCatPresenter;
    CartPresenter cartPresenter;
    public CheckOutAdapter(Context context, List<CartModel.DataEntity> cartList) {
        this.context = context;
        this.cartList = cartList;
        Paper.init(context);

        productByCatPresenter = new ProductByCatPresenter(context,this);
        cartPresenter = new CartPresenter(context,this);
    }

    @NonNull
    @Override
    public OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_flash, parent, false);
        return new OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersVh holder, final int position) {
//        BaseActitvty.animate(holder.itemView);



        productByCatPresenter.getProductById(position,holder,Integer.parseInt(cartList.get(position).getProductId()));


    }

    @Override
    public int getItemCount() {
        return cartList.size();
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
    public void onProductByOfferSuccess(ProductApiResponse productApiResponse, OrdersVh holder, int postion) {
        if (productApiResponse.getData().size() != 0) {
            holder.cart_name_txt.setText(productApiResponse.getData().get(0).getName() + "");
            holder.cart_price_txt.setText(productApiResponse.getData().get(0).getPrice() + " $");
            Picasso.with(context).load(Common.BASE_IMAGE_URL+productApiResponse.getData().get(0).getImage1()).error(R.drawable.logo).into(holder.cart_img);
        }
    }

    @Override
    public void onProductSuccess(ProductApiResponse productApiResponse) {

    }

    @Override
    public void onSuccess(CartModel cartModel) {

    }

    @Override
    public void onSuccess(DateResponse dateResponse) {
        if (Paper.book().read(Common.token)!=null)
        CartFragment.cartPresenter.getCart(new CartBody(Paper.book().read(Common.token)+""));
    }

    @Override
    public void onProgressDialog(boolean status) {

    }

    @Override
    public void onFailure(String error) {

    }

    @Override
    public void onProductFavSuccess(CartModel apiResponse) {

    }

    public class OrdersVh extends RecyclerView.ViewHolder {

        private TextView  cart_name_txt, cart_price_txt;
        ImageView  cart_img;

        public OrdersVh(View itemView) {
            super(itemView);
            cart_img= itemView.findViewById(R.id.home_product_img);
            cart_name_txt= itemView.findViewById(R.id.home_product_discount_txt);
            cart_price_txt= itemView.findViewById(R.id.home_product_price_txt);



        }
    }


}

