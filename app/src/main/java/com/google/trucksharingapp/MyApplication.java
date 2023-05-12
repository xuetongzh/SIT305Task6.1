package com.google.trucksharingapp;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.trucksharingapp.mmkv.KVConfigImpl;

import org.litepal.LitePal;

public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize LitePal
        LitePal.initialize(this);
        KVConfigImpl.init(this, "demo");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
