package com.example.routetitanhomeassignment.api;

import com.google.gson.annotations.SerializedName;

public class Bounds {
    @SerializedName("southwest")
    public Southwest southwest;
    @SerializedName("northeast")
    public Northeast northeast;
}
