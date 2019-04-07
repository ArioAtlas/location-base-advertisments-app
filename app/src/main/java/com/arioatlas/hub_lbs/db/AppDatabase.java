package com.arioatlas.hub_lbs.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SearchQuery.class,FavouriteAd.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SearchQueryDao searchQueryDao();
    public abstract FavouriteAdDao favouriteAdDao();
}
