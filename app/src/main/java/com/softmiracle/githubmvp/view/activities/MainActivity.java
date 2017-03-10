package com.softmiracle.githubmvp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.softmiracle.githubmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText mEditUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void onClickUser(View view) {
        loadActivity();
    }

    public void loadActivity() {
        Intent intent = new Intent(MainActivity.this, GHUserActivity.class);
        intent.putExtra("name", mEditUsername.getText().toString());
        startActivity(intent);
    }
}
