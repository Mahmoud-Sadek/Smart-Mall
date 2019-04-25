package com.sadek.orxstradev.smartmall.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.model.Category;

import java.util.List;

public class FashionCategoryAdapter  extends RecyclerView.Adapter<FashionCategoryAdapter.OrdersVh> {
    Context context;
    List<Category> categoryList;
//    OrderFragmentPresenter orderFragmentPresenter;

    public FashionCategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList =categoryList;
//        this.orderFragmentPresenter = orderFragmentPresenter;
    }

    @NonNull
    @Override
    public FashionCategoryAdapter.OrdersVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_fashion, parent, false);
        return new FashionCategoryAdapter.OrdersVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FashionCategoryAdapter.OrdersVh holder, final int position) {
//        BaseActitvty.animate(holder.itemView);
/*
        holder.provider_name_tv.setText(categoryList.get(position).getMemberName());
        holder.order_code_tv.setText(categoryList.get(position).getCode());

        holder.provider_location_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(context, MapsActivity.class);
                *//*Bundle bundle = new Bundle();
                bundle.putDouble(Common.lat, orderList.get(position).getLatitude());
                bundle.putDouble(Common.lng, orderList.get(position).getLongitude());
                mapIntent.putExtras(bundle);*//*
                context.startActivity(mapIntent);
            }
        });*/


    }

    @Override
    public int getItemCount() {
//        return categoryList.size();
        return 18;
    }

    public class OrdersVh extends RecyclerView.ViewHolder{

        private TextView discountTextView, provider_location_tv, provider_phone_tv, order_code_tv;

        public OrdersVh(View itemView) {
            super(itemView);
           /* provider_name_tv=itemView.findViewById(R.id.provider_name_tv);
            provider_location_tv=itemView.findViewById(R.id.provider_location_tv);
            provider_phone_tv=itemView.findViewById(R.id.provider_phone_tv);
            order_code_tv= itemView.findViewById(R.id.order_code_tv);
*/

        }
    }



}

