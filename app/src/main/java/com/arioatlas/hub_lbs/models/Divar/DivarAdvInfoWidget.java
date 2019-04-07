package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DivarAdvInfoWidget implements Serializable {

    @SerializedName("header")
    @Expose
    private DivarAdvInfoHeader header;

    @SerializedName("description")
    @Expose
    private String desc;

    @SerializedName("list_data")
    @Expose
    private DivarAdvInfoMeta[] metas;

    @SerializedName("location")
    @Expose
    private DivarAdvInfoLocation location;

    @SerializedName("images")
    @Expose
    private String[] images;

    public DivarAdvInfoHeader getHeader() {
        return header;
    }

    public void setHeader(DivarAdvInfoHeader header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public DivarAdvInfoMeta[] getMetas() {
        return metas;
    }

    public void setMetas(DivarAdvInfoMeta[] metas) {
        this.metas = metas;
    }

    public DivarAdvInfoLocation getLocation() {
        return location;
    }

    public void setLocation(DivarAdvInfoLocation location) {
        this.location = location;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
