package com.softmiracle.githubmvp.screen.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.screen.adapters.SearchViewPagerAdapter;
import com.softmiracle.githubmvp.screen.settings.SettingsActivity;
import com.softmiracle.githubmvp.utils.prefs.SearchPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.apptoolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private SearchViewPagerAdapter adapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SettingsActivity.getTheme(this, SettingsActivity.THEME_TYPE_GLOBAL));
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.action_search);

            List<Fragment> fragments = new ArrayList<>();
            fragments.add(SearchUserFragment.newInstance());
            fragments.add(SearchRepoFragment.newInstance());

            adapter = new SearchViewPagerAdapter(getSupportFragmentManager(), fragments);
            mViewPager.setOffscreenPageLimit(fragments.size());
            mViewPager.setAdapter(adapter);
            mTabLayout.setupWithViewPager(mViewPager);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        if (item != null) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) item.getActionView();
            if (searchView != null) {
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        SearchPreferences.setSearchResult(query);
                        adapter.notifyDataSetChanged();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });

                searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        finish();
                        return false;
                    }
                });
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        SearchPreferences.removeSearchResult();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        SearchPreferences.removeSearchResult();
        super.onDestroy();
    }
}
