package com.sadek.orxstradev.smartmall.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.ProductDetailActivity;
import com.sadek.orxstradev.smartmall.interfaces.AddFavoriteInterface;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.body.AddFavoriteBody;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.AddCartPresenter;
import com.sadek.orxstradev.smartmall.presenters.AddFavoritePresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductByCatPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> implements
        AddFavoriteInterface, ProductByCatInterface {


    private Context context;
    private ArrayList<CartModel.DataEntity> data;
    AddFavoritePresenter addFavoritePresenter;
    AddCartPresenter addCartPresenter;
    ProductByCatPresenter productByCatPresenter;

    public FavoriteAdapter(Context context, ArrayList<CartModel.DataEntity> data) {
        this.context = context;
        this.data = data;
        Paper.init(context);
        addFavoritePresenter = new AddFavoritePresenter(context, this);
        addCartPresenter = new AddCartPresenter(context, this);
        productByCatPresenter = new ProductByCatPresenter(context, this);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_product_h, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        productByCatPresenter.getProductFavById(position, holder, Integer.parseInt(data.get(position).getProductId()));


        holder.likeBtn.setImageResource(R.drawable.ic_favorite_black_24dp);
        holder.likeBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#A7228E")));
        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Paper.book().read(Common.token) != null) {
                    addFavoritePresenter.removeFavorite(new AddFavoriteBody(Paper.book().read(Common.token) + "", data.get(position).getId() + ""));
                    holder.likeBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    holder.likeBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#838383")));
                } else {
                    Toast.makeText(context, context.getString(R.string.you_are_not_login), Toast.LENGTH_SHORT).show();
                }
            }
        });

     /*   holder.cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCartPresenter.addCart(new AddCartBody(Paper.book().read(Common.token) + "", data.get(position).getId() + "", "1"));

            }
        });*/


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onSuccess(DateResponse dateResponse) {
        Toast.makeText(context, R.string.done, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, HomeCategoryAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, CartAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductByCatSuccess(final ProductApiResponse productApiResponse, ViewHolder holder, final int position) {
        if (context != null)
            Picasso.with(context).load(Common.BASE_IMAGE_URL+productApiResponse.getData().get(0).getImage1()).into(holder.productIV);

        holder.nameTV.setText(productApiResponse.getData().get(0).getName());
        holder.discountTV.setText(productApiResponse.getData().get(0).getPrice() + " " + context.getString(R.string.currency));
        holder.priceTV.setText(" " + productApiResponse.getData().get(0).getOfferPrice() + context.getString(R.string.currency));
        holder.discountTV.setPaintFlags(holder.discountTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if (productApiResponse.getData().get(0).getLikes() == null)
            holder.category_product_likes_txt.setText("0 ");
        else
            holder.category_product_likes_txt.setText(productApiResponse.getData().get(0).getLikes() + " ");

        if (productApiResponse.getData().get(0).getDiscount() == null) {
            holder.discount_view.setVisibility(View.GONE);
        }
        holder.discountamountTV.setText(productApiResponse.getData().get(0).getDiscount() + "%");


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

    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProductFavSuccess(CartModel apiResponse) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.category_product_img)
        ImageView productIV;
        //        @BindView(R.id.brandTV)
//        TextView brandTV;
        @BindView(R.id.category_product_name_txt)
        TextView nameTV;
        @BindView(R.id.category_product_price_txt)
        TextView priceTV;
        @BindView(R.id.category_product_discount_txt)
        TextView discountTV;
        @BindView(R.id.category_product_likes_txt)
        TextView category_product_likes_txt;
        @BindView(R.id.discountTV)
        TextView discountamountTV;
        @BindView(R.id.discount_view)
        View discount_view;

        @BindView(R.id.likeBtn)
        ImageView likeBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
