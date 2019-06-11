package com.sadek.orxstradev.smartmall.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.CartActivity;
import com.sadek.orxstradev.smartmall.activites.ProductDetailActivity;
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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.OrdersVh> implements ProductByCatInterface, CartInterface {

    Context context;
    List<CartModel.DataEntity> cartList;

    ProductByCatPresenter productByCatPresenter;
    CartPresenter cartPresenter;

    public CartAdapter(Context context, List<CartModel.DataEntity> cartList) {
        this.context = context;
        this.cartList = cartList;
        Paper.init(context);

        productByCatPresenter = new ProductByCatPresenter(context, this);
        cartPresenter = new CartPresenter(context, this);
    }

    @NonNull
    @Override
    public OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout, parent, false);
        return new OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersVh holder, final int position) {
        BaseActitvty.animate(holder.itemView);
        holder.cart_btn_quantity.setNumber(cartList.get(position).getQuantity());
        holder.cart_quantity_txt.setText(context.getString(R.string.quantity) + cartList.get(position).getQuantity());


        productByCatPresenter.getProductById(position, holder, Integer.parseInt(cartList.get(position).getProductId()));


        holder.cart_delet_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartPresenter.deleteCart(new AddFavoriteBody(Paper.book().read(Common.token) + "", cartList.get(position).getProductId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, HomeCategoryAdapter.OrdersVh holder, int postion) {
    }

    @Override
    public void onProductByCatSuccess(final ProductApiResponse productApiResponse, OrdersVh holder, int postion) {
        if (productApiResponse.getData().size() != 0) {
            holder.cart_name_txt.setText(productApiResponse.getData().get(0).getName() + "");
            holder.cart_desc_txt.setText(productApiResponse.getData().get(0).getInfromation() + "");
            holder.cart_price_txt.setText(productApiResponse.getData().get(0).getPrice() + " " + context.getString(R.string.currency));
            Picasso.with(context).load(Common.BASE_IMAGE_URL2 + productApiResponse.getData().get(0).getImage1().get(0)).error(R.drawable.logo).into(holder.cart_img);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailActivity.product = productApiResponse.getData().get(0);
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    // we will send food id to food detail class
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
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
    public void onSuccess(CartModel cartModel) {

    }

    @Override
    public void onSuccess(DateResponse dateResponse) {
        if (Paper.book().read(Common.token) != null) {
            if (CartFragment.cartPresenter != null)
                CartFragment.cartPresenter.getCart(new CartBody(Paper.book().read(Common.token) + ""));
            if (CartActivity.cartPresenter != null)
                CartActivity.cartPresenter.getCart(new CartBody(Paper.book().read(Common.token) + ""));
        }
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

        private TextView cart_desc_txt, cart_name_txt, cart_price_txt, cart_quantity_txt;
        ElegantNumberButton cart_btn_quantity;
        ImageView cart_delet_txt, cart_img;

        public OrdersVh(View itemView) {
            super(itemView);
            cart_btn_quantity = itemView.findViewById(R.id.cart_btn_quantity);
            cart_delet_txt = itemView.findViewById(R.id.cart_delet_txt);
            cart_desc_txt = itemView.findViewById(R.id.cart_desc_txt);
            cart_img = itemView.findViewById(R.id.cart_img);
            cart_name_txt = itemView.findViewById(R.id.cart_name_txt);
            cart_price_txt = itemView.findViewById(R.id.cart_price_txt);
            cart_quantity_txt = itemView.findViewById(R.id.cart_quantity_txt);


        }
    }


}

