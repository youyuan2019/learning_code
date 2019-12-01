package com.hyy.app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TAG", "app2.MyReceiver2.onReceive()" + intent.getStringExtra("action"));
    }
}
