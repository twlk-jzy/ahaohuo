package com.ahaohuo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ahaohuo.R;
import com.ahaohuo.base.BaseActivity;
import com.ahaohuo.util.AppUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

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
    private EventHandler eventHandler;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(toolbar, "注册");

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable)data;
                    String msg = throwable.getMessage();
                    showToast(msg);
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑  获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("获取验证码成功");
                            }
                        });
                    }else if(event  == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        //提交验证码成功
                    }
                }
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    @OnClick({R.id.btn_register, R.id.tv_send_sms_code})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                register();
                break;
            case R.id.tv_send_sms_code:
                sendSmsCode();
                break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        String userName = etUserName.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            showToast("请输入用户昵称");
            return;
        }
        String phone = etUserPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return;
        }
        if (!AppUtils.isPhone(phone)) {
            showToast("手机号格式不正确");
            return;
        }
        String smsCode = etSmsCode.getText().toString();

        if (TextUtils.isEmpty(smsCode)) {
            showToast("请输入短信验证码");
            return;
        }
        String userPwd = etUserPwd.getText().toString();
        if (TextUtils.isEmpty(userPwd)) {
            showToast("请输入密码");
            return;
        }
        if (userPwd.length() < 6) {
            showToast("密码长度为6-20位字符");
            return;
        }
        //注册
    }

    /**
     * 发送短信验证码
     */
    private void sendSmsCode() {
        String phone = etUserPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return;
        }
        if (!AppUtils.isPhone(phone)) {
            showToast("手机号格式不正确");
            return;
        }
        //发送短信验证码
        SMSSDK.getVerificationCode("+86",phone);
    }
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
