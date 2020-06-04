package com.example.routetitanhomeassignment.api;

import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static final String GOOGLE_BASE_URL = "https://maps.googleapis.com/maps/api/";

    public DirectionApi googleMethods() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestClient.GOOGLE_BASE_URL)
                .client(new OkHttpClient().newBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(DirectionApi.class);
    }


}
