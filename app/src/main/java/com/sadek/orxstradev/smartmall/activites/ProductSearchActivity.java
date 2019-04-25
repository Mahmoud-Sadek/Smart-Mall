package com.sadek.orxstradev.smartmall.activites;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.adapters.CartAdapter;
import com.sadek.orxstradev.smartmall.adapters.CheckOutAdapter;
import com.sadek.orxstradev.smartmall.adapters.ExploreOfferAdapter;
import com.sadek.orxstradev.smartmall.adapters.FavoriteAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.adapters.ProductHomeAdapter;
import com.sadek.orxstradev.smartmall.dialogs.FilterBottomDialogFragment;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.presenters.ProductPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductSearchActivity extends AppCompatActivity implements ProductByCatInterface {


    //init the views
    @BindView(R.id.category_product_rcy)
    RecyclerView productsRV;
    @BindView(R.id.linearviewBtn)
    ImageView verticalView;
    @BindView(R.id.gridviewBtn)
    ImageView gridView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;


    ProductHomeAdapter adapter;
    ArrayList<ProductApiResponse.DataEntity> productList = new ArrayList<>();
    ProductPresenter productPresenter;

    //    ProductAdapter adapter;
//
    ArrayList<ProductApiResponse.DataEntity> data = new ArrayList<>();

    //    ArrayList<ProductModel> data_input = new ArrayList<>();
//
//    //intent data
//    CategoryModel category;
//    OfferModel offer ;
//
//    //shared
//    SharedPrefDueDate pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        ButterKnife.bind(this);

        productPresenter = new ProductPresenter(getBaseContext(), this);

        //todo here to get the seach
        final SearchView searchView = findViewById(R.id.search);
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(android.R.color.darker_gray));
        searchEditText.setHintTextColor(getResources().getColor(android.R.color.darker_gray));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.setIconified(true);
                searchView.clearFocus();
                productList.clear();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getName().toLowerCase().contains(s.toLowerCase())) {
                        productList.add(data.get(i));

                    }
                }
                adapter = new ProductHomeAdapter(getBaseContext(), productList);
                productsRV.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                productList.clear();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getName().toLowerCase().contains(s.toLowerCase())) {
                        productList.add(data.get(i));

                    }
                }
                adapter = new ProductHomeAdapter(getBaseContext(), productList);
                productsRV.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return true;
            }
        });


        adapter = new ProductHomeAdapter(ProductSearchActivity.this, productList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProductSearchActivity.this, 2);
        productsRV.setLayoutManager(layoutManager);
        productsRV.setAdapter(adapter);


        productPresenter.getProduct();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_bright);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Common.isConnectedToInternet(getBaseContext())) {
                    productPresenter.getProduct();
                } else {
                    Toast.makeText(getBaseContext(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                    return;
                }
            }
        });
        swipeRefreshLayout.setRefreshing(false);

    }


    @OnClick(R.id.gridviewBtn)
    void gridView() {
        gridView.setVisibility(View.GONE);
        verticalView.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProductSearchActivity.this, 2);
        productsRV.setLayoutManager(layoutManager);
        adapter = new ProductHomeAdapter(ProductSearchActivity.this, productList);
        productsRV.setAdapter(adapter);
        //todo

    }

    @OnClick(R.id.linearviewBtn)
    void verticalView() {
        gridView.setVisibility(View.VISIBLE);
        verticalView.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProductSearchActivity.this, LinearLayoutManager.VERTICAL, false);
        productsRV.setLayoutManager(layoutManager);
        adapter = new ProductHomeAdapter(ProductSearchActivity.this, productList);
        productsRV.setAdapter(adapter);
        //todo
    }

    FilterBottomDialogFragment filterBottomDialogFragment;

    @OnClick(R.id.filterViewBtn)
    void filterViewBtn() {

        filterBottomDialogFragment = FilterBottomDialogFragment.newInstance(new FilterBottomDialogFragment.FilterAction() {
            @Override
            public void onGetFilterCode(int code) {
                filterBottomDialogFragment.dismiss();
                getFilterData(code);
            }
        });
        filterBottomDialogFragment.show(getSupportFragmentManager(),
                "filter_dialog_fragment");
    }

    private void getFilterData(int code) {
        if (code == 2) {
            productPresenter.getProduct();
            adapter = new ProductHomeAdapter(getBaseContext(), productList);
            productsRV.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            return;
        }

        ArrayList<ProductApiResponse.DataEntity> temp1 = new ArrayList<>();
        temp1.addAll(productList);
        ArrayList<ProductApiResponse.DataEntity> temp2 = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
//            int maxPrice = new Integer(temp1.get(0).getPrice());
//            if (temp1.get(0).getOfferPrice()!=null)
//                 maxPrice = new Integer(temp1.get(0).getOfferPrice());
            int maxPrice = temp1.get(0).getOfferPrice() != null ? new Integer(temp1.get(0).getOfferPrice()) : new Integer(temp1.get(0).getPrice());

            int id = 0;
            for (int j = 0; j < temp1.size(); j++) {
                if (maxPrice < (temp1.get(j).getOfferPrice() != null ? new Integer(temp1.get(j).getOfferPrice()) : new Integer(temp1.get(j).getPrice()))) {
                    maxPrice = (temp1.get(j).getOfferPrice() != null ? new Integer(temp1.get(j).getOfferPrice()) : new Integer(temp1.get(j).getPrice()));
                    id = j;
                }
                /*if (maxPrice < new Integer(temp1.get(j).getPrice())) {
                    maxPrice = new Integer(temp1.get(j).getPrice());
                    id = j;
                }*/
            }
            temp2.add(temp1.get(id));
            temp1.remove(id);
        }

        if (code == 0)
            Collections.reverse(temp2);
        adapter = new ProductHomeAdapter(getBaseContext(), temp2);
        productsRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, HomeCategoryAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, CartAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, FavoriteAdapter.ViewHolder holder, int postion) {

    }

    @Override
    public void onProductByOfferSuccess(ProductApiResponse productApiResponse, ExploreOfferAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductByOfferSuccess(ProductApiResponse productApiResponse, CheckOutAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductSuccess(ProductApiResponse productApiResponse) {

        productList.clear();
        if (productList != null) {
            productList.addAll(productApiResponse.getData());
            data.addAll(productList);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onProgressDialog(boolean status) {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(status);
    }

    @Override
    public void onFailure(String error) {

    }

    @Override
    public void onProductFavSuccess(CartModel apiResponse) {

    }


   /* @SuppressLint("SetTextI18n")
    private void extractJson(JSONObject response) throws JSONException {

        JSONArray arr = response.getJSONArray("data");


        data.clear();
        for(int i=0;i<arr.length();i++){
            JSONObject object = arr.getJSONObject(i);
            ProductModel model = new ProductModel();

            model.setId(object.getInt("id"));

            model.setName(object.getString("name"));
            model.setPrice(object.getString("price"));
            model.setOffer_price(object.getString("offer_price"));



            ArrayList<String> images = new ArrayList<>();

            if (!object.getString("image1").equals("null")){
                images.add(Constant.baseImage+object.getString("image1"));
            }else{
                images.add(Constant.baseImage+"1549419257.jpg");
            }
            if (!object.getString("image2").equals("null")){
                images.add(Constant.baseImage+object.getString("image2"));
            }else{
                images.add(Constant.baseImage+"1549419257.jpg");
            }

            if (!object.getString("image3").equals("null")){
                images.add(Constant.baseImage+object.getString("image3"));
            }else{
                images.add(Constant.baseImage+"1549419257.jpg");
            }
            if (!object.getString("image4").equals("null")){
                images.add(Constant.baseImage+object.getString("image4"));
            }else{
                images.add(Constant.baseImage+"1549419257.jpg");
            }
            if (!object.getString("image5").equals("null")){
                images.add(Constant.baseImage+object.getString("image5"));
            }else{
                images.add(Constant.baseImage+"1549419257.jpg");
            }

            model.setImages(images);
            model.setQuantity(object.getString("quantity"));
            model.setSelling_quantity(object.getString("selling_quantity"));
            model.setInfromation(object.getString("infromation"));
            model.setLikes(object.getString("likes"));
            model.setDetail(object.getString("detail"));

            model.setFreeshing(object.getString("freeshing"));
            model.setDiscount(object.getString("discount"));
            model.setLinks(object.getString("links"));
            model.setSku(object.getString("sku"));

            model.setPublisher(object.getString("publisher"));
            model.setOffer_id(object.getString("publisher"));
            model.setCategory_id(object.getString("category_id"));
            model.setBrand_id(object.getString("brand_id"));
            model.setTime(object.getString("time"));

            data.add(model);


        }
        adapter.notifyDataSetChanged();
    }


    @OnClick(R.id.cartBtn)void cartActio(){
        startActivity(new Intent(ProductSearchActivity.this,CartActivity.class));
    }*/

}
