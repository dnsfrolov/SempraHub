package com.softmiracle.githubmvp.view;

import com.softmiracle.githubmvp.data.models.GHUser;

/**
 * Created by Denys on 26.02.2017.
 */

public interface GHUserView {

    void showProgressIndicator();

    void showUserProfile(GHUser user);
}
