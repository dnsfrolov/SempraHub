package com.softmiracle.githubmvp.user;

import com.softmiracle.githubmvp.data.api.GHUserService;
import com.softmiracle.githubmvp.data.api.GHUserServiceImpl;
import com.softmiracle.githubmvp.data.models.GHUser;

/**
 * Created by Denys on 26.02.2017.
 */

public class GHUserPresenterImpl implements GHUserPresenter {

    private GHUserView mGHUserView;
    private GHUserService mGHUserService;

    public GHUserPresenterImpl(GHUserView userView) {
        this.mGHUserView = userView;
        this.mGHUserService = new GHUserServiceImpl();
    }

    @Override
    public void loadUser(String name) {
        if (mGHUserView != null) {
            mGHUserView.showProgressIndicator();
        }

        mGHUserService.getUserProfile(name, new GHUserService.GHUserCallback<GHUser>() {
            @Override
            public void onSuccess(GHUser response) {
                if (response != null) {
                    // ?
                    mGHUserView.showUserProfile(response);
                    mGHUserView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mGHUserView.showError(error);
                mGHUserView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        this.mGHUserView = null;
    }
}
