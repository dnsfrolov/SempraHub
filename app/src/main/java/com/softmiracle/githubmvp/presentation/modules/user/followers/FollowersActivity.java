package com.softmiracle.githubmvp.presentation.modules.user.followers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.common.SempraApplication;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.presentation.adapters.UserList;
import com.softmiracle.githubmvp.presentation.modules.settings.SettingsActivity;
import com.softmiracle.githubmvp.presentation.modules.user.ProfilePagerActivity;
import com.softmiracle.githubmvp.utils.Constants;
import com.softmiracle.githubmvp.utils.listeners.EndlessRecyclerViewScrollListener;
import com.softmiracle.githubmvp.utils.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowersActivity extends AppCompatActivity implements FollowersContract.FollowersView, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<User>, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_followers_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_followers)
    SwipeRefreshLayout mRefreshLayout;

    private UserList mAdapter;
    private FollowersContract.FollowersPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;

    private int mPage = 1;

    public static Intent newIntent(Context context, String login) {
        Intent intent = new Intent(context, FollowersActivity.class);
        intent.putExtra(Constants.EXTRA_USERNAME, login);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SettingsActivity.getTheme(this, SettingsActivity.THEME_TYPE_GLOBAL));
        setContentView(R.layout.activity_followers);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.user_fragment_followers);
        }

        setLayoutManager();

        mPresenter = new FollowersPresenterImpl(this);
        mPresenter.loadFollowers(getIntent().getStringExtra(Constants.EXTRA_USERNAME), mPage);

        mRefreshLayout.setOnRefreshListener(this);
    }

    public void setLayoutManager() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadFollowers(getIntent().getStringExtra(Constants.EXTRA_USERNAME), mPage);
    }

    @Override
    public void onItemClick(User item) {
        startActivity(new Intent(ProfilePagerActivity.newIntent(SempraApplication.getInstance(), item.getLogin())));
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void hideProgressIndicator() {

    }

    @Override
    public void showFollowers(List<User> userList) {
        mRefreshLayout.setRefreshing(false);
        mAdapter = new UserList(userList, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadFollowers(getIntent().getStringExtra(Constants.EXTRA_USERNAME), ++mPage);
        mAdapter.loadMoreEnd();
    }
}
