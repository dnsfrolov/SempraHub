package com.softmiracle.githubmvp.screen.login;

import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.LoginInteractor;
import com.softmiracle.githubmvp.data.models.Authorization;
import com.softmiracle.githubmvp.data.interactor.impl.LoginInteractorImpl;

/**
 * Created by dnsfrolov on 31.03.2017.
 */

class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private LoginContract.LoginView mLoginView;
    private LoginInteractor mLoginInteractor;

    LoginPresenterImpl(LoginContract.LoginView mLoginView) {
        this.mLoginView = mLoginView;
        this.mLoginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void login(String username, String password) {
        if (mLoginView != null) {
            mLoginView.showProgressIndicator();
        }

        mLoginInteractor.signIn(username, password, new InteractorCallback<Authorization>() {
            @Override
            public void onSuccess(Authorization response) {
                if (response != null) {
                    mLoginView.loginSuccess();
                    mLoginView.hideProgressIndicator();
                }
            }

            @Override
            public void onError(Throwable error) {
                mLoginView.showError();
                mLoginView.hideProgressIndicator();
            }
        });
    }

    @Override
    public void detachView() {
        mLoginView = null;
    }
}
