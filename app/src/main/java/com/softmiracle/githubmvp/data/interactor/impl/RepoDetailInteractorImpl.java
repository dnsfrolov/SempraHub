package com.softmiracle.githubmvp.data.interactor.impl;

import com.softmiracle.githubmvp.data.api.GithubServiceGenerator;
import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.RepoDetailInteractor;
import com.softmiracle.githubmvp.data.models.Repo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dnsfrolov on 29.04.2017.
 */

public class RepoDetailInteractorImpl implements RepoDetailInteractor {

    @Override
    public void getRepoDetail(String owner, String repo, final InteractorCallback<Repo> callback) {

        GithubServiceGenerator.getGithubService().getRepoDetail(owner, repo).enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
