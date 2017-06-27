package com.softmiracle.githubmvp.presentation.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.common.SempraApplication;
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.utils.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dnsfrolov on 17.04.2017.
 */

public class RepoListAdapter extends RecyclerView.Adapter {

    private List<Repo> mList;
    private OnItemClickListener<Repo> mListener;

    public RepoListAdapter(OnItemClickListener<Repo> listener) {
        this.mList = new ArrayList<>();
        this.mListener = listener;
    }

    public void setData(List<Repo> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.addAll(list);
    }

    public void restoreData() {
        mList = null;
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repos_item_list, parent, false);
        return new RepoListHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final RepoListHolder repoListHolder = (RepoListHolder) holder;
        repoListHolder.initData(holder.getAdapterPosition());

        repoListHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(mList.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    class RepoListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardview_repo_list)
        CardView mCardView;

        @BindView(R.id.avatar_fork_repo_list)
        ImageView mAvatarFork;

        @BindView(R.id.avatar_repo_list)
        ImageView mAvatarRepo;

        @BindView(R.id.tv_full_name_repo_list)
        TextView mFullName;

        @BindView(R.id.tv_updated_repo_list)
        TextView mUpdatedAt;

        @BindView(R.id.tv_description_repo_list)
        TextView mDescription;

        @BindView(R.id.tv_language_repo_list)
        TextView mLanguage;

        @BindView(R.id.tv_forks_repo_list)
        TextView mForks;

        @BindView(R.id.tv_stars_repo_list)
        TextView mStars;

        @BindView(R.id.ll_lang_repo_list)
        LinearLayout mLangLayout;

        RepoListHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void initData(int adapterPosition) {
            final Repo repo = mList.get(adapterPosition);
            if (!repo.isFork()) {
                mAvatarFork.setVisibility(View.GONE);
                mAvatarRepo.setVisibility(View.VISIBLE);
            }
            mFullName.setText(repo.getFullName());
            mUpdatedAt.setText(String.format("%s %s", SempraApplication.getInstance().getResources().getString(R.string.updated), DateFormat.getMediumDateFormat(SempraApplication.getInstance()).format(repo.getUpdatedAt())));
            if (repo.getLanguage() != null) {
                mLanguage.setText(repo.getLanguage());
            } else {
                mLangLayout.setVisibility(View.GONE);
            }
            mForks.setText(repo.getForksCount());
            mStars.setText(repo.getStargazersCount());

            if (repo.getDescription() != null) {
                mDescription.setText(String.format("%s %s", SempraApplication.getInstance().getResources().getString(R.string.description), repo.getDescription()));
            } else {
                mDescription.setText(SempraApplication.getInstance().getResources().getString(R.string.no_description));
            }
        }
    }
}