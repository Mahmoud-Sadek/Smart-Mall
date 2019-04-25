package com.sadek.orxstradev.smartmall.activites;

import android.content.Intent;


import com.crashlytics.android.Crashlytics;
import com.daimajia.androidanimations.library.Techniques;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


import io.fabric.sdk.android.Fabric;
import io.paperdb.Paper;


public class SplachActivity extends AwesomeSplash {

    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!


    @Override
    public void initSplash(ConfigSplash configSplash) {
//        LocaleUtils.initialize(getBaseContext(), LocaleUtils.ARABIC);
        /* you don't have to override every property */
//        final Fabric fabric = new Fabric.Builder(this)
//                .kits(new Crashlytics())
//                .debuggable(true)
//                .build();
//        Fabric.with(fabric, new Crashlytics());
        Fabric.with(this, new Crashlytics());
        overridePendingTransition(R.anim.animation1, R.anim.animation2);
        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.strokeColor); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(0); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.logo_);//or any other drawable
        configSplash.setAnimLogoSplashDuration(1500); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
//        configSplash.setPathSplash(); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(5); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
//        configSplash.setPathSplashFillColor(R.color.white); //path object filling color

        //Customize Title
        configSplash.setTitleSplash("Best offers is ours");
        configSplash.setTitleTextColor(R.color.colorPrimary);
        configSplash.setTitleTextSize(20f); //float value
        configSplash.setAnimTitleDuration(100);
        configSplash.setAnimTitleTechnique(Techniques.FadeInUp);
//        configSplash.setTitleFont("arabic2.otf"); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {

        //transit to another activity here
        //or do whatever you want
        Paper.init(getBaseContext());
        if (Paper.book().contains(Common.FIRIST_TIME)) {
            final Intent mainIntent = new Intent(SplachActivity.this, HomeActivity.class);
            SplachActivity.this.startActivity(mainIntent);
            SplachActivity.this.finish();
            return;
        }
        Common.CURRENT_USSER = Paper.book().read(Common.token);
        if (Common.CURRENT_USSER == null) {

            SplachActivity.this.startActivity(new Intent(SplachActivity.this, MainActivity.class));
            SplachActivity.this.finish();
        } else {
            final Intent mainIntent = new Intent(SplachActivity.this, HomeActivity.class);
            SplachActivity.this.startActivity(mainIntent);
            SplachActivity.this.finish();
        }
    }
}
