package com.softmiracle.githubmvp.screen.repo.repoDetail;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.SempraApplication;
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.screen.starred.repoStarred.RepoStarredActivity;
import com.softmiracle.githubmvp.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepoDetailFragment extends Fragment implements RepoDetailContract.RepoDetailView {

    @BindView(R.id.tv_reponame_repo_detail)
    TextView mRepoName;

    @BindView(R.id.tv_updated_repo_detail)
    TextView mUpdatedAt;

    @BindView(R.id.tv_description_repo_detail)
    TextView mDescription;

    @BindView(R.id.tv_language_repo_detail)
    TextView mLanguage;

    @BindView(R.id.tv_stars_repo_detail)
    TextView mStars;

    @BindView(R.id.tv_forks_repo_detail)
    TextView mForks;

    @BindView(R.id.tv_issues_repo_detail)
    TextView mIssues;

    @BindView(R.id.tv_watchers_repo_detail)
    TextView mWatchers;

    @BindView(R.id.ll_lang_repo_detail)
    LinearLayout mLangLayout;

    private RepoDetailContract.RepoDetailPresenter mPresenter;

    public static RepoDetailFragment newInstance() {
        return new RepoDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_repo_detail, container, false);
        ButterKnife.bind(this, root);

        mPresenter = new RepoDetailPresenterImpl(this);
        loadData();

        return root;
    }

    public void loadData() {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            String mOwner = intent.getStringExtra(Constants.EXTRA_USERNAME);
            String mRepoName = intent.getStringExtra(Constants.EXTRA_REPO_ITEM);

            if (!TextUtils.isEmpty(mOwner) && !TextUtils.isEmpty(mRepoName)) {
                mPresenter.loadRepoDetail(mOwner, mRepoName);
            }
        }
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void hideProgressIndicator() {

    }

    @Override
    public void showRepoDetail(Repo repo) {
        mRepoName.setText(repo.getFullName());
        mUpdatedAt.setText(DateFormat.getDateFormat(getActivity()).format(repo.getUpdatedAt()));
        mStars.setText(repo.getStargazersCount());
        mForks.setText(repo.getForksCount());
        mIssues.setText(repo.getOpenIssuesCount());
        mWatchers.setText(repo.getWatchersCount());

        if (repo.getLanguage() != null) {
            mLanguage.setText(repo.getLanguage());
        } else {
            mLangLayout.setVisibility(View.GONE);
        }
        if (repo.getDescription() != null) {
            mDescription.setText(getResources().getString(R.string.description) + repo.getDescription());
        } else {
            mDescription.setText(SempraApplication.getInstance().getResources().getString(R.string.no_description));
        }
    }

    @Override
    public void showError(Throwable error) {

    }

    @OnClick(R.id.ll_stars_repo_detail)
    public void onRepoStarredClick() {
        startActivity(RepoStarredActivity.newIntent(getContext(),
                getActivity().getIntent().getStringExtra(Constants.EXTRA_USERNAME),
                getActivity().getIntent().getStringExtra(Constants.EXTRA_REPO_ITEM)));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
