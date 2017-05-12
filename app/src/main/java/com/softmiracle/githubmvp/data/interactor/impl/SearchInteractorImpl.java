package com.softmiracle.githubmvp.data.interactor.impl;

import com.softmiracle.githubmvp.data.api.GithubServiceGenerator;
import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.SearchInteractor;
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.data.models.Search;
import com.softmiracle.githubmvp.data.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dnsfrolov on 08.05.2017.
 */

public class SearchInteractorImpl implements SearchInteractor {

    @Override
    public void searchRepo(String q, int page, final InteractorCallback<Search<Repo>> callback) {

        GithubServiceGenerator.getGithubService().searchRepo(q, page).enqueue(new Callback<Search<Repo>>() {
            @Override
            public void onResponse(Call<Search<Repo>> call, Response<Search<Repo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Search<Repo>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void searchUser(String q, int page, final InteractorCallback<Search<User>> callback) {

        GithubServiceGenerator.getGithubService().searchUser(q, page).enqueue(new Callback<Search<User>>() {
            @Override
            public void onResponse(Call<Search<User>> call, Response<Search<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Search<User>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
