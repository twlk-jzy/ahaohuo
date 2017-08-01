package com.ahaohuo.activity;

import android.content.Intent;
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
import com.ahaohuo.config.AppKey;
import com.ahaohuo.model.LoginModel;
import com.ahaohuo.presenter.LoginPresenter;
import com.ahaohuo.presenter.contract.LoginContract;
import com.ahaohuo.util.AppUtils;
import com.ahaohuo.util.SpUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.view {

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

    private LoginPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(toolbar, "登录");

        presenter = new LoginPresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        String userPhone = SpUtils.getString(AppKey.USER_PHONE,null);
        if(!TextUtils.isEmpty(userPhone)){
            etUserAccount.setText(userPhone);
            etUserAccount.setSelection(etUserAccount.getText().length());
        }
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
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
        showLoading("登录中…");
        //保存下登录账号
        SpUtils.saveString(AppKey.USER_PHONE,userAccount);
        presenter.login(userAccount,userPassword);
    }

    @Override
    public void onSuccess(LoginModel model) {
        hideLoading();
        if(model.getCode().equals("200")){
            String userName = model.getData().getTUserName();
            SpUtils.saveString(AppKey.USER_NAEM,userName);
            finish();
        }
        showToast(model.getMsg());
    }

    @Override
    public void onFail(String msg) {
        hideLoading();
        showToast(msg);
    }
}
