package com.softmiracle.githubmvp.screen.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.screen.user.followers.FollowersActivity;
import com.softmiracle.githubmvp.screen.user.following.FollowingActivity;
import com.softmiracle.githubmvp.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFragment extends Fragment implements UserContract.UserView {

    @BindView(R.id.avatar_user_info)
    ImageView mAvatar;

    @BindView(R.id.tv_username_user_info)
    TextView mUsername;

    @BindView(R.id.tv_member_user_info)
    TextView mCreatedAt;

    @BindView(R.id.tv_followers_user_info)
    TextView mFollowers;

    @BindView(R.id.tv_following_user_info)
    TextView mFollowing;

    @BindView(R.id.tv_repos_user_info)
    TextView mRepos;

    @BindView(R.id.tv_location_user_info)
    TextView mLocation;

    @BindView(R.id.tv_email_user_info)
    TextView mEmail;

    @BindView(R.id.tv_company_user_info)
    TextView mCompany;

    @BindView(R.id.tv_blog_user_info)
    TextView mBlog;

    @BindView(R.id.tv_bio_user_info)
    TextView mBio;

    @BindView(R.id.ll_location_user_info)
    LinearLayout mLocationLayout;

    @BindView(R.id.ll_email_user_info)
    LinearLayout mEmailLayout;

    @BindView(R.id.ll_company_user_info)
    LinearLayout mCompanyLayout;

    @BindView(R.id.ll_blog_user_info)
    LinearLayout mBlogLayout;

    @BindView(R.id.ll_bio_user_info)
    LinearLayout mBioLayout;

    @BindView(R.id.ll_container_user_info)
    LinearLayout llInfoContainer;

    private UserContract.UserPresenter mPresenter;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, root);

        mPresenter = new UserPresenterImpl(this);
        mPresenter.loadUser(getActivity().getIntent().getStringExtra(Constants.EXTRA_USERNAME));
        return root;
    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void hideProgressIndicator() {

    }

    @Override
    public void showUserProfile(User user) {
        getActivity().getIntent().putExtra(Constants.EXTRA_USERNAME, user.getLogin());
        Glide.with(this).load(user.getAvatar()).into(mAvatar);
        mUsername.setText(user.getName() != null ? user.getName() : user.getLogin());
        mCreatedAt.setText(DateFormat.getDateFormat(getActivity()).format(user.getCreatedAt()));
        mFollowers.setText(user.getFollowers());
        mFollowing.setText(user.getFollowing());
        mRepos.setText(user.getPublicRepos());

        if (user.getLocation() != null) {
            mLocation.setText(user.getLocation());
            llInfoContainer.setVisibility(View.VISIBLE);
        } else {
            mLocationLayout.setVisibility(View.GONE);
        }

        if (user.getEmail() != null) {
            mEmail.setText(user.getEmail());
            llInfoContainer.setVisibility(View.VISIBLE);
        } else {
            mEmailLayout.setVisibility(View.GONE);
        }

        if (user.getCompany() != null) {
            mCompany.setText(user.getCompany());
            llInfoContainer.setVisibility(View.VISIBLE);
        } else {
            mCompanyLayout.setVisibility(View.GONE);
        }

        if (user.getBio() != null) {
            mBio.setText(user.getBio());
            llInfoContainer.setVisibility(View.VISIBLE);
        } else {
            mBioLayout.setVisibility(View.GONE);
        }

        if (user.getBlog() != null && !user.getBlog().isEmpty()) {
            mBlog.setText(user.getBlog());
            llInfoContainer.setVisibility(View.VISIBLE);
        } else {
            mBlogLayout.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.ll_followers_user_info)
    void onFollowersClick() {
        startActivity(FollowersActivity.newIntent(getContext(),
                getActivity().getIntent().getStringExtra(Constants.EXTRA_USERNAME)));
    }

    @OnClick(R.id.ll_following_user_info)
    void onFollowingClick() {
        startActivity(FollowingActivity.newIntent(getContext(),
                getActivity().getIntent().getStringExtra(Constants.EXTRA_USERNAME)));
    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
