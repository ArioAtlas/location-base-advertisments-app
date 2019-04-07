package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DivarLocationResponse implements Serializable {

    @SerializedName("city")
    private DivarLocationArea city;

    @SerializedName("result")
    private String result;

    @SerializedName("district")
    private DivarLocationArea district;

    public DivarLocationArea getCity() {
        return city;
    }

    public void setCity(DivarLocationArea city) {
        this.city = city;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DivarLocationArea getDistrict() {
        return district;
    }

    public void setDistrict(DivarLocationArea district) {
        this.district = district;
    }
}
