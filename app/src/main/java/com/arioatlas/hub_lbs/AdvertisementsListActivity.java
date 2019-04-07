package com.arioatlas.hub_lbs;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.arioatlas.hub_lbs.models.Divar.DivarResult;
import com.arioatlas.hub_lbs.models.SimpleAdv;
import com.arioatlas.hub_lbs.viewmodels.AdvertisementViewModel;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AdvertisementsListActivity extends AppCompatActivity {
    private static final String TAG = "OMID Advertisement";
    private AdvAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AdvertisementViewModel mAdvertisementViewModel;
    private LatLng position;
    private int divarCatId;
    private int sheypoorCatId;
    private DivarResult divarResult = new DivarResult();

    @BindView(R.id.adv_list) RecyclerView recyclerView;
    @BindView(R.id.adv_search_group) TextView groupName;
    @BindView(R.id.adv_search_area) TextView areaName;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_advertisements);
        ButterKnife.bind(this);
        position = new LatLng(getIntent().getDoubleExtra("lat",0),getIntent().getDoubleExtra("long",0));
        divarCatId = getIntent().getIntExtra("divarCatId",0);
        sheypoorCatId = getIntent().getIntExtra("sheypoorCatId",0);

        // Search  DivarSearchResponse Location
        mAdvertisementViewModel = ViewModelProviders.of(this).get(AdvertisementViewModel.class);
        mAdvertisementViewModel.init();
        mAdvertisementViewModel.getAds().observe(this, new Observer<List<SimpleAdv>>() {
            @Override
            public void onChanged(List<SimpleAdv> simpleAdvs) {
                adapter.updateAdvList(simpleAdvs);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "ORS onChanged: ADV adapter notified!");

                for(SimpleAdv sadv : simpleAdvs){
                    Log.d(TAG, "ORS ADVS #"+sadv.getTitle());
                }
            }
        });

        populateRecycleView();
//        if(location != null){
//            loadDivarData(divarCatId, location.getDivarId());
//            String an = getIntent().getStringExtra("catName");
//            groupName.setText("گروه: "+an);
//            areaName.setText("محدوده: "+location.getDivarId());
////            Log.d(TAG, "LOC S ID: "+location.getSheypoorId());
//        }


//        Log.d(TAG, "Sheypour Id: "+sheypoorCatId);



    }

    public void populateRecycleView(){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setNestedScrollingEnabled(false);

        adapter = new AdvAdapter(this,mAdvertisementViewModel.getAds().getValue());
        recyclerView.setAdapter(adapter);

    }

    @OnClick(R.id.save_search)
    public void saveSearch(){
        Toast.makeText(this,"جستجوی شما ذخیره شد",Toast.LENGTH_SHORT).show();
    }
}
