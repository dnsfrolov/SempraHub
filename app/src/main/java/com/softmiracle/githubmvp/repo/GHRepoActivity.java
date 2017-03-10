package com.softmiracle.githubmvp.repo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.race604.drawable.wave.WaveDrawable;
import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.data.models.GHRepo;
import com.softmiracle.githubmvp.adapters.GHRepoAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GHRepoActivity extends AppCompatActivity implements GHRepoView {

    @BindView(R.id.im_load_repo)
    ImageView loadView;
    @BindView(R.id.repo_list)
    RecyclerView recyclerView;

    private GHRepoPresenter mPresenter;
    private GHRepoAdapter mRepoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        ButterKnife.bind(this);

        setProgressIndicator();

        Bundle bundle = getIntent().getExtras();
        String mNameString = bundle.getString("name");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRepoAdapter = new GHRepoAdapter();
        recyclerView.setAdapter(mRepoAdapter);

        mPresenter = new GHRepoPresenterImpl(this);
        mPresenter.loadRepo(mNameString);
    }

    @Override
    public void showProgressIndicator() {
        loadView.setVisibility(View.VISIBLE);
    }

    public void setProgressIndicator() {
        WaveDrawable waveDrawable = new WaveDrawable(this, R.drawable.github_circle);
        waveDrawable.setIndeterminate(true);
        waveDrawable.setWaveSpeed(8);
        loadView.setImageDrawable(waveDrawable);
    }

    @Override
    public void hideProgressIndicator() {
        loadView.setVisibility(View.GONE);
    }

    @Override
    public void showRepo(List<GHRepo> repoList) {
        mRepoAdapter.setData(repoList);
        mRepoAdapter.notifyDataSetChanged();
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
}
