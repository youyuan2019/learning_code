package com.hyy.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver1 extends BroadcastReceiver {
    public MyReceiver1() {
        Log.e("TAG", "app.MyReceiver1()");
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TAG", "app.MyReceiver1.onReceive()" + intent.getStringExtra("action"));
    }

}
