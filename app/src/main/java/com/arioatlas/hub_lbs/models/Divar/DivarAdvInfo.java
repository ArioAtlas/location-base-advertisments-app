package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DivarAdvInfo implements Serializable {

    @SerializedName("data")
    @Expose
    private DivarAdvInfoData data;

    @SerializedName("widgets")
    @Expose
    private DivarAdvInfoWidget widgets;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("error")
    @Expose
    private int error;

    public DivarAdvInfoData getData() {
        return data;
    }

    public void setData(DivarAdvInfoData data) {
        this.data = data;
    }

    public DivarAdvInfoWidget getWidgets() {
        return widgets;
    }

    public void setWidgets(DivarAdvInfoWidget widgets) {
        this.widgets = widgets;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
