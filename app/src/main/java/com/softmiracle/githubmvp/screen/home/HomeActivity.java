package com.softmiracle.githubmvp.screen.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.SempraApplication;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.screen.user.ProfileActivity;
import com.softmiracle.githubmvp.screen.user.UserFragment;
import com.softmiracle.githubmvp.utils.AccountPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeContract.HomeView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.home_nav_view)
    NavigationView mNavigationView;

    private HomeContract.HomePresenter mPresenter;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        mPresenter = new HomePresenterImpl(this);
        mPresenter.loadUserInfo(AccountPreferences.getUsername());
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_my_profile:
                showUserProfile();
                break;
            case R.id.nav_my_repos:
                break;
            case R.id.nav_sign_out:
                AccountPreferences.removeToken();
                finish();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showUserInfo(User user) {
        View headerLayout = mNavigationView.getHeaderView(0);
        final ImageView avatarView = (ImageView) headerLayout.findViewById(R.id.nav_avatar_view);
        final TextView nameView = (TextView) headerLayout.findViewById(R.id.nav_name_view);

        nameView.setText(user.getName());
        Glide.with(getBaseContext()).load(user.getAvatar()).into(avatarView);
        avatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ProfileActivity.newIntent(SempraApplication.getInstance(), AccountPreferences.getUsername()));
            }
        });
    }

    public void showUserProfile() {
        startActivity(ProfileActivity.newIntent(this, AccountPreferences.getUsername()));
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void hideProgressIndicator() {

    }

    @Override
    public void showError(Throwable error) {

    }
}
