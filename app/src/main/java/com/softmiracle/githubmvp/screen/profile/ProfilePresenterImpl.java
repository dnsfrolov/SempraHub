package com.softmiracle.githubmvp.screen.profile;

import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.data.service.UserService;
import com.softmiracle.githubmvp.data.service.UserServiceImpl;

/**
 * Created by Denys on 26.02.2017.
 */

public class ProfilePresenterImpl implements ProfilePresenter {

    private ProfileView mUserView;
    private UserService mUserService;

    public ProfilePresenterImpl(ProfileView userView) {
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
