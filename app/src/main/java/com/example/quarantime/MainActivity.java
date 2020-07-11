package com.example.quarantime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    public static int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Notes: MainActivity", "onCreate");

        //setContentView(R.layout.activity_main);
        Log.d("Notes: MainActivity", "set content view");

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                Log.d("Notes: MainActivity", "homeIntent");
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}