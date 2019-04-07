package com.arioatlas.hub_lbs.models.Divar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DivarLocationArea implements Serializable {

    @SerializedName("centroid")
    @Expose
    private DivarLocationCentroid centeroid;

    @SerializedName("radius")
    private int radius;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("slug")
    private String slug;

    public DivarLocationCentroid getCenteroid() {
        return centeroid;
    }

    public void setCenteroid(DivarLocationCentroid centeroid) {
        this.centeroid = centeroid;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
