package com.softmiracle.githubmvp.repo;

/**
 * Created by Denys on 01.03.2017.
 */

public interface GHRepoPresenter {

    void loadRepo(String user);

    void detachView();
}
