package com.softmiracle.githubmvp.data.api;

import com.softmiracle.githubmvp.data.models.GHUser;

/**
 * Created by Denys on 25.02.2017.
 */

interface GHUserService {

    interface GHUserCallback<T> {

        void onSuccess(T response);

        void onError(Throwable error);
    }

    void getUserProfile(String user, GHUserCallback<GHUser> callback);
}
