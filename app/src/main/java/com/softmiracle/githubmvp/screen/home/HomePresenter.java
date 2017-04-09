package com.softmiracle.githubmvp.screen.home;

import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by dnsfrolov on 08.04.2017.
 */

public interface HomePresenter {

    void loadUserInfo(String user);

    void openProfile();

    void openRepositories();

    void openGists();

    void signOut();

    void detachView();
}
