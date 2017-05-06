package com.softmiracle.githubmvp.utils;

import android.support.annotation.NonNull;
import android.util.Base64;

/**
 * Created by dnsfrolov on 05.05.2017.
 */

public final class AuthorizationUtils {

    @NonNull
    public static String createAuth(@NonNull String login, @NonNull String password) {

        String combinedStr = String.format("%1$s:%2$s", login, password);
        String basicAuth = Constants.BASIC_AUTH + Base64.encodeToString(combinedStr.getBytes(), Base64.DEFAULT);

        return basicAuth.trim();
    }
}
