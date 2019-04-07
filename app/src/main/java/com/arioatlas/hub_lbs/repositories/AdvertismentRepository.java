package com.arioatlas.hub_lbs.repositories;

import android.util.Log;
import android.widget.Toast;

import com.arioatlas.hub_lbs.AdvertisementsListActivity;
import com.arioatlas.hub_lbs.api.DivarApiClient;
import com.arioatlas.hub_lbs.api.DivarApiInterface;
import com.arioatlas.hub_lbs.api.SheypoorApiClient;
import com.arioatlas.hub_lbs.api.SheypoorApiInterface;
import com.arioatlas.hub_lbs.models.Divar.DivarAdv;
import com.arioatlas.hub_lbs.models.Divar.DivarLocationArea;
import com.arioatlas.hub_lbs.models.Divar.DivarLocationResponse;
import com.arioatlas.hub_lbs.models.Divar.DivarSearchResponse;
import com.arioatlas.hub_lbs.models.Divar.DivarSearchMeta;
import com.arioatlas.hub_lbs.models.Divar.DivarSearchRequest;
import com.arioatlas.hub_lbs.models.SimpleAdv;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvertismentRepository {
    private static final String TAG = "AdvertismentRepository";
    private static AdvertismentRepository instance;
    private long divarLastPostTime;
    final MutableLiveData<List<SimpleAdv>> divarAds = new MutableLiveData<>();
    final MutableLiveData<DivarLocationResponse> dLocation = new MutableLiveData<>();
    final MutableLiveData<Integer> dCatId = new MutableLiveData<>();
    private LatLng lastCoordinate;


    public static AdvertismentRepository getInstance(){
        if(instance == null){
            instance = new AdvertismentRepository();
        }

        return instance;
    }


    public MutableLiveData<List<SimpleAdv>> getDivarAds(int cityId, int districtId, int categoryId){

        List<SimpleAdv> mData = new ArrayList<>();
        Call<DivarSearchResponse> call;

        DivarApiInterface dApi = DivarApiClient.getApiClient().create(DivarApiInterface.class);
        DivarSearchRequest dsr = new DivarSearchRequest();

        DivarSearchMeta cityMeta = new DivarSearchMeta("place2",0, new int[]{cityId==-1?1:cityId});
        DivarSearchMeta districtMeta = new DivarSearchMeta("place",0, new int[]{districtId});
        DivarSearchMeta categoryMeta = new DivarSearchMeta("cat1",0, new int[]{categoryId});

        dsr.addSearchMeta(cityMeta.prepare());
        if(districtId!=-1){
            dsr.addSearchMeta(districtMeta.prepare());
        }
        dsr.addSearchMeta(categoryMeta.prepare());
        dsr.setSearchValidity();

        call = dApi.getAds(dsr);

        call.enqueue(new Callback<DivarSearchResponse>() {
            @Override
            public void onResponse(Call<DivarSearchResponse> call, Response<DivarSearchResponse> response) {
                if(response.isSuccessful() && response.body().getResult() != null) {
                    divarLastPostTime = response.body().getResult().getLastPostDate();

                    for (DivarAdv adv : response.body().getResult().getPostList()){
                        SimpleAdv sa = new SimpleAdv();
                        sa.setTitle(adv.getTitle());
                        sa.setImageUrl(adv.getImage());
                        sa.setPrice(adv.getV09());
                        sa.setToken(adv.getToken());
                        sa.setDatetime(adv.getLm());
                        sa.setLocationName(adv.getP4()+"");
                        sa.setCoverAvailable(adv.getIc()>0);
                        mData.add(sa);
                    }

                    divarAds.postValue(mData);
                }
            }

            @Override
            public void onFailure(Call<DivarSearchResponse> call, Throwable t) {
                // Data set failed to load
                Log.d(TAG, "onFailure: Failed to fetch divar");
            }
        });

        return divarAds;
    }

    public MutableLiveData<DivarLocationResponse> getDivarLocation(LatLng coordinate){
        if(!coordinate.equals(lastCoordinate)){
            DivarApiInterface dApi = DivarApiClient.getApiClient().create(DivarApiInterface.class);
            dApi.getLocationId(coordinate.getLatitude(),coordinate.getLongitude())
                    .enqueue(new Callback<DivarLocationResponse>() {
                        @Override
                        public void onResponse(Call<DivarLocationResponse> call, Response<DivarLocationResponse> response) {
                            if(response.isSuccessful()){
                                //TODO: rewrite last coordinate update proccess when sheypour added!
                                lastCoordinate = coordinate;
                                dLocation.postValue(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<DivarLocationResponse> call, Throwable t) {
                            Log.d(TAG, "onFailure: Failed to get location info");
                        }
                    });
        }

        return dLocation;
    }

    public DivarLocationResponse getDivarLocation(){
        return dLocation.getValue();
    }

    public void setDivarCategoryId(int id){
        dCatId.setValue(id);
    }

    private void getSheypoorAds(){
        SheypoorApiInterface sApi = SheypoorApiClient.getApiClient().create(SheypoorApiInterface.class);
    }

    public LiveData<Integer> getDivarSelectedCategoriId(){
        return dCatId;
    }
}
