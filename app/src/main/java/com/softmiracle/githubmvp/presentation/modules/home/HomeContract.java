package com.softmiracle.githubmvp.presentation.modules.home;

import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by dnsfrolov on 24.04.2017.
 */

interface HomeContract {

    interface HomeView {

        void showUserInfo(User user);

        void showProgressIndicator();

        void hideProgressIndicator();

        void showError(Throwable error);
    }

    interface HomePresenter {

        void loadUserInfo(String user);

        void detachView();
    }
}
