package com.softmiracle.githubmvp.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.softmiracle.githubmvp.screen.home.HomeActivity;
import com.softmiracle.githubmvp.screen.login.LoginActivity;


public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainPresenter = new MainPresenterImpl(this);
        mMainPresenter.checkAuthorized();
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        startActivity(new Intent(this, isAuthorized ? HomeActivity.class : LoginActivity.class));
        this.finish();
    }
}
