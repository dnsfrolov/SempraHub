package com.softmiracle.githubmvp.presentation.modules.home;

import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.UserInteractor;
import com.softmiracle.githubmvp.data.interactor.impl.UserInteractorImpl;
import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by dnsfrolov on 08.04.2017.
 */

class HomePresenterImpl implements HomeContract.HomePresenter {

    private HomeContract.HomeView mHomeView;
    private UserInteractor mUserInteractor;

    HomePresenterImpl(HomeContract.HomeView homeView) {
        this.mHomeView = homeView;
        this.mUserInteractor = new UserInteractorImpl();
    }

    @Override
    public void loadUserInfo(String user) {
        if (mHomeView != null) {
            mHomeView.showProgressIndicator();
        }

        mUserInteractor.getUserProfile(user, new InteractorCallback<User>() {
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
    public void detachView() {
        mHomeView = null;
    }
}
