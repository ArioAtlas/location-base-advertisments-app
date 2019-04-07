package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DivarAdvInfoMeta implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("format")
    @Expose
    private String format;

    @SerializedName("value")
    @Expose
    private String value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
