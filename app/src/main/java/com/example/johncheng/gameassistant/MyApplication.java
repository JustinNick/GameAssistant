package com.example.johncheng.gameassistant;

import android.app.Application;

import com.se7en.utils.SystemUtil;

/**
 * Created by johncheng on 2016/6/27.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemUtil.setContext(this);
    }
}
