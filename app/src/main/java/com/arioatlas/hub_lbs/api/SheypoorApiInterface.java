package com.arioatlas.hub_lbs.api;

import com.arioatlas.hub_lbs.models.Sheypoor.SheypoorLocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SheypoorApiInterface {

    @GET("api/web/geo")
    Call<SheypoorLocationResponse> getLocationId(@Query("latitude") double latitude, @Query("longitude") double longitude);

}
