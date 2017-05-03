package com.softmiracle.githubmvp.screen.repo.repoDetail.forks;

import com.softmiracle.githubmvp.data.interactor.ForksInteractor;
import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.impl.ForksInteractorImpl;
import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

/**
 * Created by dnsfrolov on 02.05.2017.
 */

public class ForksPresenterImpl implements ForksContract.ForksPresenter {

    private ForksContract.ForksView mForksView;
    private ForksInteractor mForksInteractor;

    public ForksPresenterImpl(ForksContract.ForksView forksView) {
        this.mForksView = forksView;
        this.mForksInteractor = new ForksInteractorImpl();
    }

    @Override
    public void loadForks(String name, String repo, int page) {
        if (mForksView != null) {
            mForksView.showProgressIndicator();
        }

        mForksInteractor.getForks(name, repo, page, new InteractorCallback<List<Repo>>() {
            @Override
            public void onSuccess(List<Repo> response) {
                if (response != null && response.size() > 0) {
                    mForksView.showForks(response);
                    mForksView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mForksView.showError(error);
                mForksView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        mForksView = null;
    }
}
