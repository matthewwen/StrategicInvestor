package com.strategicinvestor.strategicinvestor.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Person;

public class FragmentPageAdapter extends FragmentPagerAdapter {

    public static final String[] HEADINGS = new String[]{"Companies", "Strategies", "Personal"};

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return CompaniesFragment.newInstance();
            case 1: return StrategiesFragment.newInstance();
        }
        return PersonalFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return HEADINGS[position];
    }
}
