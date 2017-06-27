package com.softmiracle.githubmvp.presentation.modules.user.following;

import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 30.04.2017.
 */

public interface FollowingContract {

    interface FollowingView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showFollowing(List<User> userList);

        void showError(Throwable error);
    }

    interface FollowingPresenter {

        void loadFollowing(String name, int page);

        void detachView();
    }
}
