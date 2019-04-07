package com.arioatlas.hub_lbs;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.arioatlas.hub_lbs.models.Divar.DivarLocationResponse;
import com.arioatlas.hub_lbs.viewmodels.AdvertisementViewModel;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener, LocationEngineCallback {
    private MapView mapView;
    private MapboxMap map;
    private PermissionsManager permissionsManager;
    private Location originLocation;
    private static final String TAG = "OMID_TAG_MAIN";
    private SymbolManager symbolManager;
    private Symbol mapSymbolsStatus;
    AdvertisementViewModel viewModel;

    private static final String MAP_RED_PIN_ID = "map_red_pin";
    private static final String MAP_BLUE_PIN_ID = "map_blue_pin";


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(AdvertisementViewModel.class);

        mapView = findViewById(R.id.mainMap);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        //checkScraper();
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        map = mapboxMap;
        map.setMinZoomPreference(7);
        map.setMaxZoomPreference(17);
//        map.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
//            @Override
//            public boolean onMapClick(@NonNull LatLng point) {
//                CameraPosition cp = map.getCameraPosition();
//
//                if(symbolManager != null){
//                    if (mapSymbolsStatus != null) {
//                        mapSymbolsStatus.setLatLng(cp.target);
//                        symbolManager.update(mapSymbolsStatus);
//                    }
//                    else{
//                        mapSymbolsStatus = symbolManager.create(new SymbolOptions() .withLatLng(new LatLng(cp.target.getLatitude(), cp.target.getLongitude()))
//                                .withIconImage(MAP_RED_PIN_ID)
//                                .withIconAnchor(Property.ICON_ANCHOR_BOTTOM));
//                    }
//
//                }
//                String string = String.format(Locale.US, "User clicked at: %s", cp.target.toString());
//                Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();
//
//
////                if(symbolManager != null){
////                    if (mapSymbolsStatus != null) {
////                        mapSymbolsStatus.setLatLng(point);
////                        symbolManager.update(mapSymbolsStatus);
////                    }
////                    else{
////                        mapSymbolsStatus = symbolManager.create(new SymbolOptions() .withLatLng(new LatLng(point.getLatitude(), point.getLongitude()))
////                                .withIconImage(MAP_RED_PIN_ID)
////                                .withIconAnchor(Property.ICON_ANCHOR_BOTTOM));
////                    }
////
////                }
////                String string = String.format(Locale.US, "User clicked at: %s", point.toString());
////                Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();
//
//                return true;
//            }
//        });

        mapboxMap.setStyle(new Style.Builder().fromUrl("mapbox://styles/mapbox/light-v9"), style -> {
            // Map is set up and the style has loaded. Now you can add
            // data or make other map adjustments
            enableLocation();
            symbolManager = new SymbolManager(mapView,map,style);
            // set non-data-driven properties, such as:
            symbolManager.setIconAllowOverlap(true);
            symbolManager.setIconTranslate(new Float[]{-4f,5f});
            setupImages(style);
        });
    }

    private void setupImages(Style style){
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),R.drawable.map_pin_red);
        style.addImage(MAP_RED_PIN_ID,icon);
        icon = BitmapFactory.decodeResource(this.getResources(),R.drawable.map_pin_blue);
        style.addImage(MAP_BLUE_PIN_ID,icon);
    }

    @SuppressWarnings("MissingPermission")
    private void enableLocation(){
        if(PermissionsManager.areLocationPermissionsGranted(this)){
            Log.d(TAG, "enableLocation: Success");

            LocationEngine locationEngine = LocationEngineProvider.getBestLocationEngine(this);

            LocationEngineRequest request = new LocationEngineRequest.Builder(7000)
                    .setPriority(LocationEngineRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                    .setMaxWaitTime(12000).build();

            locationEngine.requestLocationUpdates(request,this,getMainLooper());

            LocationComponent locationComponent = map.getLocationComponent();
            locationComponent.activateLocationComponent(this,map.getStyle());
            locationComponent.setLocationEngine(locationEngine);
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.COMPASS);


        } else{
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this,"Location permission is needed for finding nearby advertisements",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if(granted){
            enableLocation();
        }else{
            finish();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void onSuccess(Object result) {
        originLocation = ((LocationEngineResult)result).getLastLocation();
    }

    @Override
    public void onFailure(@NonNull Exception exception) {
        Log.d(TAG, "onFailure: Get Location in failure");
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @OnClick(R.id.search_ads)
    public void searchForAdvertisements(){

        LatLng coord = map.getCameraPosition().target;
        viewModel.getLocationData(coord);
        Intent intent = new Intent(MainActivity.this,SelectCategoryActivity.class);
        intent.putExtra("lat",coord.getLatitude());
        intent.putExtra("long",coord.getLongitude());
        startActivity(intent);
    }

    public void checkScraper(){
        SheypoorScraper scraper = new SheypoorScraper("https://www.sheypoor.com/search?c=43627&ct=301&nh[]=928&r=8");
        scraper.execute();

//        OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url("https://www.sheypoor.com/search?c=43627&ct=301&nh[]=928&r=8")
//                        .build();
//
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.d(TAG, "onFailure: Web Call Failed!");
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Log.d(TAG, "checkScraper: " + response.body().string());
//                    }
//                });
                    

   


    }

}
