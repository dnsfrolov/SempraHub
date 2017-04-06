package com.softmiracle.githubmvp.data.service;

import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.data.api.GHApi;
import com.softmiracle.githubmvp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denys on 25.02.2017.
 */

public class UserServiceImpl implements UserService {

    private GHApi mApi;

    public UserServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = retrofit.create(GHApi.class);
    }

    @Override
    public void getUserProfile(String user, final UserCallback<User> callback) {

        mApi.getUserProfile(user).enqueue(new Callback<User>() {
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
