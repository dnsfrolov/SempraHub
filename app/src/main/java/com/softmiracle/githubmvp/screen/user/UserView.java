package com.softmiracle.githubmvp.screen.user;

import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by Denys on 26.02.2017.
 */

public interface UserView {

    void showProgressIndicator();

    void hideProgressIndicator();

    void showUserProfile(User user);

    void showError(Throwable error);
}
