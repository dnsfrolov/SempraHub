package com.softmiracle.githubmvp.screen.login;

/**
 * Created by dnsfrolov on 24.04.2017.
 */

interface LoginContract {

    interface LoginView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void loginSuccess();

        void showError();
    }

    interface LoginPresenter {

        void login(String username, String password);

        void detachView();
    }
}
