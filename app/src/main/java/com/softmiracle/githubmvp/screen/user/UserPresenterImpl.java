package com.softmiracle.githubmvp.screen.user;

import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.data.service.UserService;
import com.softmiracle.githubmvp.data.service.UserServiceImpl;

/**
 * Created by Denys on 26.02.2017.
 */

public class UserPresenterImpl implements UserPresenter {

    private UserView mUserView;
    private UserService mUserService;

    public UserPresenterImpl(UserView userView) {
        this.mUserView = userView;
        this.mUserService = new UserServiceImpl();
    }

    @Override
    public void loadUser(String name) {
        if (mUserView != null) {
            mUserView.showProgressIndicator();
        }

        mUserService.getUserProfile(name, new UserService.UserCallback<User>() {
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
