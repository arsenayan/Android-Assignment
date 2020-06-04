package com.example.routetitanhomeassignment.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Direction {
    @SerializedName("routes")
    public List<RoutesItem> routes;

    @SerializedName("status")
    public String status;
}
