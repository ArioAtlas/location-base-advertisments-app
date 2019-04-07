package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DivarAdvInfoData implements Serializable {

    @SerializedName("nabz_product_id")
    @Expose
    private int npId;

    @SerializedName("url")
    @Expose
    private String url;

    public int getNpId() {
        return npId;
    }

    public void setNpId(int npId) {
        this.npId = npId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
