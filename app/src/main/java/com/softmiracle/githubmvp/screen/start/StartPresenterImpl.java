package com.softmiracle.githubmvp.screen.start;

import android.text.TextUtils;

import com.softmiracle.githubmvp.utils.AccountPreferences;

/**
 * Created by dnsfrolov on 04.04.2017.
 */

public class StartPresenterImpl implements StartPresenter {

    private StartView mStartView;

    public StartPresenterImpl(StartView startView) {
        this.mStartView = startView;
    }

    @Override
    public void checkAuthorized() {
        if (mStartView != null) {
            mStartView.setAuthorized(!TextUtils.isEmpty(AccountPreferences.getToken()));
        }
    }

    @Override
    public void detachView() {
        mStartView = null;
    }
}
