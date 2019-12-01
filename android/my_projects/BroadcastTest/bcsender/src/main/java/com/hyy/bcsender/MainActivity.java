package com.hyy.bcsender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendNormalBC(View v) {
        Intent intent = new Intent("com.hyy.broadcasttest.MyReceiver.action1");
        intent.putExtra("action", "这是一个普通广播");
        // 从android 8.0（API26）开始，对清单文件中静态注册广播接收者增加了限制，除了例外的广播外，其他隐式广播不能接收
        // 这里使用setPackage定向发送广播，实现静态注册的广播可以接收
        intent.setPackage("com.hyy.broadcasttest");
        sendBroadcast(intent);
        Toast.makeText(this, "app发送一般广播完成", Toast.LENGTH_SHORT).show();
    }

    public void sendOrderBC(View v) {
        Intent intent = new Intent("com.hyy.broadcasttest.MyReceiver.action1");
        intent.putExtra("action", "这是一个有序广播");
        sendOrderedBroadcast(intent, null);
        Toast.makeText(this, "app发送有序广播完成", Toast.LENGTH_SHORT).show();
    }

}
