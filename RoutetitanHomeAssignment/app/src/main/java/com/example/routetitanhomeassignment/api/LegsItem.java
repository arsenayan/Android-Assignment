package com.example.routetitanhomeassignment.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LegsItem {
    @SerializedName("start_location")
     public StartLocation startLocation;

    @SerializedName("start_address")
    public String startAddress= "";
    @SerializedName("end_location")
    public EndLocation endLocation;
    @SerializedName("end_address")
    public   String endAddress= "";
    @SerializedName("steps")
    public List<StepsItem> steps;
}
