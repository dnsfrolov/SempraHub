package com.softmiracle.githubmvp.presentation.modules.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.presentation.adapters.UserListAdapter;
import com.softmiracle.githubmvp.presentation.modules.user.ProfilePagerActivity;
import com.softmiracle.githubmvp.utils.Constants;
import com.softmiracle.githubmvp.utils.listeners.EndlessRecyclerViewScrollListener;
import com.softmiracle.githubmvp.utils.listeners.OnItemClickListener;
import com.softmiracle.githubmvp.utils.prefs.SearchPreferences;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchUserFragment extends Fragment implements SearchContract.SearchUserView, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<User> {

    @BindView(R.id.recycler_user_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_user)
    SwipeRefreshLayout mRefreshLayout;

    private UserListAdapter mAdapter;
    private SearchContract.SearchUserPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private int page = 1;

    public static SearchUserFragment newInstance() {
        return new SearchUserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_user, container, false);
        ButterKnife.bind(this, root);

        setLayoutManager();
        setAdapter();

        mPresenter = new SearchPresenterImpl(this);
        mPresenter.loadUserResult(SearchPreferences.getSearchResult(), page);

        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount) {
                mPresenter.loadUserResult(SearchPreferences.getSearchResult(), ++currentPage);
            }
        });

        return root;
    }

    public void setAdapter() {
        mAdapter = new UserListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setLayoutManager() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onRefresh() {
        mAdapter.restoreData();
        mAdapter.notifyDataSetChanged();
        mPresenter.loadUserResult(SearchPreferences.getSearchResult(), page);
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void hideProgressIndicator() {

    }

    @Override
    public void showUserResult(List<User> userList) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setData(userList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachUserView();
    }

    @Override
    public void onItemClick(User item) {
        startActivity(new Intent(ProfilePagerActivity.newIntent(getContext(), item.getLogin())));
    }
}
