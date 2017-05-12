package com.softmiracle.githubmvp.screen.starred.repoStarred;

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
import com.softmiracle.githubmvp.SempraApplication;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.screen.adapters.UserListAdapter;
import com.softmiracle.githubmvp.screen.user.ProfileActivity;
import com.softmiracle.githubmvp.utils.Constants;
import com.softmiracle.githubmvp.utils.EndlessRecyclerViewScrollListener;
import com.softmiracle.githubmvp.utils.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoStarredActivity extends AppCompatActivity implements RepoStarredContract.RepoStarredView,
        SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<User> {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_repo_starred)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_repo_starred)
    SwipeRefreshLayout refreshLayout;

    private UserListAdapter mAdapter;
    private RepoStarredContract.RepoStarredPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;

    public static Intent newIntent(Context context, String login, String repo) {
        Intent intent = new Intent(context, RepoStarredActivity.class);
        intent.putExtra(Constants.EXTRA_USERNAME, login);
        intent.putExtra(Constants.EXTRA_REPO_ITEM, repo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_starred);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.stars);
        }

        setLayoutManager();
        setAdapter();

        mPresenter = new RepoStarredPresenterImpl(this);
        mPresenter.loadRepoStarred(getIntent().getStringExtra(Constants.EXTRA_USERNAME),
                getIntent().getStringExtra(Constants.EXTRA_REPO_ITEM), Constants.PAGE);

        refreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount) {
                mPresenter.loadRepoStarred(getIntent().getStringExtra(Constants.EXTRA_USERNAME),
                        getIntent().getStringExtra(Constants.EXTRA_REPO_ITEM), ++currentPage);
            }
        });
    }

    public void setAdapter() {
        mAdapter = new UserListAdapter(this);
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
        mPresenter.loadRepoStarred(getIntent().getStringExtra(Constants.EXTRA_USERNAME),
                getIntent().getStringExtra(Constants.EXTRA_REPO_ITEM), Constants.PAGE);
    }

    @Override
    public void onItemClick(User item) {
        startActivity(new Intent(ProfileActivity.newIntent(SempraApplication.getInstance(), item.getLogin())));
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void hideProgressIndicator() {

    }

    @Override
    public void showRepoStarred(List<User> userList) {
        refreshLayout.setRefreshing(false);
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
