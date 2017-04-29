package com.softmiracle.githubmvp.screen.starred.userStarred;

import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.RepoInteractor;
import com.softmiracle.githubmvp.data.interactor.impl.RepoInteractorImpl;
import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

/**
 * Created by dnsfrolov on 25.04.2017.
 */

public class UserStarredPresenterImpl implements UserStarredContract.UserStarredPresenter {

    private UserStarredContract.UserStarredView mView;
    private RepoInteractor mInteractor;

    public UserStarredPresenterImpl(UserStarredContract.UserStarredView view) {
        this.mView = view;
        this.mInteractor = new RepoInteractorImpl();
    }

    @Override
    public void loadUserStarred(String user, int page) {
        if (mView != null) {
            mView.showProgressIndicator();
        }

        mInteractor.getUserStarredList(user, page, new InteractorCallback<List<Repo>>() {
            @Override
            public void onSuccess(List<Repo> response) {
                if (response != null && response.size() > 0) {
                    mView.showUserStarred(response);
                    mView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mView.showError(error);
                mView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
