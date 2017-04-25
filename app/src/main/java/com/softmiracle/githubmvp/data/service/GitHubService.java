package com.softmiracle.githubmvp.data.service;

import com.softmiracle.githubmvp.data.models.CreateAuthorization;
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.data.models.Authorization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Denys on 25.02.2017.
 */

public interface GitHubService {

    @POST("/authorizations")
    Call<Authorization> getAuthorization(@Body CreateAuthorization authorization);

    @GET("/users/{user}")
    Call<User> getUserProfile(@Path("user") String user);

    @GET("/users/{user}/repos?per_page=20")
    Call<List<Repo>> getRepo(@Path("user") String user, @Query("page") int page);

    @GET("/users/{user}/starred?per_page=20")
    Call<List<Repo>> getUserStarred(@Path("user") String user, @Query("page") int page);
}
