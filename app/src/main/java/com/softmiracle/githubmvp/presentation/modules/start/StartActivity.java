package com.softmiracle.githubmvp.presentation.modules.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.softmiracle.githubmvp.presentation.modules.home.HomeActivity;
import com.softmiracle.githubmvp.presentation.modules.login.LoginActivity;
import com.softmiracle.githubmvp.presentation.modules.settings.SettingsActivity;


public class StartActivity extends AppCompatActivity implements StartContract.StartView {

    private StartContract.StartPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SettingsActivity.getTheme(this, SettingsActivity.THEME_TYPE_GLOBAL));
        mPresenter = new StartPresenterImpl(this);
        mPresenter.checkAuthorized();
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        Intent intent = new Intent(this, isAuthorized ? HomeActivity.class : LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
