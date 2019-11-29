package com.hyy.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //监听back键
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //显示确定的dialog
            new AlertDialog.Builder(this)
                    .setMessage("你确定退出吗?")
                    .setNegativeButton("再看看", null)
                    .setPositiveButton("退出", (dialog, which) -> finish())
                    .show();
            return true;//不会退出了
        }
        return super.onKeyUp(keyCode, event);
    }
}
