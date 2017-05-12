package com.softmiracle.githubmvp.data.interactor;

import com.softmiracle.githubmvp.data.models.Repo;
import com.softmiracle.githubmvp.data.models.Search;
import com.softmiracle.githubmvp.data.models.User;

/**
 * Created by dnsfrolov on 08.05.2017.
 */

public interface SearchInteractor {

    void searchRepo(String q, int page, InteractorCallback<Search<Repo>> callback);

    void searchUser(String q, int page, InteractorCallback<Search<User>> callback);
}
