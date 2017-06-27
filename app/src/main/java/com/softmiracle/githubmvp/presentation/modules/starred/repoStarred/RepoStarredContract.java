package com.softmiracle.githubmvp.presentation.modules.starred.repoStarred;

import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 02.05.2017.
 */

public interface RepoStarredContract {

    interface RepoStarredView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showRepoStarred(List<User> userList);

        void showError(Throwable error);
    }

    interface RepoStarredPresenter {

        void loadRepoStarred(String name, String repo, int page);

        void detachView();
    }
}
