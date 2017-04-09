package com.softmiracle.githubmvp.screen.home;

import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.data.service.UserService;
import com.softmiracle.githubmvp.data.service.UserServiceImpl;

/**
 * Created by dnsfrolov on 08.04.2017.
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeView mHomeView;
    private UserService mUserService;

    public HomePresenterImpl(HomeView homeView) {
        this.mHomeView = homeView;
        this.mUserService = new UserServiceImpl();
    }

    @Override
    public void loadUserInfo(String user) {
        if (mHomeView != null) {
            mHomeView.showProgressIndicator();
        }

        mUserService.getUserProfile(user, new UserService.UserCallback<User>() {
            @Override
            public void onSuccess(User response) {
                if (response != null) {
                    mHomeView.showUserInfo(response);
                    mHomeView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mHomeView.showError(error);
                mHomeView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void openProfile() {

    }

    @Override
    public void openRepositories() {

    }

    @Override
    public void openGists() {

    }

    @Override
    public void signOut() {

    }

    @Override
    public void detachView() {
        mHomeView = null;
    }
}
