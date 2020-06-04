package com.example.routetitanhomeassignment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.routetitanhomeassignment.adapters.MyRecyclerAdapter;
import com.example.routetitanhomeassignment.fragments.MapFragment;
import com.example.routetitanhomeassignment.fragments.StopsFragment;
import com.example.routetitanhomeassignment.ui.main.Adresses;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.routetitanhomeassignment.adapters.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private  SectionsPagerAdapter mSectionPagerAdapter;

    private BroadcastReceiver receiver;

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSectionPagerAdapter = new SectionsPagerAdapter( getSupportFragmentManager());
        TabLayout tabs = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        setuoViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                viewPager.setCurrentItem(1);
            }
        };

       registerReceiver(receiver, new IntentFilter("action.navigate"));

    }

    @Override
    protected void onDestroy() {
        try {
            unregisterReceiver(receiver);
        } catch (Exception ex){
            Log.e("map", ex.getMessage());
        }
        super.onDestroy();
    }

    private void setuoViewPager(ViewPager viewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StopsFragment(), "STOPS "+"("+20+")");
        adapter.addFragment(new MapFragment(),  "MAP" );
        viewPager.setAdapter(adapter);
    }
 /*   public List<Adresses> initAdres( ){
        ListIterator<Adresses> adresslist =iterat
       List<Adresses> adresslist



        return adresslist;
    }*/
}