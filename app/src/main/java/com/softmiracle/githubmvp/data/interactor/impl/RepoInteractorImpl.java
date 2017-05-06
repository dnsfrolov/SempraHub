package com.softmiracle.githubmvp.data.interactor.impl;

import com.softmiracle.githubmvp.data.api.GithubServiceGenerator;
import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.RepoInteractor;
import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Denys on 25.02.2017.
 */

public class RepoInteractorImpl implements RepoInteractor {

    @Override
    public void getRepoList(String user, int page, final InteractorCallback<List<Repo>> callback) {

        GithubServiceGenerator.getGithubService().getRepoList(user, page).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUserStarredList(String user, int page, final InteractorCallback<List<Repo>> callback) {

        GithubServiceGenerator.getGithubService().getUserStarred(user, page).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
