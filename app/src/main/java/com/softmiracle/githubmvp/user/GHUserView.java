package com.softmiracle.githubmvp.user;

import com.softmiracle.githubmvp.data.models.GHUser;

/**
 * Created by Denys on 26.02.2017.
 */

public interface GHUserView {

    void showProgressIndicator();

    void hideProgressIndicator();

    void showUserProfile(GHUser user);

    void showError(Throwable error);
}
