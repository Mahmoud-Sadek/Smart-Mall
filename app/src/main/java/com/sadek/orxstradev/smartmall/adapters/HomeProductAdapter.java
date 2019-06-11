package com.sadek.orxstradev.smartmall.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.CategoryProductActivity;
import com.sadek.orxstradev.smartmall.activites.ProductDetailActivity;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.OrdersVh> {

    Context context;
    List<ProductApiResponse.DataEntity> productList;

    CategoryApiResponse.DataEntity categoryItem;
    OfferApiResponse.DataEntity offerItem;
    public HomeProductAdapter(Context context, List<ProductApiResponse.DataEntity> productList, CategoryApiResponse.DataEntity categoryItem) {
        this.context = context;
        this.productList = productList;
        this.categoryItem = categoryItem;
    }
    public HomeProductAdapter(Context context, List<ProductApiResponse.DataEntity> productList, OfferApiResponse.DataEntity offerItem) {
        this.context = context;
        this.productList = productList;
        this.offerItem = offerItem;
    }
    @NonNull
    @Override
    public OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_home, parent, false);
        return new OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersVh holder, final int position) {
//        BaseActitvty.animate(holder.itemView);
        if (position == productList.size()|| position == 4 || 0 == productList.size()) {
            holder.home_product_name_txt.setText("More");
            holder.home_product_img.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
            ImageViewCompat.setImageTintList(holder.home_product_img, ColorStateList.valueOf(Color.parseColor("#A7228E")));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    if (categoryItem!=null) {
                        bundle.putInt(Common.OfferId, categoryItem.getId());
                        bundle.putString(Common.OfferImage, categoryItem.getImage());
                        bundle.putString(Common.OfferName, categoryItem.getTitle());
                    }else {
                        bundle.putInt(Common.OfferId, offerItem.getId());
                        bundle.putString(Common.OfferImage, offerItem.getImage());
                        bundle.putString(Common.OfferName, offerItem.getTitle());
                    }
                    Intent intent = new Intent(context, CategoryProductActivity.class);
                    // we will send food id to food detail class
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        } else {

            Picasso.with(context).load(Common.BASE_IMAGE_URL2+productList.get(position).getImage1().get(0)).error(R.drawable.logo).into(holder.home_product_img);
            holder.home_product_price_txt.setText(productList.get(position).getPrice());
            holder.home_product_name_txt.setText(productList.get(position).getName());

            holder.home_product_discount_txt.setText(productList.get(position).getPrice());
            holder.home_product_discount_txt.setPaintFlags(holder.home_product_discount_txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailActivity.product = productList.get(position);
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    // we will send food id to food detail class
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size() + 1;
    }

    public class OrdersVh extends RecyclerView.ViewHolder {

        private TextView home_product_price_txt, home_product_discount_txt,home_product_name_txt;
        ImageView home_product_img;

        public OrdersVh(View itemView) {
            super(itemView);
            home_product_price_txt = itemView.findViewById(R.id.home_product_price_txt);
            home_product_discount_txt = itemView.findViewById(R.id.home_product_discount_txt);
            home_product_name_txt = itemView.findViewById(R.id.home_product_name_txt);
            home_product_img = itemView.findViewById(R.id.home_product_img);
        }
    }


}

