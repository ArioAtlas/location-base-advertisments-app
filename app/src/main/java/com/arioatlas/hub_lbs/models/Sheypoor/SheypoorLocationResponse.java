package com.arioatlas.hub_lbs.models.Sheypoor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SheypoorLocationResponse implements Serializable {

    @SerializedName("data")
    private SheypoorLocationData data;

    @SerializedName("success")
    private boolean success;

    public SheypoorLocationData getData() {
        return data;
    }

    public void setData(SheypoorLocationData data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
