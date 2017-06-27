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
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.presentation.adapters.RepoListAdapter;
import com.softmiracle.githubmvp.presentation.modules.repo.RepositoryActivity;
import com.softmiracle.githubmvp.utils.Constants;
import com.softmiracle.githubmvp.utils.listeners.EndlessRecyclerViewScrollListener;
import com.softmiracle.githubmvp.utils.listeners.OnItemClickListener;
import com.softmiracle.githubmvp.utils.prefs.SearchPreferences;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchRepoFragment extends Fragment implements SearchContract.SearchRepoView, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<Repo> {

    @BindView(R.id.recycler_repo_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_repo)
    SwipeRefreshLayout mRefreshLayout;

    private RepoListAdapter mAdapter;
    private SearchContract.SearchRepoPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private int page = 1;

    public static SearchRepoFragment newInstance() {
        return new SearchRepoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_repo, container, false);
        ButterKnife.bind(this, root);

        setLayoutManager();
        setAdapter();

        mPresenter = new SearchPresenterImpl(this);
        mPresenter.loadRepoResult(SearchPreferences.getSearchResult(), page);

        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount) {
                mPresenter.loadRepoResult(SearchPreferences.getSearchResult(), ++currentPage);
            }
        });

        return root;
    }

    public void setAdapter() {
        mAdapter = new RepoListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setLayoutManager() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void hideProgressIndicator() {

    }


    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void onRefresh() {
        mAdapter.restoreData();
        mAdapter.notifyDataSetChanged();
        mPresenter.loadRepoResult(SearchPreferences.getSearchResult(), page);
    }

    @Override
    public void onItemClick(Repo item) {
        startActivity(new Intent(RepositoryActivity.newIntent(getContext(), item.getOwner().getLogin(), item.getName())));
    }

    @Override
    public void showRepoResult(List<Repo> repoList) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setData(repoList);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachRepoView();
    }
}
