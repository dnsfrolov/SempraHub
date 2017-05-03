package com.softmiracle.githubmvp.data.interactor.impl;

import com.softmiracle.githubmvp.data.interactor.ForksInteractor;
import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
import com.softmiracle.githubmvp.data.models.Repo;
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

public class ForksInteractorImpl implements ForksInteractor {

    private GitHubService mApi;

    public ForksInteractorImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = retrofit.create(GitHubService.class);
    }

    @Override
    public void getForks(String user, String repo, int page, final InteractorCallback<List<Repo>> callback) {

        mApi.getForks(user, repo, page).enqueue(new Callback<List<Repo>>() {
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
