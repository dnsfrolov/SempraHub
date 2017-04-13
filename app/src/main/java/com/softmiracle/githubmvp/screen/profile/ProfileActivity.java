package com.softmiracle.githubmvp.screen.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.adapters.ViewPagerAdapter;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.screen.repo.RepositoryFragment;
import com.softmiracle.githubmvp.screen.user.UserFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    /*@BindView(R.id.iv_avatar)
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
    Button mBtnRepo;*/

    @BindView(R.id.apptoolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    //private ProfilePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(UserFragment.newInstance());
        fragments.add(RepositoryFragment.newInstance());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        //mPresenter = new ProfilePresenterImpl(this);
        //mPresenter.loadUser(AccountPreferences.getUsername());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void showProgressIndicator() {
    }

    @Override
    public void hideProgressIndicator() {
    }

    @Override
    public void showUserProfile(User user) {
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mPresenter.detachView();
    }
}
