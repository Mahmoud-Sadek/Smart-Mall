package com.sadek.orxstradev.smartmall.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.CategoryActivity;
import com.sadek.orxstradev.smartmall.activites.CategorySubActivity;
import com.sadek.orxstradev.smartmall.fragments.CategoryFragment;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.OrdersVh> {

    Context context;
    List<CategoryApiResponse.DataEntity> categoryList;

    public CategoryAdapter(Context context, List<CategoryApiResponse.DataEntity> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_txt, parent, false);
        return new OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersVh holder, final int position) {
//        BaseActitvty.animate(holder.itemView);

        holder.category_txt.setText(categoryList.get(position).getTitle());
        Picasso.with(context).load(Common.BASE_IMAGE_URL+categoryList.get(position).getImage()).error(R.drawable.logo).into(holder.category_image);
        holder.itemView.setBackgroundColor(Color.WHITE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(Common.CategoryId, categoryList.get(position).getId());
                bundle.putString(Common.CategoryImage, categoryList.get(position).getImage());
                bundle.putString(Common.CategoryName, categoryList.get(position).getTitle());
                Intent intent = new Intent(context, CategorySubActivity.class);
                // we will send food id to food detail class
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class OrdersVh extends RecyclerView.ViewHolder {

        private TextView category_txt;
        private ImageView category_image;

        public OrdersVh(View itemView) {
            super(itemView);
            category_txt = itemView.findViewById(R.id.category_txt);
            category_image = itemView.findViewById(R.id.category_img);

        }
    }


}

