package com.softmiracle.githubmvp.screen.home;

import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by dnsfrolov on 08.04.2017.
 */

public interface HomeView {

    void showUserInfo(User user);

    void showUserProfile();

    void showProgressIndicator();

    void hideProgressIndicator();

    void showError(Throwable error);
}
