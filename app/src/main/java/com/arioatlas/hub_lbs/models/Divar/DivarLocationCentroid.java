package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DivarLocationCentroid implements Serializable {

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
