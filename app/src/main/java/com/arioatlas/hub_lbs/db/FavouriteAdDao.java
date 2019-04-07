package com.arioatlas.hub_lbs.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FavouriteAdDao {
    @Query("SELECT * FROM favouritead")
    List<FavouriteAd> getAll();

    @Insert
    void insertAll(FavouriteAd... favouriteAds);

    @Delete
    void delete(FavouriteAd favouriteAd);
}
