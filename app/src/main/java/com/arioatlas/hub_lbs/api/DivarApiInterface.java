package com.arioatlas.hub_lbs.api;

import com.arioatlas.hub_lbs.models.Divar.DivarSearchResponse;
import com.arioatlas.hub_lbs.models.Divar.DivarAdvInfo;
import com.arioatlas.hub_lbs.models.Divar.DivarSearchRequest;
import com.arioatlas.hub_lbs.models.Divar.DivarLocationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface DivarApiInterface {

    @Headers("Content-Type: application/json")
    @POST("json")
    Call<DivarSearchResponse> getAds(@Body DivarSearchRequest request);

    @Headers("Content-Type: application/json")
    @GET("v5/posts/{token}")
    Call<DivarAdvInfo> getAdvInfo(@Path("token") String token);

    @GET("v5/places/find")
    Call<DivarLocationResponse> getLocationId(@Query("lat") double latitude, @Query("long") double longitude);


}
