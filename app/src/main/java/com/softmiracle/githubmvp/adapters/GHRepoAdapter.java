package com.softmiracle.githubmvp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.data.models.GHRepo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dnsfrolov on 09.03.2017.
 */

public class GHRepoAdapter extends RecyclerView.Adapter {

    private List<GHRepo> mList;

    public GHRepoAdapter() {
        mList = new ArrayList<>();
    }

    public void setData(List<GHRepo> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_repo, parent, false);
        return new GHRepoViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final GHRepoViewHolder repoViewHolder = (GHRepoViewHolder) holder;
        repoViewHolder.initData(holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    public class GHRepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_repo_name)
        TextView mRepoName;
        @BindView(R.id.tv_repo_description)
        TextView mRepoDescription;
        @BindView(R.id.tv_repo_language)
        TextView mRepoLanguage;

        public GHRepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void initData(int adapterPosition) {
            final GHRepo ghRepo = mList.get(adapterPosition);
            mRepoName.setText("name: " + ghRepo.getName());
            mRepoDescription.setText("description: " + ghRepo.getDescription());
            mRepoLanguage.setText("language: " + ghRepo.getLanguage());
        }
    }
}
