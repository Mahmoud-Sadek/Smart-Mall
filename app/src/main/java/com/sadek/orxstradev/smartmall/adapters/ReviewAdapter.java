package com.sadek.orxstradev.smartmall.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.model.response.ReviewsResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<ReviewsResponse.DataEntity> contents;
    private Context mContext;

    //1 for detail screen 2 for all screen
    int type;

    public ReviewAdapter(List<ReviewsResponse.DataEntity> contents, Context mContext, int type) {
        this.contents = contents;
        this.mContext = mContext;
        this.type = type;
    }


    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


        if (type == 1) {

            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_review, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_review_all, parent, false);
        }
        return new ViewHolder(view);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.detailTV.setText(contents.get(position).getText());
        holder.nameTV.setText(contents.get(position).getUserId());

        //todo here to get the profile of the user


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nameTV)
        TextView nameTV;
        @BindView(R.id.detailTV)
        TextView detailTV;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }

}