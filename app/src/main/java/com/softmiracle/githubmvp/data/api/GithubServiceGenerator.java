package com.softmiracle.githubmvp.data.api;

import android.support.annotation.NonNull;

import com.softmiracle.githubmvp.data.api.interceptor.ApiKeyInterceptor;
import com.softmiracle.githubmvp.data.api.interceptor.LoggingInterceptor;
import com.softmiracle.githubmvp.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dnsfrolov on 05.05.2017.
 */

public final class GithubServiceGenerator {

    private static OkHttpClient mClient;

    private static GitHubService mService;

    private GithubServiceGenerator() {
    }

    @NonNull
    public static GitHubService getGithubService() {
        GitHubService service = mService;
        if (service == null) {
            service = mService = buildRetrofit().create(GitHubService.class);
        }
        return service;
    }

    public static void recreate() {
        mClient = null;
        mClient = getClient();
        mService = buildRetrofit().create(GitHubService.class);
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = mClient;
        if (client == null) {
            client = mClient = buildClient();
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor.create())
                .addInterceptor(ApiKeyInterceptor.create())
                .build();
    }
}
