package com.softmiracle.githubmvp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dnsfrolov on 13.04.2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final String[] FRAGMENT_TITLES = {"User", "Repository"};
    private List<Fragment> mFragments;

    public ViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
        super(fragmentManager);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return FRAGMENT_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FRAGMENT_TITLES[position];
    }
}
