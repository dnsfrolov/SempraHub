package com.softmiracle.githubmvp.data.service;

import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by Denys on 25.02.2017.
 */

public interface UserService {

    interface UserCallback<T> {

        void onSuccess(T response);

        void onError(Throwable error);
    }

    void getUserProfile(String user, UserCallback<User> callback);
}
