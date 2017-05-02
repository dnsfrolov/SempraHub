package com.softmiracle.githubmvp.data.interactor.impl;

import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.interactor.RepoStarredInteractor;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.data.service.GitHubService;
import com.softmiracle.githubmvp.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dnsfrolov on 02.05.2017.
 */

public class RepoStarredInteractorImpl implements RepoStarredInteractor {

    private GitHubService mApi;

    public RepoStarredInteractorImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = retrofit.create(GitHubService.class);
    }

    @Override
    public void getRepoStarred(String user, String repo, int page, final InteractorCallback<List<User>> callback) {

        mApi.getRepoStarred(user, repo, page).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onError(t);
            }
        });

    }
}
