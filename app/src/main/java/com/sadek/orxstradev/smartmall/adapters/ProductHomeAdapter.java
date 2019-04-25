package com.sadek.orxstradev.smartmall.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.ProductDetailActivity;
import com.sadek.orxstradev.smartmall.interfaces.AddFavoriteInterface;
import com.sadek.orxstradev.smartmall.model.body.AddCartBody;
import com.sadek.orxstradev.smartmall.model.body.AddFavoriteBody;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.AddCartPresenter;
import com.sadek.orxstradev.smartmall.presenters.AddFavoritePresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.paperdb.Paper;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ViewHolder> implements
        AddFavoriteInterface {


    private Context context;
    private ArrayList<ProductApiResponse.DataEntity> data;
    AddFavoritePresenter addFavoritePresenter;
    AddCartPresenter addCartPresenter;
    public static KProgressHUD dialog = null;

    public ProductHomeAdapter(Context context, ArrayList<ProductApiResponse.DataEntity> data) {
        this.context = context;
        this.data = data;
        dialog = new KProgressHUD(context);
        Paper.init(context);
        addFavoritePresenter = new AddFavoritePresenter(context, this);
        addCartPresenter = new AddCartPresenter(context, this);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_product_h, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if (context != null)
            Picasso.with(context).load(Common.BASE_IMAGE_URL+data.get(position).getImage1()).into(holder.productIV);

        holder.nameTV.setText(data.get(position).getName());
        holder.discountTV.setText(data.get(position).getPrice() + " " + context.getString(R.string.currency));
        holder.priceTV.setText( " " + data.get(position).getOfferPrice() + context.getString(R.string.currency));
        holder.discountTV.setPaintFlags(holder.discountTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if (data.get(position).getLikes() == null)
            holder.category_product_likes_txt.setText("0 ");
        else
            holder.category_product_likes_txt.setText(data.get(position).getLikes() + " ");

        holder.discountamountTV.setText(data.get(position).getDiscount() + "%" );
        if (data.get(position).getDiscount()==null){
            holder.discount_view.setVisibility(View.GONE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailActivity.product = data.get(position);
                Intent intent = new Intent(context, ProductDetailActivity.class);
                // we will send food id to food detail class
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


       holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Paper.book().read(Common.token) != null) {
                    addFavoritePresenter.addFavorite(new AddFavoriteBody(Paper.book().read(Common.token) + "", data.get(position).getId() + ""));
                    holder.likeBtn.setImageResource(R.drawable.ic_favorite_black_24dp);
                    holder.likeBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#A7228E")));
                }else {
                    Toast.makeText(context, context.getString(R.string.you_are_not_login), Toast.LENGTH_SHORT).show();
                }
            }
        });

     /*   holder.cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCartPresenter.addCart(new AddCartBody(Paper.book().read(Common.token) + "", data.get(position).getId() + "", "1"));

            }
        });*/


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onSuccess(DateResponse dateResponse) {
        Toast.makeText(context, R.string.done, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onProgressDialog(boolean status) {
        try {
            if (dialog != null) {
                if (status)
                    dialog.show();
                else dialog.dismiss();
            }
        }catch (Exception e){
        }
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.category_product_img)
        ImageView productIV;
//        @BindView(R.id.brandTV)
//        TextView brandTV;
        @BindView(R.id.category_product_name_txt)
        TextView nameTV;
        @BindView(R.id.category_product_price_txt)
        TextView priceTV;
        @BindView(R.id.category_product_discount_txt)
        TextView discountTV;
        @BindView(R.id.category_product_likes_txt)
        TextView category_product_likes_txt;
        @BindView(R.id.discountTV)
        TextView discountamountTV;
        @BindView(R.id.discount_view)
        View discount_view;
        @BindView(R.id.likeBtn)
        ImageView likeBtn;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
