package com.didahdx.gadsleaderboard.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.didahdx.gadsleaderboard.BuildConfig;
import com.didahdx.gadsleaderboard.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    ActivityMainBinding binding;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        enableStrictMode();
        collectIds();
    }

    private void collectIds() {
        buttonSubmit = findViewById(R.id.btnSubmit);
        buttonSubmit.setOnClickListener(this);
    }

    private void enableStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .detectAll().penaltyLog().penaltyFlashScreen().penaltyDropBox()
                    .penaltyDeath()
                    .build();

            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSubmit) {
            startActivity(new Intent(this, SubmitActivity.class));
        }
    }
}