package com.softmiracle.githubmvp.presentation.modules.user.following;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.common.SempraApplication;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.presentation.adapters.UserList;
import com.softmiracle.githubmvp.presentation.adapters.UserListAdapter;
import com.softmiracle.githubmvp.presentation.modules.settings.SettingsActivity;
import com.softmiracle.githubmvp.presentation.modules.user.ProfilePagerActivity;
import com.softmiracle.githubmvp.utils.Constants;
import com.softmiracle.githubmvp.utils.listeners.EndlessRecyclerViewScrollListener;
import com.softmiracle.githubmvp.utils.listeners.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowingActivity extends AppCompatActivity implements FollowingContract.FollowingView, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<User> {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_following_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_following_list)
    SwipeRefreshLayout mRefreshLayout;

    private UserList mAdapter;
    private FollowingContract.FollowingPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private int page = 1;

    public static Intent newIntent(Context context, String login) {
        Intent intent = new Intent(context, FollowingActivity.class);
        intent.putExtra(Constants.EXTRA_USERNAME, login);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SettingsActivity.getTheme(this, SettingsActivity.THEME_TYPE_GLOBAL));
        setContentView(R.layout.activity_following);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.user_fragment_following);
        }

        setLayoutManager();
        setAdapter();

        mPresenter = new FollowingPresenterImpl(this);
        mPresenter.loadFollowing(getIntent().getStringExtra(Constants.EXTRA_USERNAME), page);

        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount) {
                mPresenter.loadFollowing(getIntent().getStringExtra(Constants.EXTRA_USERNAME), ++currentPage);
            }
        });
    }

    public void setAdapter() {
        //mAdapter = new UserListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setLayoutManager() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onRefresh() {
        mAdapter.notifyDataSetChanged();
        mPresenter.loadFollowing(getIntent().getStringExtra(Constants.EXTRA_USERNAME), page);
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
    public void showFollowing(List<User> userList) {
        mRefreshLayout.setRefreshing(false);
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
}
