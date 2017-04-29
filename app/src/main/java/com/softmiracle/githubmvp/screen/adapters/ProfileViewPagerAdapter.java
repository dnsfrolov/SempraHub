package com.softmiracle.githubmvp.screen.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.softmiracle.githubmvp.utils.Constants;

import java.util.List;

/**
 * Created by dnsfrolov on 13.04.2017.
 */

public class ProfileViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public ProfileViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
        super(fragmentManager);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return Constants.PROFILE_FRAGMENT_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.PROFILE_FRAGMENT_TITLES[position];
    }
}
