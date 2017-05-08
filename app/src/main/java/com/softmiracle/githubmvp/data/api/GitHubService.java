package com.softmiracle.githubmvp.data.api;

import com.softmiracle.githubmvp.data.models.Authorization;
import com.softmiracle.githubmvp.data.models.CreateAuthorization;
import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Denys on 25.02.2017.
 */

public interface GitHubService {

    @POST("/authorizations")
    Call<Authorization> getAuthorization(@Header("Authorization") String authorization,
                                         @Body CreateAuthorization createAuthorization);

    @GET("/users/{user}")
    Call<User> getUserProfile(@Path("user") String user);

    @GET("/users/{user}/following?per_page=20")
    Call<List<User>> getFollowing(@Path("user") String user, @Query("page") int page);

    @GET("/users/{user}/followers?per_page=20")
    Call<List<User>> getFollowers(@Path("user") String user, @Query("page") int page);

    @GET("/users/{user}/repos?per_page=20")
    Call<List<Repo>> getRepoList(@Path("user") String user, @Query("page") int page);

    @GET("repos/{owner}/{repo}")
    Call<Repo> getRepoDetail(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/users/{user}/starred?per_page=20")
    Call<List<Repo>> getUserStarred(@Path("user") String user, @Query("page") int page);

    @GET("/repos/{user}/{repo}/stargazers?per_page=20")
    Call<List<User>> getRepoStarred(@Path("user") String user, @Path("repo") String repo, @Query("page") int page);

    @GET("/repos/{user}/{repo}/subscribers?per_page=20")
    Call<List<User>> getWatchers(@Path("user") String user, @Path("repo") String repo, @Query("page") int page);

    @GET("/repos/{user}/{repo}/forks?per_page=20")
    Call<List<Repo>> getForks(@Path("user") String user, @Path("repo") String repo, @Query("page") int page);
}
