package com.example.routetitanhomeassignment.api;


import java.util.stream.Collector;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DirectionApi {

    @GET("directions/json")
    Call<Direction> getDirections(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("key")String key
    );
}