package com.softmiracle.githubmvp.screen.main;

import android.text.TextUtils;

import com.softmiracle.githubmvp.utils.AccountPreferences;

/**
 * Created by dnsfrolov on 04.04.2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public MainPresenterImpl(MainView mMainView) {
        this.mMainView = mMainView;
    }

    @Override
    public void checkAuthorized() {
        if (mMainView != null) {
            mMainView.setAuthorized(!TextUtils.isEmpty(AccountPreferences.getToken()));
        }
    }

    @Override
    public void detachView() {
        mMainView = null;
    }
}
