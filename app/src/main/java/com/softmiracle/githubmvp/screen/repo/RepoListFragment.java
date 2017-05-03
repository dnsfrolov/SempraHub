package com.softmiracle.githubmvp.screen.repo;

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
import com.softmiracle.githubmvp.screen.adapters.RepoListAdapter;
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.utils.Constants;
import com.softmiracle.githubmvp.utils.EndlessRecyclerViewScrollListener;
import com.softmiracle.githubmvp.utils.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListFragment extends Fragment implements RepoContract.RepoView, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<Repo> {

    @BindView(R.id.recycler_repo_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_repo)
    SwipeRefreshLayout mRefreshLayout;

    private RepoListAdapter mAdapter;
    private RepoContract.RepoPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;

    public static RepoListFragment newInstance() {
        return new RepoListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, root);

        setLayoutManager();
        setAdapter();

        mPresenter = new RepoPresenterImpl(this);
        mPresenter.loadRepo(getActivity().getIntent().getStringExtra(Constants.EXTRA_USERNAME), Constants.PAGE);

        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount) {
                mPresenter.loadRepo(getActivity().getIntent().getStringExtra(Constants.EXTRA_USERNAME), ++currentPage);
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
    public void showRepo(List<Repo> repoList) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setData(repoList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable error) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onRefresh() {
        mAdapter.restoreData();
        mAdapter.notifyDataSetChanged();
        mPresenter.loadRepo(getActivity().getIntent().getStringExtra(Constants.EXTRA_USERNAME), Constants.PAGE);
    }

    @Override
    public void onItemClick(Repo item) {
        startActivity(new Intent(RepositoryActivity.newIntent(getContext(), item.getOwner().getLogin(), item.getName())));
    }
}
