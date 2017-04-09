package com.softmiracle.githubmvp.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.softmiracle.githubmvp.screen.home.HomeActivity;
import com.softmiracle.githubmvp.screen.login.LoginActivity;


public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new MainPresenterImpl(this);
        mPresenter.checkAuthorized();
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        Intent intent = new Intent(this, isAuthorized ? HomeActivity.class : LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
