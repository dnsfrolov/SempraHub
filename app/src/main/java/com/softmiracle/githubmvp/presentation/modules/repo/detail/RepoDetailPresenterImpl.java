package com.softmiracle.githubmvp.presentation.modules.repo.detail;

import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.RepoDetailInteractor;
import com.softmiracle.githubmvp.data.interactor.impl.RepoDetailInteractorImpl;
import com.softmiracle.githubmvp.data.models.Repo;

/**
 * Created by dnsfrolov on 29.04.2017.
 */

public class RepoDetailPresenterImpl implements RepoDetailContract.RepoDetailPresenter {

    private RepoDetailContract.RepoDetailView mRepoView;
    private RepoDetailInteractor mRepoInteractor;

    public RepoDetailPresenterImpl(RepoDetailContract.RepoDetailView repoView) {
        this.mRepoView = repoView;
        mRepoInteractor = new RepoDetailInteractorImpl();
    }

    @Override
    public void loadRepoDetail(String owner, String repo) {
        if (mRepoView != null) {
            mRepoView.showProgressIndicator();
        }

        mRepoInteractor.getRepoDetail(owner, repo, new InteractorCallback<Repo>() {
            @Override
            public void onSuccess(Repo response) {
                if (response != null) {
                    mRepoView.showRepoDetail(response);
                    mRepoView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mRepoView.showError(error);
                mRepoView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        mRepoView = null;
    }
}
