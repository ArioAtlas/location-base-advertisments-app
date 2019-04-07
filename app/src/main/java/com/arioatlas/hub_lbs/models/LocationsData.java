package com.arioatlas.hub_lbs.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationsData {
    @SerializedName("sheypoorCityID")
    private int scid;

    @SerializedName("TehranGeoJson")
    private List<LocationGeo> locations;

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public List<LocationGeo> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationGeo> locations) {
        this.locations = locations;
    }
}

