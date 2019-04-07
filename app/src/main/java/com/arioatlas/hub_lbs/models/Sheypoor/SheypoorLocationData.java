package com.arioatlas.hub_lbs.models.Sheypoor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SheypoorLocationData implements Serializable {
    @SerializedName("province")
    private int povince;

    @SerializedName("city")
    private  int city;

    @SerializedName("district")
    private  int district;

    @SerializedName("districtSuggests")
    private int[] districtSuggests;

    public int getPovince() {
        return povince;
    }

    public void setPovince(int povince) {
        this.povince = povince;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int[] getDistrictSuggests() {
        return districtSuggests;
    }

    public void setDistrictSuggests(int[] districtSuggests) {
        this.districtSuggests = districtSuggests;
    }
}
