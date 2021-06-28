package com.android.rohitt.asiancountriesinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView asia;
    private TextView countries;
    private Animation slidefromleft;
    private Animation slidefromright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        asia = findViewById(R.id.asiatext);
        countries = findViewById(R.id.countriestext);
        slidefromleft = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_from_left);
        slidefromright = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_from_right);

        asia.setAnimation(slidefromleft);
        countries.setAnimation(slidefromright);
        slidefromright.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SystemClock.sleep(1000);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}