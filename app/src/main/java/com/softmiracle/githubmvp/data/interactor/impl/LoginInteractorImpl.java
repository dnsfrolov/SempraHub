package com.softmiracle.githubmvp.data.interactor.impl;

import com.softmiracle.githubmvp.data.api.GithubServiceGenerator;
import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.LoginInteractor;
import com.softmiracle.githubmvp.data.models.Authorization;
import com.softmiracle.githubmvp.data.models.CreateAuthorization;
import com.softmiracle.githubmvp.utils.prefs.AccountPreferences;
import com.softmiracle.githubmvp.utils.helpers.AuthorizationHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dnsfrolov on 12.03.2017.
 */

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void signIn(final String login, String password, final InteractorCallback<Authorization> callback) {

        String authorizationStr = AuthorizationHelper.createAuth(login, password);
        CreateAuthorization createAuthorization = new CreateAuthorization();

        GithubServiceGenerator.getGithubService().getAuthorization(authorizationStr, createAuthorization)
                .enqueue(new Callback<Authorization>() {
                    @Override
                    public void onResponse(Call<Authorization> call, Response<Authorization> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            callback.onSuccess(response.body());

                            AccountPreferences.setToken(response.body().getToken());
                            AccountPreferences.setUsername(login);
                            GithubServiceGenerator.recreate();
                        }
                    }

                    @Override
                    public void onFailure(Call<Authorization> call, Throwable t) {
                        callback.onError(t);
                    }
                });
    }
}
