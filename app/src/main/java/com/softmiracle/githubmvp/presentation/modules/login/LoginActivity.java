package com.softmiracle.githubmvp.presentation.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.presentation.modules.home.HomeActivity;
import com.softmiracle.githubmvp.presentation.modules.settings.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    @BindView(R.id.et_username)
    EditText mUsername;

    @BindView(R.id.et_password)
    EditText mPassword;

    private LoginContract.LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SettingsActivity.getTheme(this, SettingsActivity.THEME_TYPE_LOGIN));
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void hideProgressIndicator() {

    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showError() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @OnClick(R.id.btn_signIn)
    public void onSignIn() {
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        mPresenter.login(username, password);
    }
}
