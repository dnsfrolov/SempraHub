package com.softmiracle.githubmvp.data.api;

import com.softmiracle.githubmvp.data.models.GHRepo;

import java.util.List;

/**
 * Created by Denys on 25.02.2017.
 */

public interface GHRepoService {

    interface GHRepoCallback<T> {

        void onSuccess(List<GHRepo> repoList);

        void onError(Throwable error);
    }

    void getRepo(String user, GHRepoCallback<List<GHRepo>> callback);
}
