package com.sadek.orxstradev.smartmall.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.model.body.OptionModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {

    private List<OptionModel> contents;
    private Context mContext;
    private int row_index = 0;

    public OptionAdapter(List<OptionModel> contents, Context mContext) {
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
                .inflate(R.layout.item_color_option, parent, false);
        return new ViewHolder(view);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.option_txt.setBackgroundColor(Color.parseColor(contents.get(position).getColor()));
        holder.option_txt.setText(contents.get(position).getTxt().trim());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if (row_index == position) {
            holder.option_background.setBackgroundColor(mContext.getResources().getColor(R.color.stay_color));
            holder.option_checked_image.setVisibility(View.VISIBLE);
        } else {
            holder.option_background.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
            holder.option_checked_image.setVisibility(View.GONE);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.option_background)
        View option_background;
        @BindView(R.id.option_txt)
        TextView option_txt;
        @BindView(R.id.option_checked_image)
        ImageView option_checked_image;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }

}