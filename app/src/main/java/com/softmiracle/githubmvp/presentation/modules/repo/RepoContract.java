package com.softmiracle.githubmvp.presentation.modules.repo;

import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

/**
 * Created by dnsfrolov on 24.04.2017.
 */

interface RepoContract {

    interface RepoView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showRepo(List<Repo> repoList);

        void showError(Throwable error);
    }

    interface RepoPresenter {

        void loadRepo(String user, int page);

        void detachView();
    }
}
