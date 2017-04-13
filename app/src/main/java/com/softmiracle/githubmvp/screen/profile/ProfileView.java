package com.softmiracle.githubmvp.screen.profile;

import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by Denys on 26.02.2017.
 */

public interface ProfileView {

    void showProgressIndicator();

    void hideProgressIndicator();

    void showUserProfile(User user);

    void showError(Throwable error);
}
