package com.softmiracle.githubmvp.screen.login;

/**
 * Created by dnsfrolov on 31.03.2017.
 */

public interface LoginPresenter {

    void login(String username,String password);

    void signOut();

    void detachView();
}
