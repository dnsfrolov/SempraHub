package com.softmiracle.githubmvp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.data.models.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dnsfrolov on 09.03.2017.
 */

public class RepoAdapter extends RecyclerView.Adapter {

    private List<Repo> mList;

    public RepoAdapter() {
        mList = new ArrayList<>();
    }

    public void setData(List<Repo> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_repo, parent, false);
        return new RepoViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final RepoViewHolder repoViewHolder = (RepoViewHolder) holder;
        repoViewHolder.initData(holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    public class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_repo_name)
        TextView mRepoName;
        @BindView(R.id.tv_repo_description)
        TextView mRepoDescription;
        @BindView(R.id.tv_repo_language)
        TextView mRepoLanguage;

        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void initData(int adapterPosition) {
            final Repo ghRepo = mList.get(adapterPosition);
            mRepoName.setText("name: " + ghRepo.getName());
            mRepoDescription.setText("description: " + ghRepo.getDescription());
            mRepoLanguage.setText("language: " + ghRepo.getLanguage());
        }
    }
}
