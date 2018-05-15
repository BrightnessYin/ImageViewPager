package com.example.administrator.myapplication;

import android.app.Application;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by Administrator on 2018/5/11.
 */

public class MyApp extends Application {
    private static MyApp sMyApp;
    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp = this;
        BGASwipeBackManager.getInstance().init(this);
    }
}
