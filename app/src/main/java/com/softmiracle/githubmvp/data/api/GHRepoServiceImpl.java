package com.softmiracle.githubmvp.data.api;

import com.softmiracle.githubmvp.utils.Constants;
import com.softmiracle.githubmvp.data.models.GHRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denys on 25.02.2017.
 */

public class GHRepoServiceImpl implements GHRepoService {

    private GHApi mGHApi;

    public GHRepoServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGHApi = retrofit.create(GHApi.class);
    }

    @Override
    public void getRepo(String user, final GHRepoCallback<GHRepo> callback) {

        mGHApi.getRepo(user).enqueue(new Callback<GHRepo>() {
            @Override
            public void onResponse(Call<GHRepo> call, Response<GHRepo> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GHRepo> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
