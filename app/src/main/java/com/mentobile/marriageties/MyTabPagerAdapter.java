package com.mentobile.marriageties;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by user on 10/18/2015.
 */
public class MyTabPagerAdapter extends FragmentStatePagerAdapter {
    private final int numOfTabs;

    public MyTabPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("TabsPageAdapter ", ":::::Adapter " + position);
        switch (position) {
            case 0:
                FragmentQuickSearch quickSearch = new FragmentQuickSearch();
                return quickSearch;
            case 1:
                FragmentAdvanceSearch fragmentAdvanceSearch = new FragmentAdvanceSearch();
                return fragmentAdvanceSearch;
            case 2:
                FragmentKeywordSearch keywordSearch = new FragmentKeywordSearch();
                return keywordSearch;
            case 3:
                FragmentIdSearch fragmentIdSearch = new FragmentIdSearch();
                return fragmentIdSearch;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
