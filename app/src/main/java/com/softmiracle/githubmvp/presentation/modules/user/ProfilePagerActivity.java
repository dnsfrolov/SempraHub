package com.softmiracle.githubmvp.presentation.modules.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.presentation.adapters.ProfileViewPagerAdapter;
import com.softmiracle.githubmvp.presentation.modules.repo.RepoListFragment;
import com.softmiracle.githubmvp.presentation.modules.settings.SettingsActivity;
import com.softmiracle.githubmvp.presentation.modules.starred.userStarred.UserStarredListFragment;
import com.softmiracle.githubmvp.presentation.modules.user.detail.UserFragment;
import com.softmiracle.githubmvp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilePagerActivity extends AppCompatActivity {

    @BindView(R.id.apptoolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    public static Intent newIntent(Context context, String login) {
        Intent intent = new Intent(context, ProfilePagerActivity.class);
        intent.putExtra(Constants.EXTRA_USERNAME, login);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SettingsActivity.getTheme(this, SettingsActivity.THEME_TYPE_GLOBAL));
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra(Constants.EXTRA_USERNAME));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}