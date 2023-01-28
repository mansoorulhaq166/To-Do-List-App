package com.example.todolist.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.todolist.R;

import java.util.Objects;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(Splash.this, Task_Main.class));
            finish();
        }, 3000);
    }
}