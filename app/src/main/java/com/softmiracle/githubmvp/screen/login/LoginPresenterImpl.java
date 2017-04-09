package com.softmiracle.githubmvp.screen.login;

import com.softmiracle.githubmvp.data.models.Authorization;
import com.softmiracle.githubmvp.data.service.LoginService;
import com.softmiracle.githubmvp.data.service.LoginServiceImpl;

/**
 * Created by dnsfrolov on 31.03.2017.
 */

class LoginPresenterImpl implements LoginPresenter {

    private LoginView mLoginView;
    private LoginService mLoginService;

    LoginPresenterImpl(LoginView mLoginView) {
        this.mLoginView = mLoginView;
        this.mLoginService = new LoginServiceImpl();
    }

    @Override
    public void login(String username, String password) {
        if (mLoginView != null) {
            mLoginView.showProgressIndicator();
        }

        mLoginService.signIn(username, password, new LoginService.LoginCallback<Authorization>() {
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
