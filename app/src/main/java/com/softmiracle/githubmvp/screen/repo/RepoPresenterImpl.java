package com.softmiracle.githubmvp.screen.repo;

import com.softmiracle.githubmvp.data.service.RepoService;
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.data.service.RepoServiceImpl;

import java.util.List;

/**
 * Created by Denys on 01.03.2017.
 */

public class RepoPresenterImpl implements RepoPresenter {

    private RepoView mRepoView;
    private RepoService mRepoService;

    public RepoPresenterImpl(RepoView mRepoView) {
        this.mRepoView = mRepoView;
        this.mRepoService = new RepoServiceImpl();
    }

    @Override
    public void loadRepo(final String user) {
        if (mRepoView != null) {
            mRepoView.showProgressIndicator();
        }

        mRepoService.getRepo(user, new RepoService.RepoCallback<List<Repo>>() {
            @Override
            public void onSuccess(List<Repo> response) {
                if (response != null && response.size() > 0) {
                    mRepoView.showRepo(response);
                    mRepoView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mRepoView.showError(error);
                mRepoView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        mRepoView = null;
    }
}
