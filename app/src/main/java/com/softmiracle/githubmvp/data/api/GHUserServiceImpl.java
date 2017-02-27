package com.softmiracle.githubmvp.data.api;

import com.softmiracle.githubmvp.utils.Constants;
import com.softmiracle.githubmvp.data.models.GHUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denys on 25.02.2017.
 */

public class GHUserServiceImpl implements GHUserService {

    private GHApi mGHApi;

    public GHUserServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGHApi = retrofit.create(GHApi.class);
    }

    @Override
    public void getUserProfile(String user, final GHUserCallback<GHUser> callback) {

        mGHApi.getUserProfile(user).enqueue(new Callback<GHUser>() {
            @Override
            public void onResponse(Call<GHUser> call, Response<GHUser> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<GHUser> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
