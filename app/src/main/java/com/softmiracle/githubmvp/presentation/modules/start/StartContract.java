package com.softmiracle.githubmvp.presentation.modules.start;

/**
 * Created by dnsfrolov on 24.04.2017.
 */

interface StartContract {

    interface StartView {

        void setAuthorized(boolean isAuthorized);
    }

    interface StartPresenter {

        void checkAuthorized();

        void detachView();
    }
}
