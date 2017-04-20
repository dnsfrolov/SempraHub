package com.softmiracle.githubmvp.screen.repo;

/**
 * Created by Denys on 01.03.2017.
 */

public interface RepoPresenter {

    void loadRepo(String user, int page);

    void detachView();
}
