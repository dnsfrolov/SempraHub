package com.softmiracle.githubmvp.data.interactor;

import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 02.05.2017.
 */

public interface RepoStarredInteractor {

    void getRepoStarred(String user, String repo, int page, InteractorCallback<List<User>> callback);
}
