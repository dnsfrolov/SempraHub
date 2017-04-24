package com.softmiracle.githubmvp.screen.repo;

import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.RepoInteractor;
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.data.interactor.impl.RepoInteractorImpl;

import java.util.List;

/**
 * Created by Denys on 01.03.2017.
 */

class RepoPresenterImpl implements RepoContract.RepoPresenter {

    private RepoContract.RepoView mRepoView;
    private RepoInteractor mRepoInteractor;

    RepoPresenterImpl(RepoContract.RepoView mRepoView) {
        this.mRepoView = mRepoView;
        this.mRepoInteractor = new RepoInteractorImpl();
    }

    @Override
    public void loadRepo(final String user, final int page) {
        if (mRepoView != null) {
            mRepoView.showProgressIndicator();
        }

        mRepoInteractor.getRepo(user, page, new InteractorCallback<List<Repo>>() {
            @Override
            public void onSuccess(List<Repo> response) {
                if (response != null && response.size() > 0) {
                    mRepoView.showRepo(response);
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
