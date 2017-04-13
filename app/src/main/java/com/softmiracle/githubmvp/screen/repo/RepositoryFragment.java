package com.softmiracle.githubmvp.screen.repo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmiracle.githubmvp.R;

public class RepositoryFragment extends Fragment {

    public static RepositoryFragment newInstance() {
        return new RepositoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repository, container, false);
    }
}
