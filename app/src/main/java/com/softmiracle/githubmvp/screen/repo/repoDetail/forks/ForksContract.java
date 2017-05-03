package com.softmiracle.githubmvp.screen.repo.repoDetail.forks;

import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

/**
 * Created by dnsfrolov on 02.05.2017.
 */

public interface ForksContract {

    interface ForksView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showForks(List<Repo> repoList);

        void showError(Throwable error);
    }

    interface ForksPresenter {

        void loadForks(String name, String repo, int page);

        void detachView();
    }
}
