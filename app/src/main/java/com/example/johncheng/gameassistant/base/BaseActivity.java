package com.example.johncheng.gameassistant.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by johncheng on 2016/6/27.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewId());

        findViews();
        init();
        initEvents();
        loadData();
    }

    protected abstract int setViewId();

    protected abstract void findViews();

    protected abstract void init();

    protected abstract void initEvents();

    protected abstract void loadData();
}
