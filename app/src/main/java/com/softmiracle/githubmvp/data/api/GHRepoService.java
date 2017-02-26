package com.softmiracle.githubmvp.data.api;

import com.softmiracle.githubmvp.data.models.GHRepo;

/**
 * Created by Denys on 25.02.2017.
 */

interface GHRepoService {

    interface GHRepoCallback<T> {

        void onSuccess(T response);

        void onError(Throwable error);
    }

    void getRepo(String user, GHRepoCallback<GHRepo> callback);
}
