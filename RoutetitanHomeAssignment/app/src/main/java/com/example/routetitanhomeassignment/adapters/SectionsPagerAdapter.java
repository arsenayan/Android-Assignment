package com.example.routetitanhomeassignment.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.routetitanhomeassignment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to one of the
 * sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final List<Fragment> fragmentList= new ArrayList<>();
    private final List<String> fragmentTitleList= new ArrayList<>();


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
   }
    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
     }

    @Override
    public int getCount() {
         return 2;
    }
    public void addFragment(Fragment fragment,String title){
        fragmentList.add(fragment);
        fragmentTitleList.add(title);

    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);    }


}