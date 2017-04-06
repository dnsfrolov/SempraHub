package com.softmiracle.githubmvp.screen.repo;

import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

/**
 * Created by Denys on 01.03.2017.
 */

public interface RepoView {

    void showProgressIndicator();

    void hideProgressIndicator();

    void showRepo(List<Repo> repoList);

    void showError(Throwable error);
}
