package com.example.studinfo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Wellcomepage extends AppCompatActivity {
     private static int SPLASH=2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcomepage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                   startActivity(new Intent(Wellcomepage.this,MainActivity.class));
                   finish();
            }
        },SPLASH);
    }
}
