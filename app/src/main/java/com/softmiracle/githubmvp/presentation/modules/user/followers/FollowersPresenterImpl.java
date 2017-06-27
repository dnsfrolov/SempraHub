package com.softmiracle.githubmvp.presentation.modules.user.followers;

import com.softmiracle.githubmvp.data.interactor.FollowersInteractor;
import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.impl.FollowersInteractorImpl;
import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 30.04.2017.
 */

public class FollowersPresenterImpl implements FollowersContract.FollowersPresenter {

    private FollowersContract.FollowersView mFollowersView;
    private FollowersInteractor mFollowersInteractor;

    public FollowersPresenterImpl(FollowersContract.FollowersView followersView) {
        this.mFollowersView = followersView;
        mFollowersInteractor = new FollowersInteractorImpl();
    }

    @Override
    public void loadFollowers(String name, int page) {
        if (mFollowersView != null) {
            mFollowersView.showProgressIndicator();
        }

        mFollowersInteractor.getFollowers(name, page, new InteractorCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> response) {
                if (response != null && response.size() > 0) {
                    mFollowersView.showFollowers(response);
                    mFollowersView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mFollowersView.showError(error);
                mFollowersView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        mFollowersView = null;
    }
}
