package com.arioatlas.hub_lbs;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arioatlas.hub_lbs.models.Categories;
import com.arioatlas.hub_lbs.viewmodels.AdvertisementViewModel;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SelectCategoryActivity extends AppCompatActivity {
    CategoryAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    AdvertisementViewModel viewModel;

    @BindView(R.id.categoriesRecycleView) RecyclerView cRecyclerView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_select);
        viewModel = ViewModelProviders.of(this).get(AdvertisementViewModel.class);
        LatLng coord = new LatLng(getIntent().getDoubleExtra("lat",0),getIntent().getDoubleExtra("long",0));

        ButterKnife.bind(this);

        List<Categories> cats = new ArrayList<>();
        cats.add(new Categories("املاک و مستغلات",R.drawable.cat_cover_house,143,0));
        cats.add(new Categories("وسایل نقلیه",R.drawable.cat_cover_car,67,0));
        cats.add(new Categories("لوازم الکترونیکی",R.drawable.cat_cover_electrical,1,0));
        cats.add(new Categories("مربوط به خانه",R.drawable.cat_cover_appliance,2,0));
        cats.add(new Categories("خدمات",R.drawable.cat_cover_service,125,0));
        cats.add(new Categories("وسایل شخصی",R.drawable.cat_cover_personal,12,0));
        cats.add(new Categories("سرگرمی و فراغت",R.drawable.cat_cover_entertainment,38,0));
        cats.add(new Categories("اجتماعی",R.drawable.cat_cover_social,151,0));
        cats.add(new Categories("برای کسب و کار",R.drawable.cat_cover_business,79,0));
        cats.add(new Categories("استخدام و کاریابی",R.drawable.cat_cover_job,191,0));


        layoutManager = new LinearLayoutManager(this);
        adapter = new CategoryAdapter(this, cats, viewModel, coord);

        cRecyclerView.setLayoutManager(layoutManager);
        cRecyclerView.setAdapter(adapter);
    }

}
