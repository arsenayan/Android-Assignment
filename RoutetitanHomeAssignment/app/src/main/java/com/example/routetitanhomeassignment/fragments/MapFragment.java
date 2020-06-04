package com.example.routetitanhomeassignment.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.maps.android.PolyUtil;

import com.example.routetitanhomeassignment.R;
import com.example.routetitanhomeassignment.adapters.MyRecyclerAdapter;
import com.example.routetitanhomeassignment.api.Direction;
import com.example.routetitanhomeassignment.api.LegsItem;
import com.example.routetitanhomeassignment.api.RestClient;
import com.example.routetitanhomeassignment.api.Route;
import com.example.routetitanhomeassignment.ui.main.Adresses;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MapFragment extends ParentFragment implements OnMapReadyCallback, LocationListener {
    GoogleMap googleMap;
    static final String MAP = "map";
    private BroadcastReceiver receiver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String destinatioon = intent.getStringExtra("destionation");
                drawRout(destinatioon);
                Log.d(MAP, destinatioon);
            }
        };
        getActivity().registerReceiver(receiver, new IntentFilter("action.navigate"));

    }

    private void drawRout(String destination) {
        new RestClient().googleMethods().getDirections("40.24646939984506,44.42144074985102", destination, getString(R.string.google_app_maps_api_key))
                .enqueue(new Callback<Direction>() {
                    @Override
                    public void onResponse(Call<Direction> call, Response<Direction> response) {
                        Direction directions = response.body();
                        if (directions == null) {
                            return;
                        }

                        if (directions.status.equals("OK")) {
                            LegsItem legs = directions.routes.get(0).legs.get(0);
                            Route route = new Route("getString(R.string.time_square)", "getString(R.string.chelsea_market)",
                                    legs.startLocation.lat, legs.startLocation.lng, legs.endLocation.lat, legs.endLocation.lng, directions.routes.get(0).overviewPolyline.points);
                            setMarkersAndRoute(route);
                        } Log.d("map", "onResponse");
                    }

                    @Override
                    public void onFailure(Call<Direction> call, Throwable t) {
                        Log.d("map", "onFailure");
                    }
                });
    }

    private void setMarkersAndRoute(Route route){
        LatLng startLatLng = new LatLng(route.startLat, route.startLng);
        MarkerOptions startMarkerOptions  = new MarkerOptions().position(startLatLng)
                .title(route.startName)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED));
        LatLng endLatLng = new LatLng(route.endLat, route.endLng);
        MarkerOptions endMarkerOptions = new MarkerOptions().position(endLatLng)
                .title(route.endName)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        Marker  startMarker = googleMap.addMarker(startMarkerOptions);
        Marker endMarker = googleMap.addMarker(endMarkerOptions);

        PolylineOptions polylineOptions = drawRoute();
        List<LatLng> pointsList = PolyUtil.decode(route.overviewPolyline);
        for (LatLng latLng : pointsList) {
            polylineOptions.add(latLng);
        }        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(endLatLng, 13));



        googleMap.addPolyline(polylineOptions);
    }
    private PolylineOptions drawRoute()  {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.width(getResources().getDimension(R.dimen.route_line_width));
        polylineOptions.geodesic(true);
        polylineOptions.color(getResources().getColor(R.color.colorDark, getActivity().getTheme()));
        return polylineOptions;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onDestroy() {
        try {
            getActivity().unregisterReceiver(receiver);

        } catch (Exception ex) {
            Log.e(MAP, ex.getMessage());
        }
        super.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        // List<Address> addressList = new ArrayList<>();
        //    LatLng pp = new LatLng( addressList.get(1).getLatitude(),addressList.get(1).getLongitude());
//        LatLng pp = new LatLng( 40.18182489088315,44.52117313638205);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(pp);
//        googleMap.addMarker(markerOptions);
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pp));

        for (Adresses add : addressesList) {
            LatLng pp = new LatLng(add.getLatitude(), add.getLongitude());
            LatLng myLatLang = new LatLng(40.198781, 44.491329);
            MarkerOptions markerOptions = new MarkerOptions();

            Marker melbourne = googleMap.addMarker(new MarkerOptions().position(pp)
                    .title(add.getStreet())
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        }

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(addressesList.get(0).getLatitude(), addressesList.get(0).getLongitude()), 13));

    }

    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

