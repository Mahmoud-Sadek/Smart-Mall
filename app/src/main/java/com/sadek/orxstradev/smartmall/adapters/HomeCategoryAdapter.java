package com.sadek.orxstradev.smartmall.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.OfferActivity;
import com.sadek.orxstradev.smartmall.interfaces.HomeOfferInterface;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.presenters.OfferByCatPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.OrdersVh> implements HomeOfferInterface {

    Context context;
    List<CategoryApiResponse.DataEntity> categoryList;

    public HomeCategoryAdapter(Context context, List<CategoryApiResponse.DataEntity> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_category, parent, false);
        return new OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersVh holder, final int position) {
//        BaseActitvty.animate(holder.itemView);

        holder.categoryTextView.setText(categoryList.get(position).getTitle());

        Picasso.with(context).load(Common.BASE_IMAGE_URL+categoryList.get(position).getMobileIcon()).error(R.drawable.unpaid).into(holder.category_image);
        OfferByCatPresenter offerByCatPresenter = new OfferByCatPresenter(context, this);
        offerByCatPresenter.getOffer(position, holder, categoryList.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();

    }

    @Override
    public void onSuccess(final OfferApiResponse offerApiResponse, OrdersVh holder, final int position) {
        if (offerApiResponse.getData().size() != 0) {
            if (offerApiResponse.getData().get(0).getImage() != null) {
                Picasso.with(context).load(Common.BASE_IMAGE_URL + offerApiResponse.getData().get(0).getImage()).error(R.drawable.logo).into(holder.home_cat_offer_img);
                holder.home_cat_offer_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offerClick(offerApiResponse.getData().get(0));
                    }
                });
            }
            if (offerApiResponse.getData().size() > 1 && offerApiResponse.getData().get(1).getImage() != null) {
                Picasso.with(context).load(Common.BASE_IMAGE_URL + offerApiResponse.getData().get(1).getImage()).error(R.drawable.logo).into(holder.home_cat_offer1_img);

                holder.home_cat_offer1_txt1.setText(offerApiResponse.getData().get(1).getTitle() + "");
                holder.home_cat_offer1_txt2.setText(offerApiResponse.getData().get(1).getDescription() + "");
                holder.home_cat_offer1_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offerClick(offerApiResponse.getData().get(1));
                    }
                });
            }
            if (offerApiResponse.getData().size() > 2 && offerApiResponse.getData().get(2).getImage() != null) {
                Picasso.with(context).load(Common.BASE_IMAGE_URL + offerApiResponse.getData().get(2).getImage()).error(R.drawable.logo).into(holder.home_cat_offer2_img);

                holder.home_cat_offer2_txt1.setText(offerApiResponse.getData().get(2).getTitle() + "");
                holder.home_cat_offer2_txt2.setText(offerApiResponse.getData().get(2).getDescription() + "");
                holder.home_cat_offer2_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offerClick(offerApiResponse.getData().get(2));
                    }
                });
            }
            if (offerApiResponse.getData().size() > 3 && offerApiResponse.getData().get(3).getImage() != null){
                Picasso.with(context).load(Common.BASE_IMAGE_URL + offerApiResponse.getData().get(3).getImage()).error(R.drawable.logo).into(holder.home_cat_offer3_img);

                holder.home_cat_offer3_txt1.setText(offerApiResponse.getData().get(3).getTitle() + "");
                holder.home_cat_offer3_txt2.setText(offerApiResponse.getData().get(3).getDescription() + "");
                holder.home_cat_offer3_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offerClick(offerApiResponse.getData().get(3));
                    }
                });
            }


        }
    }

    private void offerClick(OfferApiResponse.DataEntity dataEntity) {
        Bundle bundle = new Bundle();
        bundle.putInt(Common.OfferId, dataEntity.getId());
        bundle.putString(Common.OfferImage, dataEntity.getImage());
        bundle.putString(Common.OfferName, dataEntity.getTitle());
        Intent intent = new Intent(context, OfferActivity.class);
        // we will send food id to food detail class
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    public void onProgressDialog(boolean status) {
    }

    @Override
    public void onFailure(String error) {

    }

    public class OrdersVh extends RecyclerView.ViewHolder {
        private ImageView category_image;

        private TextView categoryTextView, home_cat_offer1_txt1, home_cat_offer2_txt1, home_cat_offer3_txt1,
                home_cat_offer1_txt2, home_cat_offer2_txt2, home_cat_offer3_txt2;
        private ImageView home_cat_offer_img, home_cat_offer1_img, home_cat_offer2_img, home_cat_offer3_img;

        public OrdersVh(View itemView) {
            super(itemView);
            category_image = itemView.findViewById(R.id.category_img);
            categoryTextView = itemView.findViewById(R.id.home_cat_txt);
            home_cat_offer_img = itemView.findViewById(R.id.home_cat_offer_img);
            home_cat_offer1_img = itemView.findViewById(R.id.home_cat_offer1_img);
            home_cat_offer2_img = itemView.findViewById(R.id.home_cat_offer2_img);
            home_cat_offer3_img = itemView.findViewById(R.id.home_cat_offer3_img);

            home_cat_offer1_txt1 = itemView.findViewById(R.id.home_cat_offer1_txt1);
            home_cat_offer2_txt1 = itemView.findViewById(R.id.home_cat_offer2_txt1);
            home_cat_offer3_txt1 = itemView.findViewById(R.id.home_cat_offer3_txt1);
            home_cat_offer1_txt2 = itemView.findViewById(R.id.home_cat_offer1_txt2);
            home_cat_offer2_txt2 = itemView.findViewById(R.id.home_cat_offer2_txt2);
            home_cat_offer3_txt2 = itemView.findViewById(R.id.home_cat_offer3_txt2);
        }
    }


}

