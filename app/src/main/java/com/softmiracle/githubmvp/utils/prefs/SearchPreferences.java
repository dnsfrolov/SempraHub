package com.softmiracle.githubmvp.utils.prefs;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;
import com.softmiracle.githubmvp.utils.Constants;

/**
 * Created by dnsfrolov on 12.05.2017.
 */

public class SearchPreferences {

    @NonNull
    public static String getSearchResult() {
        return Hawk.get(Constants.QUERY, "");
    }

    public static void setSearchResult(@NonNull String result) {
        Hawk.put(Constants.QUERY, result);
    }

    public static void removeSearchResult() {
        Hawk.remove(Constants.QUERY);
    }
}
