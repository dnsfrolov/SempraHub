package com.softmiracle.githubmvp.screen.repo;

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
import com.softmiracle.githubmvp.screen.adapters.RepoViewPagerAdapter;
import com.softmiracle.githubmvp.screen.repo.repoDetail.RepoDetailFragment;
import com.softmiracle.githubmvp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryActivity extends AppCompatActivity {

    @BindView(R.id.apptoolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    public static Intent newIntent(Context context, String owner, String repo) {
        Intent intent = new Intent(context, RepositoryActivity.class);
        intent.putExtra(Constants.EXTRA_USERNAME, owner);
        intent.putExtra(Constants.EXTRA_REPO_ITEM, repo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra(Constants.EXTRA_REPO_ITEM));
        }

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(RepoDetailFragment.newInstance());

        RepoViewPagerAdapter adapter = new RepoViewPagerAdapter(getSupportFragmentManager(), fragments);
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
