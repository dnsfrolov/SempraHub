package com.softmiracle.githubmvp.screen.repo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.adapters.RepoListAdapter;
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.utils.AccountPreferences;
import com.softmiracle.githubmvp.utils.EndlessRecyclerViewScrollListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListFragment extends Fragment implements RepoView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_repo_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_repo)
    SwipeRefreshLayout refreshLayout;
    private RepoListAdapter mAdapter;

    private RepoPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;

    private static final int PAGE = 1;

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
        mPresenter.loadRepo(AccountPreferences.getUsername(), PAGE);

        refreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount) {
                mPresenter.loadRepo(AccountPreferences.getUsername(), ++currentPage);
            }
        });
        return root;
    }

    public void setAdapter() {
        mAdapter = new RepoListAdapter();
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
        refreshLayout.setRefreshing(false);
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
        mPresenter.loadRepo("k0shk0sh", PAGE);
    }
}
