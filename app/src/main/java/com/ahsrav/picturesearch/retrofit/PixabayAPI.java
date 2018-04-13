package com.ahsrav.picturesearch.retrofit;

import com.ahsrav.picturesearch.models.ImageList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface PixabayAPI {
    @GET("api")
    Call<ImageList> baseApiCall(@QueryMap Map<String, String> parameter);
}
