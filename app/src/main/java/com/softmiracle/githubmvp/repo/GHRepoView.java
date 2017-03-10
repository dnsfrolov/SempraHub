package com.softmiracle.githubmvp.repo;

import com.softmiracle.githubmvp.data.models.GHRepo;

import java.util.List;

/**
 * Created by Denys on 01.03.2017.
 */

public interface GHRepoView {

    void showProgressIndicator();

    void hideProgressIndicator();

    void showRepo(List<GHRepo> repoList);

    void showError(Throwable error);
}
