package com.example.happsapp2.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> firstFrag  = new ArrayList<>();
    private final List<String> firstTitles  = new ArrayList<>();
    private final List<Fragment> secondFrag = new ArrayList<>();
    private final List<Fragment> thirdFrag  = new ArrayList<>();
    private final List<Fragment> fourthFrag = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return firstFrag.get(position);
    }

    @Override
    public int getCount() {
        return firstTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return firstTitles.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        firstFrag.add(fragment);
        firstTitles.add(title);
    }
}
