package com.softmiracle.githubmvp.screen.user;

import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.UserInteractor;
import com.softmiracle.githubmvp.data.interactor.impl.UserInteractorImpl;
import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by Denys on 26.02.2017.
 */

class UserPresenterImpl implements UserContract.UserPresenter {

    private UserContract.UserView mUserView;
    private UserInteractor mUserInteractor;

    UserPresenterImpl(UserContract.UserView userView) {
        this.mUserView = userView;
        this.mUserInteractor = new UserInteractorImpl();
    }

    @Override
    public void loadUser(String name) {
        if (mUserView != null) {
            mUserView.showProgressIndicator();
        }

        mUserInteractor.getUserProfile(name, new InteractorCallback<User>() {
            @Override
            public void onSuccess(User response) {
                if (response != null) {
                    mUserView.showUserProfile(response);
                    mUserView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mUserView.showError(error);
                mUserView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        mUserView = null;
    }
}
