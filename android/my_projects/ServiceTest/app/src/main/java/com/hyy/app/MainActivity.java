package com.hyy.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hyy.app.R;
import com.hyy.app.local.MyService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //启动服务
    public void startMyService(View v) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        Toast.makeText(this, "start service", Toast.LENGTH_SHORT).show();
    }

    public void stopMyService(View v) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        Toast.makeText(this, "stop service", Toast.LENGTH_SHORT).show();
    }

    private ServiceConnection conn;

    //绑定服务
    public void bindMyService(View v) {
        Intent intent = new Intent(this, MyService.class);
        //创建连接对象
        if (conn == null) {
            conn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.e("TAG", "onServiceConnected()");
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.e("TAG", "onServiceDisconnected()");
                }

            };

            //绑定Service
            bindService(intent, conn, Context.BIND_AUTO_CREATE);

            Toast.makeText(this, "bind service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经bindservice", Toast.LENGTH_SHORT).show();
        }
    }

    //解绑服务
    public void unbindMyService(View v) {
        if (conn != null) {
            unbindService(conn);
            conn = null;
            Toast.makeText(this, "unbind service", 0).show();
        } else {
            Toast.makeText(this, "还没有bindservice", 0).show();
        }

    }

    //在Activity死亡之前调用
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            unbindService(conn);
            conn = null;
        }
    }

}
