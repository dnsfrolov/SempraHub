package com.softmiracle.githubmvp.data.interactor.impl;

import com.softmiracle.githubmvp.data.api.GithubServiceGenerator;
import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.UserInteractor;
import com.softmiracle.githubmvp.data.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Denys on 25.02.2017.
 */

public class UserInteractorImpl implements UserInteractor {

    @Override
    public void getUserProfile(String user, final InteractorCallback<User> callback) {

        GithubServiceGenerator.getGithubService().getUserProfile(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
