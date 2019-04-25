package com.sadek.orxstradev.smartmall.activites;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.adapters.BottomBarAdapter;
import com.sadek.orxstradev.smartmall.adapters.ViewPagerAdapter;
import com.sadek.orxstradev.smartmall.fragments.CartFragment;
import com.sadek.orxstradev.smartmall.fragments.CategoryFragment;
import com.sadek.orxstradev.smartmall.fragments.ExploreFragment;
import com.sadek.orxstradev.smartmall.fragments.HomeFragment;
import com.sadek.orxstradev.smartmall.fragments.ProfileFragment;
import com.sadek.orxstradev.smartmall.utils.LocaleUtils;
import com.sadek.orxstradev.smartmall.utils.NoSwipePager;

public class HomeActivity extends BaseActitvty {

    //the icons of tablayout  icon white  don't selected
    private int[] tabIcons = {
            R.drawable.ic_home_black_24dp,
            R.drawable.category,
            R.drawable.ic_explore_black_24dp,
            R.drawable.ic_shopping_cart_black_24dp,
            R.drawable.ic_account_circle_black_24dp

    };
    // icon of tab layout selected blue icons
    private int[] tabIconsSelected = {
            R.drawable.ic_home_black_24dp,
            R.drawable.category,
            R.drawable.ic_explore_black_24dp,
            R.drawable.ic_shopping_cart_black_24dp,
            R.drawable.ic_account_circle_black_24dp
    };

    //inti the views
    private TabLayout tabLayout;
    private ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        LocaleUtils.initialize(getBaseContext(), LocaleUtils.ARABIC);

        getWindow().setStatusBarColor(Color.TRANSPARENT);


        // inti the viewPager and set up it
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        //inti the tab layout and it's icons
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();



    }

    @Override
    public void onResume() {
        super.onResume();
    }
    ViewPagerAdapter adapter;
    /**
     * set up the view pager fragment
     *
     * @param viewPager refer to the viewPager view
     */
    private void setupViewPager(ViewPager viewPager) {

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), getString(R.string.title_home));
        adapter.addFragment(new CategoryFragment(), getString(R.string.title_category));
        adapter.addFragment(new ExploreFragment(), getString(R.string.title_explore));
        adapter.addFragment(new CartFragment(), getString(R.string.title_cart));
        adapter.addFragment(new ProfileFragment(), getString(R.string.title_profile));
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(6);


    }


    /**
     * set up the tab icons to the tab layout and inti the custom view to it
     */
    private void setupTabIcons() {
        final View view0 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        ((ImageView) view0.findViewById(R.id.image_tab)).setImageResource(R.drawable.ic_home_black_24dp);
        ((ImageView) view0.findViewById(R.id.image_tab)).setImageTintList( ColorStateList.valueOf(Color.parseColor("#A7228E")));
        tabLayout.getTabAt(0).setCustomView(view0);

        View view1 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        ((ImageView) view1.findViewById(R.id.image_tab)).setImageResource(tabIcons[1]);
        ((ImageView) view1.findViewById(R.id.image_tab)).setImageTintList( ColorStateList.valueOf(Color.parseColor("#838383")));
        tabLayout.getTabAt(1).setCustomView(view1);

        View view2 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        ((ImageView) view2.findViewById(R.id.image_tab)).setImageResource(tabIcons[2]);
        ((ImageView) view2.findViewById(R.id.image_tab)).setImageTintList( ColorStateList.valueOf(Color.parseColor("#838383")));
        tabLayout.getTabAt(2).setCustomView(view2);

        View view3 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        ((ImageView) view3.findViewById(R.id.image_tab)).setImageResource(tabIcons[3]);
        ((ImageView) view3.findViewById(R.id.image_tab)).setImageTintList( ColorStateList.valueOf(Color.parseColor("#838383")));
        tabLayout.getTabAt(3).setCustomView(view3);
        View view4 = getLayoutInflater().inflate(R.layout.custom_tab, null);

        ((ImageView) view4.findViewById(R.id.image_tab)).setImageResource(tabIcons[4]);
        ((ImageView) view4.findViewById(R.id.image_tab)).setImageTintList( ColorStateList.valueOf(Color.parseColor("#838383")));
        tabLayout.getTabAt(4).setCustomView(view4);


        final View[] selectedImageResources = {view0, view1, view2, view3,view4};

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((ImageView) selectedImageResources[tab.getPosition()].findViewById(R.id.image_tab))
                        .setImageResource(tabIconsSelected[tab.getPosition()]);
                ((ImageView) selectedImageResources[tab.getPosition()].findViewById(R.id.image_tab)).setImageTintList( ColorStateList.valueOf(Color.parseColor("#A7228E")));
                tab.setCustomView(selectedImageResources[tab.getPosition()]);
                onPageSelected(adapter.getItem(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((ImageView) selectedImageResources[tab.getPosition()].findViewById(R.id.image_tab))
                        .setImageResource(tabIcons[tab.getPosition()]);
                ((ImageView) selectedImageResources[tab.getPosition()].findViewById(R.id.image_tab)).setImageTintList( ColorStateList.valueOf(Color.parseColor("#838383")));
                tab.setCustomView(selectedImageResources[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.press_back_again), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void onPageSelected(Fragment fragment)
    {
        if (fragment instanceof HomeFragment)
        {
            hideStatusBar();
        }
        else
        {
            showStatusBar();
        }
    }

    public void hideStatusBar()
    {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void showStatusBar()
    {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);
    }
}


