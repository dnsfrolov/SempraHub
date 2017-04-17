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
import com.softmiracle.githubmvp.utils.AccountPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragment extends Fragment implements UserView {

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
    LinearLayout mLocationView;
    @BindView(R.id.ll_email_user_info)
    LinearLayout mEmailView;
    @BindView(R.id.ll_company_user_info)
    LinearLayout mCompanyView;
    @BindView(R.id.ll_blog_user_info)
    LinearLayout mBlogView;
    @BindView(R.id.ll_bio_user_info)
    LinearLayout mBioView;

    private UserPresenter presenter;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, root);

        presenter = new UserPresenterImpl(this);
        presenter.loadUser(AccountPreferences.getUsername());
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
        Glide.with(this).load(user.getAvatar()).into(mAvatar);
        mUsername.setText(user.getName());
        mCreatedAt.setText(DateFormat.getDateFormat(getActivity()).format(user.getCreatedAt()));
        mFollowers.setText(user.getFollowers());
        mFollowing.setText(user.getFollowing());
        mRepos.setText(user.getPublicRepos());

        if (user.getLocation() != null) {
            mLocation.setText(user.getLocation());
        } else {
            mLocationView.setVisibility(View.GONE);
        }

        if (user.getEmail() != null) {
            mEmail.setText(user.getEmail());
        } else {
            mEmailView.setVisibility(View.GONE);
        }

        if (user.getCompany() != null) {
            mCompany.setText(user.getCompany());
        } else {
            mCompanyView.setVisibility(View.GONE);
        }

        if (user.getBio() != null) {
            mBio.setText(user.getBio());
        } else {
            mBioView.setVisibility(View.GONE);
        }

        if (user.getBlog() != null) {
            mBlog.setText(user.getBlog());
        } else {
            mBlogView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(Throwable error) {

    }
}
