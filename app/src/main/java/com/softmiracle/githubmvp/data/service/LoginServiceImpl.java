package com.softmiracle.githubmvp.data.service;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.softmiracle.githubmvp.data.api.GHApi;
import com.softmiracle.githubmvp.data.models.Authorization;
import com.softmiracle.githubmvp.data.models.CreateAuthorization;
import com.softmiracle.githubmvp.utils.AccountPreferences;
import com.softmiracle.githubmvp.utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dnsfrolov on 12.03.2017.
 */

public class LoginServiceImpl implements LoginService {

    private GHApi mApi;
    private static String userName;
    private static String password;

    private void setUserInfo(String userName, String password) {
        LoginServiceImpl.userName = userName;
        LoginServiceImpl.password = password;
    }


    public LoginServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        mApi = retrofit.create(GHApi.class);
    }

    @NonNull
    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {

                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        String userCredentials = userName + ":" + password;
                        String basicAuth = "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.DEFAULT));
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader("User-Agent", "Sempra for GitHub")
                                .addHeader("Accept", "application/vnd.github.v3+json")
                                .addHeader("Authorization", basicAuth.trim());
                        Request request = requestBuilder.build();

                        return chain.proceed(request);
                    }
                });
        return okHttpBuilder.build();
    }

    @Override
    public void signIn(String username, String password, final LoginCallback<Authorization> callback) {
        final CreateAuthorization createAuthorization = new CreateAuthorization();
        setUserInfo(username, password);
        mApi.getAuthorization(createAuthorization).enqueue(new Callback<Authorization>() {
            @Override
            public void onResponse(Call<Authorization> call, Response<Authorization> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                    AccountPreferences.setToken(response.body().getToken());
                }
            }

            @Override
            public void onFailure(Call<Authorization> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
