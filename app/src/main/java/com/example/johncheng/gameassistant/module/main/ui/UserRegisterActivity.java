package com.example.johncheng.gameassistant.module.main.ui;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseActivity;

public class UserRegisterActivity extends BaseActivity implements View.OnClickListener {


    private TextView mtvAgree;
    private TextView mtvGetCode;
    private int miCnt = 120;
    private ImageView mregGoback;

    @Override
    protected int setViewId() {
        return R.layout.activity_user_register;
    }

    @Override
    protected void findViews() {
        mtvAgree = (TextView) findViewById(R.id.tv_reg);
        mtvGetCode = (TextView) findViewById(R.id.tv_getcode);
        mregGoback = (ImageView) findViewById(R.id.reg_goback);
        mtvAgree.setMovementMethod(new LinkMovementMethod());
        String strAgree = "我已阅读并同意接受协议";
        new SpannableString(strAgree);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvents() {
        mregGoback.setOnClickListener(this);
        mtvGetCode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mtvGetCode.setEnabled(false);
                new CountDownTimer(miCnt * 1000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        miCnt--;
                        mtvGetCode.setText(miCnt + "");
                    }

                    @Override
                    public void onFinish() {
                        mtvGetCode.setEnabled(true);
                        miCnt = 120;
                        mtvGetCode.setText("获取验证码");
                    }
                }.start();
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_goback:
                finish();
                break;
        }
    }
}
