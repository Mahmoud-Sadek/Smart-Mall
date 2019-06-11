package com.sadek.orxstradev.smartmall.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.fragments.CartFragment;
import com.sadek.orxstradev.smartmall.interfaces.CartInterface;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.body.AddFavoriteBody;
import com.sadek.orxstradev.smartmall.model.body.CartBody;
import com.sadek.orxstradev.smartmall.model.response.AddressModel;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.CartPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductByCatPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.paperdb.Paper;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.OrdersVh> {

    Context context;
    List<AddressModel.DataEntity> addressList;

    public AddressAdapter(Context context, List<AddressModel.DataEntity> addressList) {
        this.context = context;
        this.addressList = addressList;
        Paper.init(context);

    }

    @NonNull
    @Override
    public OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_address, parent, false);
        return new OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersVh holder, final int position) {
        BaseActitvty.animate(holder.itemView);
        holder.address_country_txt.setText(addressList.get(position).getCountry()+"");
        holder.address_city_txt.setText(addressList.get(position).getCity()+"");
        holder.address_street_txt.setText(addressList.get(position).getStreet()+"");
        holder.address_mobile_txt.setText(addressList.get(position).getPhone()+"");

    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class OrdersVh extends RecyclerView.ViewHolder {

        private TextView address_country_txt, address_city_txt, address_street_txt, address_mobile_txt;


        public OrdersVh(View itemView) {
            super(itemView);
            address_country_txt= itemView.findViewById(R.id.address_country_txt);
            address_city_txt= itemView.findViewById(R.id.address_city_txt);
            address_street_txt= itemView.findViewById(R.id.address_street_txt);
            address_mobile_txt= itemView.findViewById(R.id.address_mobile_txt);


        }
    }


}

