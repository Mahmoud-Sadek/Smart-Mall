package com.sadek.orxstradev.smartmall.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;

import com.sadek.orxstradev.smartmall.adapters.FashionCategoryAdapter;
import com.sadek.orxstradev.smartmall.adapters.HomeCategoryAdapter;
import com.sadek.orxstradev.smartmall.model.Category;

import java.util.ArrayList;
import java.util.List;

public class FashionActivity extends BaseActitvty {

    RecyclerView recyclerView;
    List<Category> categoryList;
    FashionCategoryAdapter fashionCategoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);

        intUI();
    }

    private void intUI() {
        recyclerView = findViewById(R.id.recycler_menu);

        final RecyclerView.LayoutManager layoutManager= new GridLayoutManager(getBaseContext(), 3);

        recyclerView.setLayoutManager(layoutManager);
        categoryList = new ArrayList<>();
        fashionCategoryAdapter = new FashionCategoryAdapter(getBaseContext(), categoryList);
        recyclerView.setAdapter(fashionCategoryAdapter);
        recyclerView.setHorizontalScrollBarEnabled(true);

    }
}
