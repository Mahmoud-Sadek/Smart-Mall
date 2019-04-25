package com.sadek.orxstradev.smartmall.activites;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.adapters.CartAdapter;
import com.sadek.orxstradev.smartmall.adapters.CheckOutAdapter;
import com.sadek.orxstradev.smartmall.adapters.ExploreOfferAdapter;
import com.sadek.orxstradev.smartmall.adapters.FavoriteAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.adapters.ProductHomeAdapter;
import com.sadek.orxstradev.smartmall.adapters.ProductImagesAdapter;
import com.sadek.orxstradev.smartmall.adapters.ReviewAdapter;
import com.sadek.orxstradev.smartmall.dialogs.ReviewDialog;
import com.sadek.orxstradev.smartmall.interfaces.AddFavoriteInterface;
import com.sadek.orxstradev.smartmall.interfaces.ProductByCatInterface;
import com.sadek.orxstradev.smartmall.interfaces.ReviewInterface;
import com.sadek.orxstradev.smartmall.model.body.AddCartBody;
import com.sadek.orxstradev.smartmall.model.body.AddFavoriteBody;
import com.sadek.orxstradev.smartmall.model.body.ReviewBody;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ReviewsResponse;
import com.sadek.orxstradev.smartmall.presenters.AddCartPresenter;
import com.sadek.orxstradev.smartmall.presenters.AddFavoritePresenter;
import com.sadek.orxstradev.smartmall.presenters.AddReviewPresenter;
import com.sadek.orxstradev.smartmall.presenters.ProductBySKUPresenter;
import com.sadek.orxstradev.smartmall.presenters.ReviewPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.sadek.orxstradev.smartmall.view.CircleIndicator2;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

public class ProductDetailActivity extends BaseActitvty implements AddFavoriteInterface,
        ProductByCatInterface, ReviewInterface, RatingDialogListener {

    //init the offers Recycler
    @BindView(R.id.imagesRV)
    RecyclerView imagesRV;
    //init the loader
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.productsRV)
    RecyclerView productsRV;


    //init the views
    @BindView(R.id.brandTV)
    TextView brandTV;
    @BindView(R.id.nameTV)
    TextView nameTV;
    @BindView(R.id.discountTV)
    TextView discountTV;
    @BindView(R.id.priceTV)
    TextView priceTV;
    @BindView(R.id.offerPriceTV)
    TextView offerPriceTV;
    @BindView(R.id.detailTV)
    TextView detailTV;
    @BindView(R.id.informationTV)
    TextView informationTV;
    @BindView(R.id.likeTxt)
    TextView likeTxt;
    @BindView(R.id.likeBtn)
    ImageView likeBtn;


    //the images
    ProductImagesAdapter adapterImages;
    ArrayList<String> dataImages = new ArrayList<>();


    //todo product have the same sku

    ProductHomeAdapter adapter;
    ArrayList<ProductApiResponse.DataEntity> productList = new ArrayList<>();


    //todo the reviews
    @BindView(R.id.reviewsRV)
    RecyclerView reviewsRV;
    ReviewAdapter reviewAdapter;

    ArrayList<ReviewsResponse.DataEntity> reviewData = new ArrayList<>();


    public static ProductApiResponse.DataEntity product;

    public static KProgressHUD dialog = null;
    AddFavoritePresenter addFavoritePresenter;
    AddReviewPresenter addReviewPresenter;
    AddCartPresenter addCartPresenter;
    ProductBySKUPresenter productBySKUPresenter;
    ReviewPresenter reviewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        dialog = new KProgressHUD(this);


        addReviewPresenter = new AddReviewPresenter(this, this);
        addFavoritePresenter = new AddFavoritePresenter(this, this);
        addCartPresenter = new AddCartPresenter(this, this);
        productBySKUPresenter = new ProductBySKUPresenter(this, this);
        reviewPresenter = new ReviewPresenter(this, this);
        initUI();

        if (product.getSku() != null)
            productBySKUPresenter.getProduct(Integer.parseInt(product.getSku() + ""));
        reviewPresenter.getReview(product.getId());

    }

    private void initUI() {
        Paper.init(this);
        //init the images
        RecyclerView.LayoutManager layoutManager6 = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        imagesRV.setLayoutManager(layoutManager6);
        adapterImages = new ProductImagesAdapter(dataImages, ProductDetailActivity.this);
        imagesRV.setAdapter(adapterImages);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(imagesRV);
        CircleIndicator2 indicator = findViewById(R.id.indicator);
        indicator.attachToRecyclerView(imagesRV, pagerSnapHelper);
        adapterImages.registerAdapterDataObserver(indicator.getAdapterDataObserver());


        // the products that have the same sku


        adapter = new ProductHomeAdapter(ProductDetailActivity.this, productList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProductDetailActivity.this, 2);

        productsRV.setLayoutManager(layoutManager);
        productsRV.setAdapter(adapter);


        //todo the reviews
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        reviewsRV.setLayoutManager(layoutManager1);

        reviewAdapter = new ReviewAdapter(reviewData, ProductDetailActivity.this, 1);

        reviewsRV.setAdapter(reviewAdapter);


        initData();

    }

    private void initData() {
        brandTV.setText(product.getBrandId() + "");
        nameTV.setText(product.getName() + "");
        priceTV.setText(product.getPrice() + " " + getString(R.string.currency));
        if (product.getDiscount() == null)
            discountTV.setVisibility(View.GONE);
        else
            discountTV.setText(getString(R.string.discount) + " " + product.getDiscount() + " ٪ ");
        offerPriceTV.setText(product.getOfferPrice() + " " + getString(R.string.currency));
        priceTV.setPaintFlags(offerPriceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        detailTV.setText(product.getDetail() + "");
        informationTV.setText(product.getInfromation() + "");

        if (product.getLikes() == null)
            likeTxt.setText("0");
        else
            likeTxt.setText(product.getLikes() + "");

        List<String> imageList = new ArrayList<>();
        if (product.getImage1() != null)
            imageList.add(product.getImage1());
        if (product.getImage2() != null)
            imageList.add(product.getImage2());
        if (product.getImage3() != null)
            imageList.add(product.getImage3());
        if (product.getImage4() != null)
            imageList.add(product.getImage4());
        if (product.getImage5() != null)
            imageList.add(product.getImage5());
        dataImages.addAll(imageList);
        adapterImages.notifyDataSetChanged();

    }


    // close current activity
    @OnClick(R.id.backIV)
    void backIV() {
        finish();
    }

    @OnClick(R.id.shareImage)
    void shareImage() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = product.getName();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                product.getLinks());
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


    ReviewDialog reviewDialog;

    @OnClick(R.id.addReviewParent)
    void addReview() {
        if (Paper.book().read(Common.token) != null) {

            showRatingDialog();
        } else {
            Toast.makeText(this, getString(R.string.you_are_not_login), Toast.LENGTH_SHORT).show();
        }

    }


    private void showRatingDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNeutralButtonText("Later")
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(1)
                .setTitle("Rate this food")
                .setDescription("Please select some stars and give your feedback")
                .setStarColor(R.color.colorPrimaryDark)
                .setNoteDescriptionTextColor(R.color.colorAccent)
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.colorPrimary)
                .setHint("Please write your comment here ...")
                .setHintTextColor(R.color.colorAccent)
                .setCommentTextColor(R.color.colorText)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .create(ProductDetailActivity.this)
                .show();
    }


    @OnClick(R.id.likeBtn)
    void addLikeBtn() {
        if (Paper.book().read(Common.token) != null) {
            addFavoritePresenter.addFavorite(new AddFavoriteBody(Paper.book().read(Common.token) + "", product.getId() + ""));
            likeBtn.setImageResource(R.drawable.ic_favorite_black_24dp);
            likeBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#A7228E")));
        } else {
            Toast.makeText(this, getString(R.string.you_are_not_login), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @OnClick(R.id.addCartBtn)
    void addCartBtn() {

        if (Paper.book().read(Common.token) != null) {
            addCartPresenter.addCart(new AddCartBody(Paper.book().read(Common.token) + "", product.getId() + "", "1"));
        } else {
            Toast.makeText(this, getString(R.string.you_are_not_login), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    @OnClick(R.id.goCartBtn)
    void goCartBtn() {
        startActivity(new Intent(this, CartActivity.class));
    }


    @Override
    public void onSuccess(DateResponse dateResponse) {
        Toast.makeText(getBaseContext(), getString(R.string.done), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, HomeCategoryAdapter.OrdersVh holder, int postion) {
        productList.clear();
        if (productList != null)
            productList.addAll(productApiResponse.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, CartAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductByCatSuccess(ProductApiResponse productApiResponse, FavoriteAdapter.ViewHolder holder, int postion) {

    }

    @Override
    public void onProductByOfferSuccess(ProductApiResponse productApiResponse, ExploreOfferAdapter.OrdersVh holder, int postion) {
        productList.clear();
        if (productList != null)
            productList.addAll(productApiResponse.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onProductByOfferSuccess(ProductApiResponse productApiResponse, CheckOutAdapter.OrdersVh holder, int postion) {

    }

    @Override
    public void onProductSuccess(ProductApiResponse productApiResponse) {

    }

    @Override
    public void onSuccess(ReviewsResponse reviewsResponse) {
        reviewData.clear();
        if (reviewData != null)
            reviewData.addAll(reviewsResponse.getData());
        reviewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProgressDialog(boolean status) {
        if (dialog != null) {
            if (status)
                dialog.show();
            else dialog.dismiss();
        }
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(getBaseContext(), R.string.error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProductFavSuccess(CartModel apiResponse) {

    }

    @Override
    public void onPositiveButtonClicked(int value, String comments) {
        ReviewBody reviewBody = new ReviewBody(Paper.book().read(Common.token) + "", comments, product.getId() + "");
        addReviewPresenter.addFavorite(reviewBody);
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }
}
