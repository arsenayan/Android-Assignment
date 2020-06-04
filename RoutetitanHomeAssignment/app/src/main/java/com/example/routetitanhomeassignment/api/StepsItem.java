package com.example.routetitanhomeassignment.api;

import com.google.gson.annotations.SerializedName;

public class StepsItem {
    @SerializedName("start_location")
    public StartLocation startLocation;
    @SerializedName("end_location")
    public EndLocation endLocation;
    @SerializedName("polyline")
    public Polyline polyline;
}
