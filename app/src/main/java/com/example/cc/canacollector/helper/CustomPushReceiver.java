package com.example.cc.canacollector.helper;

import android.content.Context;
import android.content.Intent;

import com.example.cc.canacollector.MainActivity;
import com.parse.ParsePushBroadcastReceiver;

/**
 * Created by Breno on 1/3/2016.
 */
public class CustomPushReceiver extends ParsePushBroadcastReceiver {
    private final String TAG = CustomPushReceiver.class.getSimpleName();

    private Intent parseIntent;

    public CustomPushReceiver() {
        super();
    }

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);
    }

    @Override
    protected void onPushDismiss(Context context, Intent intent) {
        super.onPushDismiss(context, intent);
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }
}
