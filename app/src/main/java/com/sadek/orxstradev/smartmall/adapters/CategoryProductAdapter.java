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

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.OrdersVh> {

    Context context;
    List<ProductApiResponse.DataEntity> productList;

    public CategoryProductAdapter(Context context, List<ProductApiResponse.DataEntity> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_category, parent, false);
        return new OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersVh holder, final int position) {
//        BaseActitvty.animate(holder.itemView);

        Picasso.with(context).load(Common.BASE_IMAGE_URL+productList.get(position).getImage1()).error(R.drawable.logo).into(holder.category_product_img);
        holder.category_product_price_txt.setText(productList.get(position).getPrice());
        holder.category_product_name_txt.setText(productList.get(position).getName());
        holder.category_product_likes_txt.setText(productList.get(position).getLikes()+"");
        holder.category_product_discount_txt.setText(productList.get(position).getPrice());
        holder.category_product_discount_txt.setPaintFlags(holder.category_product_discount_txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
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

        private TextView category_product_price_txt, category_product_discount_txt, category_product_name_txt, category_product_likes_txt;
        ImageView category_product_img;

        public OrdersVh(View itemView) {
            super(itemView);
            category_product_price_txt = itemView.findViewById(R.id.category_product_price_txt);
            category_product_discount_txt = itemView.findViewById(R.id.category_product_discount_txt);
            category_product_name_txt = itemView.findViewById(R.id.category_product_name_txt);
            category_product_img = itemView.findViewById(R.id.category_product_img);
            category_product_likes_txt = itemView.findViewById(R.id.category_product_likes_txt);
        }
    }


}

