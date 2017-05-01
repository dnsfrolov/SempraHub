package com.softmiracle.githubmvp.screen.user.followers;

import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 30.04.2017.
 */

public interface FollowersContract {

    interface FollowersView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showFollowers(List<User> userList);

        void showError(Throwable error);
    }

    interface FollowersPresenter {

        void loadFollowers(String name, int page);

        void detachView();
    }
}
