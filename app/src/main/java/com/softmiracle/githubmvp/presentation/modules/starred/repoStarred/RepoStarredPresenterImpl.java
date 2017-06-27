package com.softmiracle.githubmvp.presentation.modules.starred.repoStarred;

import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.RepoStarredInteractor;
import com.softmiracle.githubmvp.data.interactor.impl.RepoStarredInteractorImpl;
import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 02.05.2017.
 */

public class RepoStarredPresenterImpl implements RepoStarredContract.RepoStarredPresenter {

    private RepoStarredContract.RepoStarredView mRepoStarredView;
    private RepoStarredInteractor mRepoStarredInteractor;

    public RepoStarredPresenterImpl(RepoStarredContract.RepoStarredView repoStarredView) {
        this.mRepoStarredView = repoStarredView;
        this.mRepoStarredInteractor = new RepoStarredInteractorImpl();
    }

    @Override
    public void loadRepoStarred(String name, String repo, int page) {
        if (mRepoStarredView != null) {
            mRepoStarredView.showProgressIndicator();
        }

        mRepoStarredInteractor.getRepoStarred(name, repo, page, new InteractorCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> response) {
                if (response != null && response.size() > 0) {
                    mRepoStarredView.showRepoStarred(response);
                    mRepoStarredView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mRepoStarredView.showError(error);
                mRepoStarredView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        mRepoStarredView = null;
    }
}
