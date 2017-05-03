package com.softmiracle.githubmvp.data.interactor;

import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

/**
 * Created by dnsfrolov on 02.05.2017.
 */

public interface ForksInteractor {

    void getForks(String user, String repo, int page, InteractorCallback<List<Repo>> callback);
}
