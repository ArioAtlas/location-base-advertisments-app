package com.arioatlas.hub_lbs.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavouriteAd {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "token")
    public String token;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "price")
    public long price;

    @ColumnInfo(name = "date_time")
    public int dateTime;
}
