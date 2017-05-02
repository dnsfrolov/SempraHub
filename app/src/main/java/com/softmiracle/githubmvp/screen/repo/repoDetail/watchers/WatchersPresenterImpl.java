package com.softmiracle.githubmvp.screen.repo.repoDetail.watchers;

import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.WatchersInteractor;
import com.softmiracle.githubmvp.data.interactor.impl.WatchersInteractorImpl;
import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 02.05.2017.
 */

public class WatchersPresenterImpl implements WatchersContract.WatchersPresenter {

    private WatchersContract.WatchersView mWatchersView;
    private WatchersInteractor mWatchersInteractor;

    public WatchersPresenterImpl(WatchersContract.WatchersView watchersView) {
        this.mWatchersView = watchersView;
        this.mWatchersInteractor = new WatchersInteractorImpl();
    }

    @Override
    public void loadWatchers(String name, String repo, int page) {
        if (mWatchersView != null) {
            mWatchersView.showProgressIndicator();
        }

        mWatchersInteractor.getWatchers(name, repo, page, new InteractorCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> response) {
                if (response != null && response.size() > 0) {
                    mWatchersView.showWatchers(response);
                    mWatchersView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mWatchersView.showError(error);
                mWatchersView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        mWatchersView = null;
    }
}
