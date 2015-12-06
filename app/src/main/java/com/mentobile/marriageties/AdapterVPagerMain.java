package com.mentobile.marriageties;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by user on 10/18/2015.
 */
public class AdapterVPagerMain extends FragmentPagerAdapter {
    private final int numOfTabs;

    public AdapterVPagerMain(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("Position ", ":::::::::Pos " + position);
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new FragmentMatchProfile();
                return fragment;
            case 1:
                fragment = new FragmentShortListed();
                return fragment;
            case 2:
            default:
                fragment = new FragmentBlockProfile();
                return fragment;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
