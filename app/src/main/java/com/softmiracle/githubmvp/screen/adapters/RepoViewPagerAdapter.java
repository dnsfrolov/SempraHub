package com.softmiracle.githubmvp.screen.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.softmiracle.githubmvp.utils.Constants;

import java.util.List;

/**
 * Created by dnsfrolov on 29.04.2017.
 */

public class RepoViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public RepoViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return Constants.REPOSITORY_FRAGMENT_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.REPOSITORY_FRAGMENT_TITLES[position];
    }
}
