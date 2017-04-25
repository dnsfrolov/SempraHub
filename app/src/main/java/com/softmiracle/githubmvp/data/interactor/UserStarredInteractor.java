package com.softmiracle.githubmvp.data.interactor;

import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

/**
 * Created by dnsfrolov on 25.04.2017.
 */

public interface UserStarredInteractor {

    void getUserStarred(String user, int page, InteractorCallback<List<Repo>> callback);
}
