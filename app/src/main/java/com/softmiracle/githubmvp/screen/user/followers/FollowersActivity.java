package com.softmiracle.githubmvp.screen.user.followers;

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
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.screen.adapters.FollowersAdapter;
import com.softmiracle.githubmvp.utils.Constants;
import com.softmiracle.githubmvp.utils.EndlessRecyclerViewScrollListener;
import com.softmiracle.githubmvp.utils.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowersActivity extends AppCompatActivity implements FollowersContract.FollowersView, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<User> {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_followers_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_followers)
    SwipeRefreshLayout mRefreshLayout;

    private FollowersAdapter mAdapter;
    private FollowersContract.FollowersPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;

    public static Intent newIntent(Context context, String login) {
        Intent intent = new Intent(context, FollowersActivity.class);
        intent.putExtra(Constants.EXTRA_USERNAME, login);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setLayoutManager();
        setAdapter();

        mPresenter = new FollowersPresenterImpl(this);
        mPresenter.loadFollowers(getIntent().getStringExtra(Constants.EXTRA_USERNAME), Constants.PAGE);

        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount) {
                mPresenter.loadFollowers(getIntent().getStringExtra(Constants.EXTRA_USERNAME), ++currentPage);
            }
        });
    }

    public void setAdapter() {
        mAdapter = new FollowersAdapter(this);
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
        mAdapter.restoreData();
        mAdapter.notifyDataSetChanged();
        mPresenter.loadFollowers(getIntent().getStringExtra(Constants.EXTRA_USERNAME), Constants.PAGE);
    }

    @Override
    public void onItemClick(User item) {

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
        mAdapter.setData(userList);
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
