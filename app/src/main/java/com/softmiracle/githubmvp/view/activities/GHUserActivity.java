package com.softmiracle.githubmvp.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.data.models.GHUser;
import com.softmiracle.githubmvp.presenter.GHUserPresenter;
import com.softmiracle.githubmvp.presenter.GHUserPresenterImpl;
import com.softmiracle.githubmvp.view.GHUserView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GHUserActivity extends AppCompatActivity implements GHUserView {

    @BindView(R.id.iv_avatar)
    ImageView mAvatar;
    @BindView(R.id.tv_username)
    TextView mUsername;
    @BindView(R.id.tv_login)
    TextView mLogin;
    @BindView(R.id.tv_followers)
    TextView mFollowers;
    @BindView(R.id.tv_following)
    TextView mFollowing;
    @BindView(R.id.tv_email)
    TextView mEmail;

    private GHUserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        String string = bundle.getString("name");

        mPresenter = new GHUserPresenterImpl(this);
        mPresenter.loadUser(string);
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void showUserProfile(GHUser user) {
        setDataProfile(user);
        setAvatarProfile(user);
    }

    private void setAvatarProfile(GHUser user) {
        Glide.with(getBaseContext())
                .load(user.getAvatar())
                .into(mAvatar);
    }

    private void setDataProfile(GHUser user) {
        mUsername.setText("Username: " + user.getName());
        mLogin.setText("Login: " + user.getLogin());
        mFollowers.setText("Followers: " + user.getFollowers());
        mFollowing.setText("Following: " + user.getFollowing());
        mEmail.setText("Email: " + user.getEmail());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
