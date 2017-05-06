package com.softmiracle.githubmvp.utils;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

/**
 * Created by dnsfrolov on 01.04.2017.
 */

public class AccountPreferences {

    public AccountPreferences() {
    }

    @NonNull
    public static String getToken() {
        return Hawk.get(Constants.TOKEN, "");
    }

    public static void setToken(@NonNull String token) {
        Hawk.put(Constants.TOKEN, token);
    }

    @NonNull
    public static String getUsername() {
        return Hawk.get(Constants.USERNAME, "");
    }

    public static void setUsername(@NonNull String username) {
        Hawk.put(Constants.USERNAME, username);
    }

    public static void removeToken() {
        Hawk.remove(Constants.TOKEN);
    }
}
