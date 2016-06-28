package com.example.johncheng.gameassistant.module.main.ui;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private long mlExitTime;
    private TextView mLoginReg;
    private EditText metLoginPhone;
    private EditText metLoginPwd;
    private Button mbtnLogin;

    @Override
    protected int setViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void findViews() {
        mLoginReg = (TextView) findViewById(R.id.tv_login_reg);
        metLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        metLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        mbtnLogin = (Button) findViewById(R.id.btn_login);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvents() {
        mLoginReg.setOnClickListener(this);
        mbtnLogin.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long lcurTime = System.currentTimeMillis();
            if ((lcurTime - mlExitTime) > 2000) {
                Toast.makeText(LoginActivity.this, "再次点击退出程序！", Toast.LENGTH_SHORT).show();
                mlExitTime = lcurTime;
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_reg:
                startActivity(new Intent(LoginActivity.this, UserRegisterActivity.class));
                break;
            case R.id.btn_login:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;
        }
    }
}
