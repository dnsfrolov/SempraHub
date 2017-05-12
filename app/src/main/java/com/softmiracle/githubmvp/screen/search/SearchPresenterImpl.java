package com.softmiracle.githubmvp.screen.search;

import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.SearchInteractor;
import com.softmiracle.githubmvp.data.interactor.impl.SearchInteractorImpl;
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.data.models.Search;
import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by dnsfrolov on 08.05.2017.
 */

public class SearchPresenterImpl implements SearchContract.SearchUserPresenter, SearchContract.SearchRepoPresenter {

    private SearchContract.SearchUserView mUserView;
    private SearchContract.SearchRepoView mRepoView;
    private SearchInteractor mSearchInteractor;

    public SearchPresenterImpl(SearchContract.SearchUserView userView) {
        this.mUserView = userView;
        this.mSearchInteractor = new SearchInteractorImpl();
    }

    public SearchPresenterImpl(SearchContract.SearchRepoView repoView) {
        this.mRepoView = repoView;
        this.mSearchInteractor = new SearchInteractorImpl();
    }

    @Override
    public void loadUserResult(String userSearch, int page) {
        if (mUserView != null) {
            mUserView.showProgressIndicator();
        }

        mSearchInteractor.searchUser(userSearch, page, new InteractorCallback<Search<User>>() {
            @Override
            public void onSuccess(Search<User> response) {
                if (response != null) {
                    mUserView.showUserResult(response.getItems());
                    mUserView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mUserView.showError(error);
                mUserView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void loadRepoResult(String repoSearch, int page) {
        if (mRepoView != null) {
            mRepoView.showProgressIndicator();
        }

        mSearchInteractor.searchRepo(repoSearch, page, new InteractorCallback<Search<Repo>>() {
            @Override
            public void onSuccess(Search<Repo> response) {
                if (response != null) {
                    mRepoView.showRepoResult(response.getItems());
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    @Override
    public void detachUserView() {
        mUserView = null;
    }

    @Override
    public void detachRepoView() {
        mRepoView = null;
    }
}
