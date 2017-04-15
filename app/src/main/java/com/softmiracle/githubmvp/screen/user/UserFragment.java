package com.softmiracle.githubmvp.screen.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        mCreatedAt.setText(user.getCreatedAt());
    }

    @Override
    public void showError(Throwable error) {

    }
}
