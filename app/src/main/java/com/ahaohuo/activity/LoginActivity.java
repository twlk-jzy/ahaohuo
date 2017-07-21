package com.ahaohuo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ahaohuo.R;
import com.ahaohuo.base.BaseActivity;
import com.ahaohuo.util.AppUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.et_user_account)
    EditText etUserAccount;
    @BindView(R.id.et_user_password)
    EditText etUserPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(toolbar, "登录");
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        String userAccount = etUserAccount.getText().toString();
        if (TextUtils.isEmpty(userAccount)) {
            showToast("请先输入手机号");
            return;
        }
        if (userAccount.length() < 11) {
            showToast("手机号位数不正确");
            return;
        }
        boolean isPhone = AppUtils.isPhone(userAccount);
        if (!isPhone) {
            showToast("手机号格式不正确");
            return;
        }
        String userPassword = etUserPassword.getText().toString();
        if (TextUtils.isEmpty(userPassword)) {
            showToast("请先输入密码");
            return;
        }
        if (userPassword.length() < 6 || userPassword.length() > 20) {
            showToast("密码长度为6-20位的字符");
            return;
        }
        //登录
    }
}
