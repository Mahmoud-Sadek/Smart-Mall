package com.sadek.orxstradev.smartmall.service;

import android.location.Address;

import com.sadek.orxstradev.smartmall.model.body.AddCartBody;
import com.sadek.orxstradev.smartmall.model.body.AddFavoriteBody;
import com.sadek.orxstradev.smartmall.model.body.CartBody;
import com.sadek.orxstradev.smartmall.model.body.ChangeNameBody;
import com.sadek.orxstradev.smartmall.model.body.LoginBody;
import com.sadek.orxstradev.smartmall.model.body.ProfileBody;
import com.sadek.orxstradev.smartmall.model.body.ReviewBody;
import com.sadek.orxstradev.smartmall.model.body.SignupBody;
import com.sadek.orxstradev.smartmall.model.body.SocialLoginBody;
import com.sadek.orxstradev.smartmall.model.response.AddressModel;
import com.sadek.orxstradev.smartmall.model.response.AdsApiResponse;
import com.sadek.orxstradev.smartmall.model.response.AdsRandomApiResponse;
import com.sadek.orxstradev.smartmall.model.response.CartModel;
import com.sadek.orxstradev.smartmall.model.response.CategoryApiResponse;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.LoginResponse;
import com.sadek.orxstradev.smartmall.model.response.OfferApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProductApiResponse;
import com.sadek.orxstradev.smartmall.model.response.ProfileResponse;
import com.sadek.orxstradev.smartmall.model.response.ReviewsResponse;
import com.sadek.orxstradev.smartmall.model.response.SignupResponse;
import com.sadek.orxstradev.smartmall.model.response.SuccessResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mahmoud Sadek on 1/11/2019.
 */

public class ApiService {

    public interface ApiInterface {

        @POST("login_api")
        Call<LoginResponse> getLoginResponse(@Body LoginBody loginBody);

        @POST("login_social")
        Call<SocialLoginBody> getLoginSocialResponse(@Body SignupBody loginBody);

        @POST("signup_api")
        Call<SignupResponse> getSignupResponse(@Body SignupBody signupBody);

        //offer_api/ar
        @GET("offer_sliders/{lang}")
        Call<OfferApiResponse> getOfferApiResponse(@Path("lang") String lang);

        //offer_api/ar
        @GET("ads/{lang}")
        Call<AdsApiResponse> getAdsApiResponse(@Path("lang") String lang);

        //ads_randoms
        @GET("ads_randoms")
        Call<AdsRandomApiResponse> getAdsRandomsApiResponse();

        //offer_api/cat_id=3/ar
        @GET("offer_api/cat_id={cat_id}/{lang}")
        Call<OfferApiResponse> getOfferByCatIdResponse(@Path("cat_id") int cat_id, @Path("lang") String lang);

        //categories_api/ar
        @GET("categories_api/{lang}")
        Call<CategoryApiResponse> getCategoryApiResponse(@Path("lang") String lang);

        //categories_api/parent_id=0/ar
        @GET("categories_api/parent_id={parent_id}/{lang}")
        Call<CategoryApiResponse> getCategoryByParIdResponse(@Path("lang") String lang, @Path("parent_id") int parent_id);

        //product_api/ar
        @GET("product_api/user_id={user_id}/language={language}")
        Call<ProductApiResponse> getProductApiResponse(@Path("user_id") String user_id,@Path("language") String lang);

        //product_api/ar
        @GET("offer_flash/{lang}")
        Call<OfferApiResponse> getFlashSaleApiResponse(@Path("lang") String lang);

        //product_api/cat=3/en
        @GET("product_api/category_id={category_id}/user_id={user_id}/language={language}")
        Call<ProductApiResponse> getProductByCatIdResponse(@Path("category_id") int cat,@Path("user_id") int user_id, @Path("language") String lang);

        //product_api/offer_id=3/en
        @GET("offer_sliders/id={id}/user_id={user_id}/language={language}")
        Call<ProductApiResponse> getProductByOfferIdResponse(@Path("id") int offer_id,@Path("user_id") int user_id, @Path("language") String lang);

        //product_api/brand_id=3/en
        @GET("product_api/{lang}")
        Call<ProductApiResponse> getProductByBradIdResponse(@Query("brand_id") int brand_id, @Path("lang") String lang);

        //product_api/sku=3/en
        @GET("similar/subcategory={subcategory}/user_id={user_id}/language={language}")
        Call<ProductApiResponse> getProductBySKUResponse(@Path("subcategory") int subcategory,@Path("user_id") int user_id, @Path("language") String lang);

        //product_api/id=3/en
        @GET("product_api/id={id}/user_id={user_id}/language={language}")
        Call<ProductApiResponse> getProductByIdResponse(@Path("id") int id,@Path("user_id") int user_id, @Path("language") String lang);

        //review_api/product_id=3/en
        @GET("review_api/product_id={product_id}/{lang}")
        Call<ReviewsResponse> getReviewsResponse(@Path("product_id") int product_id, @Path("lang") String lang);
        //adress_api
        @GET("adress_api/user_id={user_id}/{lang}")
        Call<AddressModel> getAddressApiResponse(@Path("user_id") int user_id );

        //add_address_api
        @POST("add_address_api")
        Call<DateResponse> addAddressApiResponse(@Body AddressModel.DataEntity addressModel);

        //update_profile_api
        @POST("update_profile_api")
        Call<DateResponse> updateProfileApiResponse(@Body ChangeNameBody changeNameBody);

        //add_favourite_api
        @POST("add_favourite_api")
        Call<DateResponse> addFavouriteApiResponse(@Body AddFavoriteBody addFavoriteBody);

        //add_cart_api
        @POST("add_cart_api")
        Call<DateResponse> addCartApiResponse(@Body AddCartBody addCartBody,
                                              @Header("Content-Type") String Content_type);

        //profile_api
        @POST("profile_api")
        Call<ProfileResponse> getProfileApiResponse(@Body ProfileBody profileBody);

        //add_review_api
        @POST("add_review_api")
        Call<DateResponse> AddReviewApiResponse(@Body ReviewBody reviewBody);

        //cart_userid
        @POST("cart_userid")
        Call<CartModel> getCartResponse(@Body CartBody cartBody);

        //del_cart
        @POST("del_cart")
        Call<DateResponse> DeleteCartApiResponse(@Body AddFavoriteBody cartBody);

        //del_favourite
        @POST("del_favourite")
        Call<DateResponse> DeleteFavouriteApiResponse(@Body AddFavoriteBody favoriteBody);

        //get_favourite
        @POST("all_favourite_user")
        Call<CartModel> getFavouriteApiResponse(@Body AddFavoriteBody favoriteBody);

        //createAddress
        @POST("api/addresses/create")
        Call<AddressModel> getCreateAddresseApiResponse(@Body AddressModel.DataEntity addressModel,@Header("Authorization") String authorization);

        //removeAddress
        @POST("api/addresses/{id}/remove")
        Call<AddressModel> getRemoveAddresseApiResponse(@Path("id") String id,@Header("Authorization") String authorization);



        //update-email
        @POST("api/profile/update-email")
        Call<SuccessResponse> getUpdateEmailApiResponse(@Body LoginBody loginBody, @Header("Authorization") String authorization);



        //update_profile_api
//        @POST("update_profile_api")
//        Call<ProfileResponse> getProfileApiResponse(@Body ProfileBody profileBody);


    }

    public static final String BASE_URL = "http://smartmall-sa.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
