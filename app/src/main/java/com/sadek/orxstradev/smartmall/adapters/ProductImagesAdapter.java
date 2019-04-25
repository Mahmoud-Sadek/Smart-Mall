package com.sadek.orxstradev.smartmall.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductImagesAdapter extends RecyclerView.Adapter<ProductImagesAdapter.ViewHolder> {

    private List<String> contents;
    private Context mContext;
    public ProductImagesAdapter(List<String> contents, Context mContext) {
        this.contents = contents;
        this.mContext = mContext;
    }


    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_offers, parent, false);
        return new ViewHolder(view);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if (mContext != null)
            Picasso.with(mContext).load(Common.BASE_IMAGE_URL+contents.get(position)).into(holder.image);
        holder.image.setScaleType(ImageView.ScaleType.FIT_CENTER);


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.activityImage)ImageView image;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }

}