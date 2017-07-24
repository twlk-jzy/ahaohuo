package com.ahaohuo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ahaohuo.R;
import com.ahaohuo.base.BaseActivity;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.et_sms_code)
    EditText etSmsCode;
    @BindView(R.id.tv_send_sms_code)
    TextView tvSendSmsCode;
    @BindView(R.id.et_user_pwd)
    EditText etUserPwd;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(toolbar,"注册");
    }

}
