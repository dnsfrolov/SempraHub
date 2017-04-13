package com.softmiracle.githubmvp.screen.profile;

/**
 * Created by Denys on 26.02.2017.
 */

public interface ProfilePresenter {

    void loadUser(String name);

    void detachView();
}
