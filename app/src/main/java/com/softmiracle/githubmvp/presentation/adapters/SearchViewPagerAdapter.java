package com.softmiracle.githubmvp.presentation.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.softmiracle.githubmvp.utils.Constants;

import java.util.List;

/**
 * Created by dnsfrolov on 08.05.2017.
 */

public class SearchViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public SearchViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
        super(fragmentManager);
        mFragments = fragments;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return Constants.SEARCH_FRAGMENT_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.SEARCH_FRAGMENT_TITLES[position];
    }
}
