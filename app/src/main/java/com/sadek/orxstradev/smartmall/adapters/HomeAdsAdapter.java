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
import com.sadek.orxstradev.smartmall.activites.CategoryActivity;
import com.sadek.orxstradev.smartmall.activites.CategorySubActivity;
import com.sadek.orxstradev.smartmall.activites.OfferActivity;
import com.sadek.orxstradev.smartmall.activites.ProductListActivity;
import com.sadek.orxstradev.smartmall.interfaces.HomeOfferInterface;
import com.sadek.orxstradev.smartmall.model.response.AdsApiResponse;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.presenters.OfferByCatPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdsAdapter extends RecyclerView.Adapter<HomeAdsAdapter.OrdersVh>  {

    Context context;
    List<AdsApiResponse.DataBean> categoryList;

    public HomeAdsAdapter(Context context, List<AdsApiResponse.DataBean> categoryList) {
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

        holder.categoryTextView.setText(categoryList.get(position).getMaincategoryName());

        Picasso.with(context).load(Common.BASE_IMAGE_URL+categoryList.get(position).getMainIcon()).error(R.drawable.unpaid).into(holder.category_image);
//        OfferByCatPresenter offerByCatPresenter = new OfferByCatPresenter(context, this);
//        offerByCatPresenter.getOffer(position, holder, categoryList.get(position).getId());
        setData(holder,position);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();

    }

    public void setData(@NonNull OrdersVh holder, final int position) {

                Picasso.with(context).load(Common.BASE_IMAGE_URL + categoryList.get(position).getImage1()).error(R.drawable.logo).into(holder.home_cat_offer_img);
                holder.home_cat_offer_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offerClick(categoryList.get(position).getSub1Id(),categoryList.get(position).getImage1(),categoryList.get(position).getDesc1());
                    }
                });


                Picasso.with(context).load(Common.BASE_IMAGE_URL + categoryList.get(position).getImage2()).error(R.drawable.logo).into(holder.home_cat_offer1_img);

                holder.home_cat_offer1_txt1.setText(categoryList.get(position).getSub2Name() + "");
                holder.home_cat_offer1_txt2.setText(categoryList.get(position).getDesc2()  + "");
                holder.home_cat_offer1_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offerClick(categoryList.get(position).getSub2Id(),categoryList.get(position).getImage2(),categoryList.get(position).getDesc2());
                    }
                });

                Picasso.with(context).load(Common.BASE_IMAGE_URL + categoryList.get(position).getImage3()).error(R.drawable.logo).into(holder.home_cat_offer2_img);

                holder.home_cat_offer2_txt1.setText(categoryList.get(position).getSub3Name() + "");
                holder.home_cat_offer2_txt2.setText(categoryList.get(position).getDesc3() + "");
                holder.home_cat_offer2_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offerClick(categoryList.get(position).getSub3Id(),categoryList.get(position).getImage3(),categoryList.get(position).getDesc3());
                    }
                });

                Picasso.with(context).load(Common.BASE_IMAGE_URL + categoryList.get(position).getImage4()).error(R.drawable.logo).into(holder.home_cat_offer3_img);

                holder.home_cat_offer3_txt1.setText(categoryList.get(position).getSub4Name() + "");
                holder.home_cat_offer3_txt2.setText(categoryList.get(position).getDesc4() + "");
                holder.home_cat_offer3_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offerClick(categoryList.get(position).getSub4Id(),categoryList.get(position).getImage4(),categoryList.get(position).getDesc4());
                    }
                });



    }

    private void offerClick(String sub1, String image1, String desc1) {
        Bundle bundle = new Bundle();
        bundle.putInt(Common.CategoryId, new Integer(sub1));
        bundle.putString(Common.CategoryImage, image1);
        bundle.putString(Common.CategoryName, desc1);
        Intent intent = new Intent(context, ProductListActivity.class);
        // we will send food id to food detail class
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
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

