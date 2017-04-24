package com.softmiracle.githubmvp.data.interactor;

import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by dnsfrolov on 24.04.2017.
 */

public interface UserInteractor {

    void getUserProfile(String user, InteractorCallback<User> callback);
}
