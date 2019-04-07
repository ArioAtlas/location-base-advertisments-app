package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DivarAdvInfoHeader implements Serializable {

    @SerializedName("ladder")
    @Expose
    private String ladder;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("place")
    @Expose
    private String place;

    public String getLadder() {
        return ladder;
    }

    public void setLadder(String ladder) {
        this.ladder = ladder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
