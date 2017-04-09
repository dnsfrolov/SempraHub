package com.softmiracle.githubmvp.utils;

import com.orhanobut.hawk.Hawk;

/**
 * Created by dnsfrolov on 01.04.2017.
 */

public class AccountPreferences {

    private static final String NAME = "AccountPrefs";
    private static final String TOKEN = "token";
    private static final String USERNAME = "username";

    public AccountPreferences() {
    }

    public static String getToken() {
        return Hawk.get(TOKEN, "");
    }

    public static void setToken(String token) {
        Hawk.put(TOKEN, token);
    }

    public static String getUsername() {
        return Hawk.get(USERNAME, "");
    }

    public static void setUsername(String username) {
        Hawk.put(USERNAME, username);
    }

    public static void clear() {
        Hawk.clear();
    }
}
