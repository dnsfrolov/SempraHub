package com.softmiracle.githubmvp.screen.user;

/**
 * Created by Denys on 26.02.2017.
 */

public interface UserPresenter {

    void loadUser(String name);

    void detachView();
}
