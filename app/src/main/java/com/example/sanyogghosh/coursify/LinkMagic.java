package com.example.sanyogghosh.coursify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class LinkMagic extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public LinkMagic(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                Recommended tab0 = new Recommended();
                return tab0;
            case 1:
                First tab1 = new First();
                return tab1;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}