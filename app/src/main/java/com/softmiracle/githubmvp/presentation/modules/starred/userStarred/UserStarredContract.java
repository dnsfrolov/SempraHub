package com.softmiracle.githubmvp.presentation.modules.starred.userStarred;

import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

/**
 * Created by dnsfrolov on 25.04.2017.
 */

interface UserStarredContract {

    interface UserStarredView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showUserStarred(List<Repo> repoList);

        void showError(Throwable error);
    }

    interface UserStarredPresenter {

        void loadUserStarred(String user, int page);

        void detachView();
    }
}
