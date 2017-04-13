package com.softmiracle.githubmvp.screen.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.softmiracle.githubmvp.screen.home.HomeActivity;
import com.softmiracle.githubmvp.screen.login.LoginActivity;


public class StartActivity extends AppCompatActivity implements StartView {

    private StartPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new StartPresenterImpl(this);
        mPresenter.checkAuthorized();
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        Intent intent = new Intent(this, isAuthorized ? HomeActivity.class : LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
