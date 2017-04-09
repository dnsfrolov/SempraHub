package com.softmiracle.githubmvp.data.service;

import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.data.api.GHApi;
import com.softmiracle.githubmvp.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denys on 25.02.2017.
 */

public class RepoServiceImpl implements RepoService {

    private GHApi mApi;

    public RepoServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = retrofit.create(GHApi.class);
    }

    @Override
    public void getRepo(String user, final RepoCallback<List<Repo>> callback) {

        mApi.getRepo(user).enqueue(new Callback<List<Repo>>() {
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
