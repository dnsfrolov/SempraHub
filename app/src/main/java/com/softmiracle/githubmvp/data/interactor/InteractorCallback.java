package com.softmiracle.githubmvp.data.interactor;

/**
 * Created by dnsfrolov on 24.04.2017.
 */

public interface InteractorCallback<T> {

    void onSuccess(T response);

    void onError(Throwable error);
}
