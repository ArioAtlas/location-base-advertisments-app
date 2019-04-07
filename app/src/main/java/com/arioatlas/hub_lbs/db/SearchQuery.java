package com.arioatlas.hub_lbs.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SearchQuery {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "search_name")
    public String searchName;

    @ColumnInfo(name = "location_latitude")
    public double locationLat;

    @ColumnInfo(name = "location_longitude")
    public double locationLong;

    @ColumnInfo(name = "cat_id")
    public int catId;
}
