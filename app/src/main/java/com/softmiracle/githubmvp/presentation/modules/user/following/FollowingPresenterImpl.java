package com.softmiracle.githubmvp.presentation.modules.user.following;

import com.softmiracle.githubmvp.data.interactor.FollowingInteractor;
import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.impl.FollowingInteractorImpl;
import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 30.04.2017.
 */

public class FollowingPresenterImpl implements FollowingContract.FollowingPresenter {

    private FollowingContract.FollowingView mFollowingView;
    private FollowingInteractor mFollowingInteractor;

    public FollowingPresenterImpl(FollowingContract.FollowingView followingView) {
        this.mFollowingView = followingView;
        mFollowingInteractor = new FollowingInteractorImpl();
    }

    @Override
    public void loadFollowing(String name, int page) {
        if (mFollowingView != null) {
            mFollowingView.showProgressIndicator();
        }

        mFollowingInteractor.getFollowing(name, page, new InteractorCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> response) {
                if (response != null && response.size() > 0) {
                    mFollowingView.showFollowing(response);
                    mFollowingView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mFollowingView.showError(error);
            }
        });
    }

    @Override
    public void detachView() {
        mFollowingView = null;
    }
}
