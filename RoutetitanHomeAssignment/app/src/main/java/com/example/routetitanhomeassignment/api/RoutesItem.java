package com.example.routetitanhomeassignment.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoutesItem {
    @SerializedName("summary")
    public String summary ="";
    @SerializedName("copyrights")
    public String copyrights ="";
    @SerializedName("legs")
    public List<LegsItem> legs;
    @SerializedName("bounds")
    public Bounds bounds;
    @SerializedName("overview_polyline")
    public OverviewPolyline overviewPolyline;


}
