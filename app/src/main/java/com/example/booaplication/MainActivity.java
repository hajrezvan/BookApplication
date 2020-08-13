package com.example.booaplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    public void setView() {
        setBottomNavigationView();
        setFrameLayout();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_id, new HomeFragment()).commit();
    }

    public void setFrameLayout() {
        this.frameLayout = (FrameLayout) findViewById(R.id.frame_id);
    }

    public void setBottomNavigationView() {
        this.bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int navigationID = item.getItemId();

        switch (navigationID) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_id, new HomeFragment()).commit();
                break;

            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_id, new SearchFragment()).commit();
                break;

            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_id, new ProfileFragment()).commit();
                break;
        }

        return true;
    }
}