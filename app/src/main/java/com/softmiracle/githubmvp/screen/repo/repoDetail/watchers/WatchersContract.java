package com.softmiracle.githubmvp.screen.repo.repoDetail.watchers;

import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 02.05.2017.
 */

public interface WatchersContract {

    interface WatchersView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showWatchers(List<User> userList);

        void showError(Throwable error);
    }

    interface WatchersPresenter {

        void loadWatchers(String name, String repo, int page);

        void detachView();
    }
}
