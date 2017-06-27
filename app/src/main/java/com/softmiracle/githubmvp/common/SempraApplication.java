package com.softmiracle.githubmvp.common;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.softmiracle.githubmvp.BuildConfig;

/**
 * Created by dnsfrolov on 03.04.2017.
 */

public class SempraApplication extends Application {

    private static SempraApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .build();
    }

    public static SempraApplication getInstance() {
        return instance;
    }
}
