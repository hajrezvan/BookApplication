package com.example.booaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity2 extends AppCompatActivity {
    private boolean isLogin;
    private static final String LOGIN = "Login";

    public SplashActivity2() {
        isLogin = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        new SplashActivity2();
        getData();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin) {
                    Intent intent = new Intent(SplashActivity2.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity2.this, LoginActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 4000);
    }

    public void getData() {
        SharedPreferences sharedPreferences = getSharedPreferences(LOGIN, MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("IsLogin", false);
    }
}