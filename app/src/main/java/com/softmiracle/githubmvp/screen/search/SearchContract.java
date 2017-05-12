package com.softmiracle.githubmvp.screen.search;

import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.data.models.Search;
import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 08.05.2017.
 */

public interface SearchContract {

    interface SearchUserView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showUserResult(List<User> userList);

        void showError(Throwable error);
    }

    interface SearchUserPresenter {

        void loadUserResult(String userSearch, int page);

        void detachUserView();
    }

    interface SearchRepoView {

        void showProgressIndicator();

        void hideProgressIndicator();

        void showRepoResult(List<Repo> repoList);

        void showError(Throwable error);
    }

    interface SearchRepoPresenter {

        void loadRepoResult(String repoSearch, int page);

        void detachRepoView();
    }
}
