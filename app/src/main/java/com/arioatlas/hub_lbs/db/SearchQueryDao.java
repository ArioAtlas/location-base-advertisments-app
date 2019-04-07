package com.arioatlas.hub_lbs.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SearchQueryDao {
    @Query("SELECT * FROM searchquery")
    List<SearchQuery> getAll();

    @Insert
    void insertAll(SearchQuery... searchQueries);

    @Delete
    void delete(SearchQuery searchQuery);
}
