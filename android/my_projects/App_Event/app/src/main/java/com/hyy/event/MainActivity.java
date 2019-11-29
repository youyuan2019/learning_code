package com.hyy.event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main_test1).setOnClickListener((v) ->
                startActivity(new Intent(MainActivity.this, MotionEventTestActivity.class)));

        findViewById(R.id.btn_main_test2).setOnLongClickListener((v) -> {
                    startActivity(new Intent(MainActivity.this, KeyEventTestActivity.class));
                    return true;
                }
        );
    }
}
