package com.example.cc.canacollector.app;

import android.app.Application;

import com.example.cc.canacollector.helper.ParseUtils;

/**
 * Created by Breno on 1/3/2016.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // register with parse
        ParseUtils.registerParse(this);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
