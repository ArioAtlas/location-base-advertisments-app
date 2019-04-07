package com.arioatlas.hub_lbs.viewmodels;

import com.arioatlas.hub_lbs.models.Divar.DivarLocationResponse;
import com.arioatlas.hub_lbs.models.SimpleAdv;
import com.arioatlas.hub_lbs.repositories.AdvertismentRepository;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class AdvertisementViewModel extends ViewModel {
    private MutableLiveData<List<SimpleAdv>> ads;
    private AdvertismentRepository mRepo;
    private LiveData<DivarLocationResponse> dLocation;
    private int categoryId;

    public AdvertisementViewModel() {
        mRepo = AdvertismentRepository.getInstance();
    }

    public void init(){
        if(this.ads !=null){
            return;
        }

        // TODO: there is great chance that district would be null, so checking for null is required
        ads = mRepo.getDivarAds(mRepo.getDivarLocation().getCity()!=null?mRepo.getDivarLocation().getCity().getId():-1,
                mRepo.getDivarLocation().getDistrict()!=null?mRepo.getDivarLocation().getDistrict().getId():-1,
                mRepo.getDivarSelectedCategoriId().getValue());
    }



    public void setCategoryId(int categoryId){
        mRepo.setDivarCategoryId(categoryId);
        this.categoryId=categoryId;
    }

    public LiveData<DivarLocationResponse> getLocationData(LatLng coordinate){
        return mRepo.getDivarLocation(coordinate);
    }

    public LiveData<List<SimpleAdv>> getAds(){
        return ads;
    }
}
