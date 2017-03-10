package com.softmiracle.githubmvp.presenter.repo;

/**
 * Created by Denys on 01.03.2017.
 */

public interface GHRepoPresenter {

    void loadRepo(String user);

    void detachView();
}
