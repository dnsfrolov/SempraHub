package com.softmiracle.githubmvp.data.interactor;

import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 02.05.2017.
 */

public interface WatchersInteractor {

    void getWatchers(String user, String repo, int page, InteractorCallback<List<User>> callback);
}
