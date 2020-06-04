package com.example.routetitanhomeassignment.fragments;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.routetitanhomeassignment.R;
import com.example.routetitanhomeassignment.adapters.MyRecyclerAdapter;
import com.example.routetitanhomeassignment.ui.main.Adresses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StopsFragment extends ParentFragment implements MyRecyclerAdapter.AddresClickListener {

    private String []st_estimated_time_arrival;
    private String []st_deliver;
    RecyclerView recyclerView;
    List<Adresses> adressesList= new ArrayList<>();
    private  Random random= new Random();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_stops,container,false);
        recyclerView=view.findViewById(R.id.recyclerView);
        st_estimated_time_arrival= getResources().getStringArray(R.array.estimated_time_arrival);
        st_deliver= getResources().getStringArray(R.array.delivery_time);
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(getActivity(), st_estimated_time_arrival, st_deliver, addressesList , this);
        recyclerView.setAdapter(myRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onClicked(Adresses adresses) {
        Intent intent = new Intent();
        intent.putExtra("destionation", adresses.getLatitude() + "," +adresses.getLongitude());
        intent.setAction("action.navigate");
        getActivity().sendBroadcast(intent);
    }
}
