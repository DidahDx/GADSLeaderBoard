package com.didahdx.gadsleaderboard.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.didahdx.gadsleaderboard.R;

public class SplashScreenActivity extends AppCompatActivity implements Animator.AnimatorListener {
    ImageView imageView;
    private ObjectAnimator fadeAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        collectIds();
    }

    private void collectIds() {
        imageView = findViewById(R.id.imageView2);
        fadeAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0.0f, 1.0f);
        fadeAnimator.setDuration(3000);
        fadeAnimator.addListener(this);
        fadeAnimator.start();
    }


    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        if (animator == fadeAnimator) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}