package com.sadek.orxstradev.smartmall.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.ProductDetailActivity;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FlashSaleProductAdapter extends RecyclerView.Adapter<FlashSaleProductAdapter.OrdersVh> {

    Context context;
    List<ProductApiResponse.DataEntity> productList;

    public FlashSaleProductAdapter(Context context, List<ProductApiResponse.DataEntity> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_flash, parent, false);
        return new OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersVh holder, final int position) {
        Picasso.with(context).load(Common.BASE_IMAGE_URL + productList.get(position).getImage1()).error(R.drawable.logo).into(holder.home_product_img);
        holder.home_product_price_txt.setText(productList.get(position).getOfferPrice() != null ? productList.get(position).getOfferPrice() :
                productList.get(position).getPrice() + context.getString(R.string.currency));

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

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class OrdersVh extends RecyclerView.ViewHolder {

        private TextView home_product_price_txt, home_product_discount_txt;
        ImageView home_product_img;

        public OrdersVh(View itemView) {
            super(itemView);
            home_product_price_txt = itemView.findViewById(R.id.home_product_price_txt);
            home_product_discount_txt = itemView.findViewById(R.id.home_product_discount_txt);

            home_product_img = itemView.findViewById(R.id.home_product_img);
        }
    }


}

