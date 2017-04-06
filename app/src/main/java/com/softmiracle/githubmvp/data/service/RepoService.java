package com.softmiracle.githubmvp.data.service;

import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

/**
 * Created by Denys on 25.02.2017.
 */

public interface RepoService {

    interface RepoCallback<T> {

        void onSuccess(List<Repo> repoList);

        void onError(Throwable error);
    }

    void getRepo(String user, RepoCallback<List<Repo>> callback);
}
