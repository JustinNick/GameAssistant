package com.example.johncheng.gameassistant.module.main.ui;

import android.content.Intent;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseActivity;
import com.example.johncheng.gameassistant.base.NetCallback;
import com.example.johncheng.gameassistant.common.constant.Constant;
import com.example.johncheng.gameassistant.common.net.HttpNet;
import com.example.johncheng.gameassistant.module.main.bean.Logininfo;
import com.google.gson.Gson;
import com.se7en.utils.SystemUtil;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private long mlExitTime;
    private TextView mLoginReg;
    private EditText metLoginPhone;
    private EditText metLoginPwd;
    private Button mbtnLogin;
    private CheckBox mcbLoginself;

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
        mcbLoginself = (CheckBox) findViewById(R.id.cb_login_loginself);
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
                String strPhone = metLoginPhone.getText().toString();
                String strPwd = metLoginPwd.getText().toString();
                byte[] bytes = Base64.encode(strPwd.getBytes(), Base64.DEFAULT);
                strPwd=new String(bytes);
                HashMap<String, String> params = new HashMap<>();
                params.put("username",strPhone);
                params.put("password",strPwd);

                HttpNet.doHttpRequest("POST", Constant.LOGIN_URL, params, new NetCallback() {

                    @Override
                    public void success(String strResult) {
                        doLoginInfo(strResult);
                    }

                    @Override
                    public void fail(String strFail) {

                    }
                });
                break;
        }
    }

    private void doLoginInfo(String strLogininfo) {
        Gson gson = new Gson();
        Logininfo logininfo = gson.fromJson(strLogininfo, Logininfo.class);
        String strState = logininfo.getState();
        if (strState.equals("success")){
            if (mcbLoginself.isChecked()){
                SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG,true);
            }else{
                SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG,false);
            }
            Toast.makeText(this,"登录成功！",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }else{
            SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG,false);
        }
    }
}
