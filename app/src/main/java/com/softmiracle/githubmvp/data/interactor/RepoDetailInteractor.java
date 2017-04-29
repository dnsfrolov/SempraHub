package com.softmiracle.githubmvp.data.interactor;

import com.softmiracle.githubmvp.data.models.Repo;

/**
 * Created by dnsfrolov on 29.04.2017.
 */

public interface RepoDetailInteractor {

    void getRepoDetail(String owner, String repo, InteractorCallback<Repo> callback);
}
