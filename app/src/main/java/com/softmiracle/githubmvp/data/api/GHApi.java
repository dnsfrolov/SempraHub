package com.softmiracle.githubmvp.data.api;

import com.softmiracle.githubmvp.data.models.GHRepo;
import com.softmiracle.githubmvp.data.models.GHUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Denys on 25.02.2017.
 */

public interface GHApi {

    @GET("/users/{user}/repos")
    Call<GHRepo> getRepo(@Path("user") String name);

    @GET("/users/{user}")
    Call<GHUser> getUserProfile(@Path("user") String user);
}
