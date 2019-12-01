package com.hyy.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private MyReceiver2 receiver;

    public void registBR(View v) {
        if (receiver != null) {
            Toast.makeText(this, "接收器已注册", Toast.LENGTH_SHORT).show();
            return;
        }
        receiver = new MyReceiver2();
        registerReceiver(receiver, new IntentFilter("com.hyy.broadcasttest.MyReceiver.action1"));
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    public void unRegistBR(View v) {
        if (receiver == null) {
            Toast.makeText(this, "尚未注册接收器", Toast.LENGTH_SHORT).show();
            return;
        }
        unregisterReceiver(receiver);
        receiver = null;
        Toast.makeText(this, "取消成功", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(receiver!=null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
