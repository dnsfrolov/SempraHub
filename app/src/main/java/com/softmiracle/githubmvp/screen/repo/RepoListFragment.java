package com.softmiracle.githubmvp.screen.repo;

import android.os.Bundle;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListFragment extends Fragment implements RepoView {

    @BindView(R.id.recycler_repo_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_repo)
    SwipeRefreshLayout refreshLayout;

    private RepoListAdapter mAdapter;
    private RepoPresenter mPresenter;
    private LinearLayoutManager layoutManager;

    public static RepoListFragment newInstance() {
        return new RepoListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, root);

        setAdapter();

        mPresenter = new RepoPresenterImpl(this);
        mPresenter.loadRepo(AccountPreferences.getUsername(), 1);

        return root;
    }

    public void setAdapter() {
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RepoListAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showProgressIndicator() {
        Toast.makeText(getActivity(), "show", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressIndicator() {
        Toast.makeText(getActivity(), "hide", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRepo(List<Repo> repoList) {
        mAdapter.setData(repoList);
        mAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(getActivity(), R.string.error_sign_out, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
