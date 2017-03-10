package com.softmiracle.githubmvp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.race604.drawable.wave.WaveDrawable;
import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.data.models.GHUser;
import com.softmiracle.githubmvp.presenter.user.GHUserPresenter;
import com.softmiracle.githubmvp.presenter.user.GHUserPresenterImpl;
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
    @BindView(R.id.im_load_user)
    ImageView mLoadView;
    @BindView(R.id.btn_repo)
    Button mBtnRepo;

    private String mNameString;
    private GHUserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        setProgressIndicator();

        Bundle bundle = getIntent().getExtras();
        mNameString = bundle.getString("name");

        mPresenter = new GHUserPresenterImpl(this);
        mPresenter.loadUser(mNameString);
    }

    @Override
    public void showProgressIndicator() {
        mBtnRepo.setVisibility(View.GONE);
        mLoadView.setVisibility(View.VISIBLE);
    }

    public void setProgressIndicator() {
        WaveDrawable waveDrawable = new WaveDrawable(this, R.drawable.github_circle);
        waveDrawable.setIndeterminate(true);
        waveDrawable.setWaveSpeed(8);
        mLoadView.setImageDrawable(waveDrawable);
    }

    @Override
    public void hideProgressIndicator() {
        mBtnRepo.setVisibility(View.VISIBLE);
        mLoadView.setVisibility(View.GONE);
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
    public void showError(Throwable error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void loadActivity() {
        Intent intent = new Intent(GHUserActivity.this, GHRepoActivity.class);
        intent.putExtra("name", mNameString);
        startActivity(intent);
    }

    public void onClickRepo(View view) {
        loadActivity();
    }
}
