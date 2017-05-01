package com.softmiracle.githubmvp.data.interactor.impl;

import com.softmiracle.githubmvp.data.interactor.FollowersInteractor;
import com.softmiracle.githubmvp.data.interactor.InteractorCallback;
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
 * Created by dnsfrolov on 30.04.2017.
 */

public class FollowersInteractorImpl implements FollowersInteractor {

    private GitHubService mApi;

    public FollowersInteractorImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = retrofit.create(GitHubService.class);
    }

    @Override
    public void getFollowers(String user, int page, final InteractorCallback<List<User>> callback) {

        mApi.getFollowers(user, page).enqueue(new Callback<List<User>>() {
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
