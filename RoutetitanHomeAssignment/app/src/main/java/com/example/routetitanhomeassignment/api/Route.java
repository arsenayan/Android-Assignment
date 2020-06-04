package com.example.routetitanhomeassignment.api;

public class Route {
    public String startName;
    public String endName;
    public double startLat;
    public double startLng;
    public double endLat;
    public double endLng;
    public String overviewPolyline;

    public Route(String startName, String endName, double startLat, double startLng, double endLat, double endLng, String overviewPolyline) {
        this.startName = startName;
        this.endName = endName;
        this.startLat = startLat;
        this.startLng = startLng;
        this.endLat = endLat;
        this.endLng = endLng;
        this.overviewPolyline = overviewPolyline;
    }
}
