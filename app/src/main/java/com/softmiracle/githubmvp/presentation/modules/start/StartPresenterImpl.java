package com.softmiracle.githubmvp.presentation.modules.start;

import android.text.TextUtils;

import com.softmiracle.githubmvp.utils.prefs.AccountPreferences;

/**
 * Created by dnsfrolov on 04.04.2017.
 */

class StartPresenterImpl implements StartContract.StartPresenter {

    private StartContract.StartView mStartView;

    StartPresenterImpl(StartContract.StartView startView) {
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
