package com.softmiracle.githubmvp.data.interactor;

import com.softmiracle.githubmvp.data.models.User;

import java.util.List;

/**
 * Created by dnsfrolov on 30.04.2017.
 */

public interface FollowingInteractor {

    void getFollowing(String user, int page, InteractorCallback<List<User>> callback);
}
