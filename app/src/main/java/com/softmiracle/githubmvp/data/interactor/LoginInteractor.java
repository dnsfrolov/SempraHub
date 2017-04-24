package com.softmiracle.githubmvp.data.interactor;

import com.softmiracle.githubmvp.data.models.Authorization;


/**
 * Created by dnsfrolov on 24.04.2017.
 */

public interface LoginInteractor {

    void signIn(String username, String password, InteractorCallback<Authorization> callback);
}
