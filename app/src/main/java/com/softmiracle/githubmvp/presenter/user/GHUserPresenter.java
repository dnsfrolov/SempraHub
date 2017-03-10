package com.softmiracle.githubmvp.presenter.user;

/**
 * Created by Denys on 26.02.2017.
 */

public interface GHUserPresenter {

    void loadUser(String name);

    void detachView();
}
