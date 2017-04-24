package com.softmiracle.githubmvp.screen.user;

import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by dnsfrolov on 24.04.2017.
 */

interface UserContract {

    interface UserView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showUserProfile(User user);

        void showError(Throwable error);
    }

    interface UserPresenter {

        void loadUser(String name);

        void detachView();
    }
}
