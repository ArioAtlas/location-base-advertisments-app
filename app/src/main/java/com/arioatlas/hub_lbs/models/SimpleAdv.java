package com.arioatlas.hub_lbs.models;

public class SimpleAdv {

    private String imageUrl;
    private String title;
    private long price;
    private int datetime;
    private String locationName;
    private String token;
    private boolean coverAvailable;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getDatetime() {
        return datetime;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isCoverAvailable() {
        return coverAvailable;
    }

    public void setCoverAvailable(boolean coverAvailable) {
        this.coverAvailable = coverAvailable;
    }
}
