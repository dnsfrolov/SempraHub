package com.softmiracle.githubmvp.screen.login;

/**
 * Created by dnsfrolov on 12.03.2017.
 */

public interface LoginView {

    void showProgressIndicator();

    void hideProgressIndicator();

    void loginSuccess();

    void showError();
}
