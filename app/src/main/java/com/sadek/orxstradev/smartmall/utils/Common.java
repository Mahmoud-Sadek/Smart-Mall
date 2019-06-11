package com.sadek.orxstradev.smartmall.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Common {


    public static final String token = "token";
    public static final String language = "language";
    public static final String OfferId = "OfferId";
    public static final String OfferImage = "OfferImage";
    public static final String OfferName = "OfferName";

    public static final String CategoryId = "CategoryId";
    public static final String CategoryImage = "CategoryImage";
    public static final String CategoryName = "CategoryName";
    public static final String FIRIST_TIME = "firstTime";

    public static final String BASE_IMAGE_URL = "http://smartmall-sa.com/dashboard/storage/app/";
    public static final String BASE_IMAGE_URL2 = "http://smartmall-sa.com/dashboard/storage/app/images/";
    public static String CURRENT_USSER ;
    public static final String api_token = "api_token";


    public static boolean isConnectedToInternet (Context context){
       /* ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null){
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null){
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }*/
        return true;
    }

    public static void restartApp(Context context, Activity activity) {
        Intent settingIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        settingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        settingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(settingIntent);
        activity.finish();
    }
}
