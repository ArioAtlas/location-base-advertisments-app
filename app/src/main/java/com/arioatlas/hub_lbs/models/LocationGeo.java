package com.arioatlas.hub_lbs.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationGeo {
    @SerializedName("divarID")
    private int divarId;

    @SerializedName("sheypoorID")
    private int sheypoorId;

    @SerializedName("polygon")
    private List<List<Double>> polygon;

    @SerializedName("center")
    private List<Double> center;

    public int getDivarId() {
        return divarId;
    }

    public void setDivarId(int divarId) {
        this.divarId = divarId;
    }

    public int getSheypoorId() {
        return sheypoorId;
    }

    public void setSheypoorId(int sheypoorId) {
        this.sheypoorId = sheypoorId;
    }

    public List<List<Double>> getPolygon() {
        return polygon;
    }

    public void setPolygon(List<List<Double>> polygon) {
        this.polygon = polygon;
    }

    public List<Double> getCenter() {
        return center;
    }

    public void setCenter(List<Double> center) {
        this.center = center;
    }
}
