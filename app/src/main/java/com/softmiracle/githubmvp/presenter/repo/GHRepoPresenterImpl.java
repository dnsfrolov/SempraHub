package com.softmiracle.githubmvp.presenter.repo;

import com.softmiracle.githubmvp.data.api.GHRepoService;
import com.softmiracle.githubmvp.data.api.GHRepoServiceImpl;
import com.softmiracle.githubmvp.data.models.GHRepo;
import com.softmiracle.githubmvp.view.GHRepoView;

import java.util.List;

/**
 * Created by Denys on 01.03.2017.
 */

public class GHRepoPresenterImpl implements GHRepoPresenter {

    private GHRepoView mGHRepoView;
    private GHRepoService mGHRepoService;

    public GHRepoPresenterImpl(GHRepoView mGHRepoView) {
        this.mGHRepoView = mGHRepoView;
        this.mGHRepoService = new GHRepoServiceImpl();
    }

    @Override
    public void loadRepo(final String user) {
        if (mGHRepoView != null) {
            mGHRepoView.showProgressIndicator();
        }

        mGHRepoService.getRepo(user, new GHRepoService.GHRepoCallback<List<GHRepo>>() {
            @Override
            public void onSuccess(List<GHRepo> response) {
                if (response != null && response.size() > 0) {
                    mGHRepoView.showRepo(response);
                    mGHRepoView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mGHRepoView.showError(error);
                mGHRepoView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        mGHRepoView = null;
    }
}
