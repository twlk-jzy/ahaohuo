package com.ahaohuo.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.ahaohuo.base.BaseModel;
import com.ahaohuo.presenter.RegisterPresenter;
import com.ahaohuo.presenter.contract.RegisterContract;
import com.ahaohuo.util.AppUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity implements RegisterContract.view {

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


    private RegisterPresenter presenter;
    private String userName;
    private String phone;
    private String userPwd;
    private String smsCode;


    private CountDownTimer countDownTimer;
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(toolbar, "注册");
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvSendSmsCode.setClickable(false);
                tvSendSmsCode.setText("   "+millisUntilFinished / 1000 +"秒 ");
            }

            @Override
            public void onFinish() {
                tvSendSmsCode.setClickable(true);
                tvSendSmsCode.setText(" 重新发送 ");
            }
        };
        presenter = new RegisterPresenter(this);

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                hideLoading();
                if(result == SMSSDK.RESULT_COMPLETE){
                    boolean smart = false;
                    if(data instanceof Boolean){
                        smart = (Boolean)data;
                    }
                    if(smart) {
                        //通过智能验证
                        //执行注册操作
                        registerUser();
                    } else{
                        switch (event){
                            case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showToast("获取验证码成功");
                                    }
                                });
                                break;
                            case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                                //校验验证码，返回校验的手机和国家代码
                                registerUser();
                                break;
                            case SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE:
                                //请求发送语音验证码
                                break;
                        }
                    }
                }else{
                    try {
                        Throwable throwable = (Throwable) data;
                        JSONObject object = new JSONObject(throwable.getMessage());
                        final String des = object.optString("detail");//错误描述
                        int status = object.optInt("status");//错误代码
                        if (status > 0 && !TextUtils.isEmpty(des)) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("验证码错误");
                                }
                            });
                            return;
                        }
                        registerUser();
                    } catch (Exception e) {
                        //do something
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("短信验证码验证失败");
                            }
                        });
                    }
                }
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    /**
     * 注册用户操作
     */
    private void registerUser() {

        //注册
        presenter.register(userName, phone, userPwd);
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
        userName = etUserName.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            showToast("请输入用户昵称");
            return;
        }
        phone = etUserPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return;
        }
        if (!AppUtils.isPhone(phone)) {
            showToast("手机号格式不正确");
            return;
        }
        smsCode = etSmsCode.getText().toString();

        if (TextUtils.isEmpty(smsCode)) {
            showToast("请输入短信验证码");
            return;
        }
        userPwd = etUserPwd.getText().toString();
        if (TextUtils.isEmpty(userPwd)) {
            showToast("请输入密码");
            return;
        }
        if (userPwd.length() < 6) {
            showToast("密码长度为6-20位字符");
            return;
        }
        showLoading("注册中…");
        SMSSDK.submitVerificationCode("+86",phone,smsCode);

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
        showLoading("发送短信中…");
        countDownTimer.start();
        //发送短信验证码
        SMSSDK.getVerificationCode("+86", phone);
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
        countDownTimer.cancel();
    }

    @Override
    public void onSuccess(BaseModel model) {
        hideLoading();
        showToast(model.getMsg());
        if (model.getCode().equals("200")) {
            finish();
        }
    }

    @Override
    public void onFail(String msg) {
        hideLoading();
        showToast(msg);
    }



}
