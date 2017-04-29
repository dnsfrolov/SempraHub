package com.softmiracle.githubmvp.screen.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.screen.adapters.ProfileViewPagerAdapter;
import com.softmiracle.githubmvp.screen.repo.RepoListFragment;
import com.softmiracle.githubmvp.screen.starred.userStarred.UserStarredListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.apptoolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(UserFragment.newInstance());
        fragments.add(RepoListFragment.newInstance());
        fragments.add(UserStarredListFragment.newInstance());

        ProfileViewPagerAdapter adapter = new ProfileViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}