package com.softmiracle.githubmvp.screen.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.softmiracle.githubmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.et_username)
    EditText mUsername;
    @BindView(R.id.et_password)
    EditText mPassword;

    private LoginPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUsernameError() {

    }

    @Override
    public void showPasswordError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void onClickLogin(View view) {
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        mPresenter.login(username, password);
    }
}