package com.softmiracle.githubmvp.presenter;

import android.widget.Toast;

import com.softmiracle.githubmvp.data.api.GHUserService;
import com.softmiracle.githubmvp.data.api.GHUserServiceImpl;
import com.softmiracle.githubmvp.data.models.GHUser;
import com.softmiracle.githubmvp.view.GHUserView;

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
        mGHUserService.getUserProfile(name, new GHUserService.GHUserCallback<GHUser>() {
            @Override
            public void onSuccess(GHUser response) {
                if (response != null) {
                    // ?
                    mGHUserView.showUserProfile(response);
                }
            }

            @Override
            public void onError(Throwable error) {
                error.getMessage();
            }
        });
    }

    @Override
    public void detachView() {
        this.mGHUserView = null;
    }
}
