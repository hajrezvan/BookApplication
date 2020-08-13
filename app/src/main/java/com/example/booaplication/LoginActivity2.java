package com.example.booaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity2 extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private String name_txt;
    private Button enter;
    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        name = findViewById(R.id.username_loginForm);
        enter = findViewById(R.id.enter_button);
        enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int viewID = view.getId();

        if (viewID == R.id.enter_button) {
            nameValidation();
        }
    }

    public void nameValidation() {
        name_txt = this.name.getText().toString().trim();

        if (name_txt.isEmpty()) {
            Toast.makeText(this, "نام خود را وارد کنید", Toast.LENGTH_SHORT).show();
        } else {
            saveName();
        }
    }

    public void saveName() {
        isLogin = !isLogin;
        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", name_txt);
        editor.putBoolean("IsLogin", isLogin);
        editor.apply();
        goToHome();
    }

    public void goToHome() {
        Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}