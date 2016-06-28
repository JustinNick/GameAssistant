package com.example.johncheng.gameassistant.module.main.ui;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseActivity;

/**
 * Created by johncheng on 2016/6/27.
 */
public class RBActivity extends BaseActivity implements View.OnClickListener {

    PopupWindow mrbWindow;
    private View mRbView;
    private Button startRgb;
    private TextView startLogin;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mrbWindow == null) {
            setAlpha(0.4f);
            mrbWindow = new PopupWindow(mRbView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
            mrbWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
    }

    protected void setAlpha(float fAlpha) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = fAlpha;
        getWindow().setAttributes(params);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mrbWindow != null) {
            mrbWindow.dismiss();
            mrbWindow = null;
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initEvents() {
        startRgb.setOnClickListener(this);
        startLogin.setOnClickListener(this);
    }

    @Override
    protected void init() {
    }

    @Override
    protected void findViews() {
        mRbView = LayoutInflater.from(this).inflate(R.layout.layout_pw_rb, null);
        startRgb = (Button) mRbView.findViewById(R.id.btn_startregister);
        startLogin = (TextView) mRbView.findViewById(R.id.tv_startlogin);
    }

    @Override
    protected int setViewId() {
        return R.layout.layout_rbactivity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startregister:
                startActivity(new Intent(RBActivity.this, UserRegisterActivity.class));
                break;
            case R.id.tv_startlogin:
                startActivity(new Intent(RBActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }
}
