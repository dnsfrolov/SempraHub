package com.softmiracle.githubmvp.presentation.modules.repo.detail;

import com.softmiracle.githubmvp.data.models.Repo;

/**
 * Created by dnsfrolov on 29.04.2017.
 */

public class RepoDetailContract {

    interface RepoDetailView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showRepoDetail(Repo repo);

        void showError(Throwable error);
    }

    interface RepoDetailPresenter {

        void loadRepoDetail(String owner, String repo);

        void detachView();
    }
}
