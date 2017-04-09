package com.softmiracle.githubmvp.data.service;

import com.softmiracle.githubmvp.data.models.Authorization;

/**
 * Created by dnsfrolov on 12.03.2017.
 */

public interface LoginService {

    interface LoginCallback<T> {

        void onSuccess(T response);

        void onError(Throwable error);
    }

    void signIn(String username, String password, LoginCallback<Authorization> callback);
}