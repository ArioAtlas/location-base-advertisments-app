package com.arioatlas.hub_lbs.models;

import java.io.Serializable;

public class Categories implements Serializable {
    private int cover;
    private String title;
    private int dId;
    private int sId;

    public Categories(String title, int cover, int divarId, int sheypoorId) {
        this.cover = cover;
        this.title = title;
        this.dId = divarId;
        this.sId = sheypoorId;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }
}
