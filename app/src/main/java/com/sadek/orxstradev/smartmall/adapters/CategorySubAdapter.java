package com.sadek.orxstradev.smartmall.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.CategoryProductActivity;
import com.sadek.orxstradev.smartmall.interfaces.CategoryByParentInterface;
import com.sadek.orxstradev.smartmall.interfaces.CategoryInterface;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.CategoryByParentPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductByCatPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategorySubAdapter extends RecyclerView.Adapter<CategorySubAdapter.OrdersVh> implements CategoryByParentInterface {

    Context context;
    List<CategoryApiResponse.DataEntity> categoryList;

    public CategorySubAdapter(Context context, List<CategoryApiResponse.DataEntity> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_sub, parent, false);
        return new OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersVh holder, final int position) {
//        BaseActitvty.animate(holder.itemView);

        holder.category_txt.setText(categoryList.get(position).getTitle());

        CategoryByParentPresenter categoryByParentPresenter = new CategoryByParentPresenter(context, this);
        categoryByParentPresenter.getCategorys(position, holder, categoryList.get(position).getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(Common.CategoryId, categoryList.get(position).getId());
                bundle.putString(Common.CategoryImage, categoryList.get(position).getImage());
                bundle.putString(Common.CategoryName, categoryList.get(position).getTitle());
                Intent intent = new Intent(context, CategoryProductActivity.class);
                // we will send food id to food detail class
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public void onCategoryByParentSuccess(CategoryApiResponse categoryApiResponse) {

    }

    @Override
    public void onCategoryByParentSuccess(CategoryApiResponse categoryApiResponse, OrdersVh holder, int position) {

        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        holder.categoryRCV.setLayoutManager(mLayoutManager);
        List<CategoryApiResponse.DataEntity> categoryApiResponseList = new ArrayList<>();
        categoryApiResponseList.addAll(categoryApiResponse.getData());
        CategorySubItemAdapter categorySubItemAdapter = new CategorySubItemAdapter(context, categoryApiResponseList);
        holder.categoryRCV.setAdapter(categorySubItemAdapter);
    }

    @Override
    public void onProgressDialog(boolean status) {

    }

    @Override
    public void onFailure(String error) {

    }

    public class OrdersVh extends RecyclerView.ViewHolder {

        private TextView category_txt;
        private RecyclerView categoryRCV;

        public OrdersVh(View itemView) {
            super(itemView);
            category_txt = itemView.findViewById(R.id.category_sub_txt);
            categoryRCV = itemView.findViewById(R.id.category_sub_rcy);


        }
    }


}

