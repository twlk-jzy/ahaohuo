package com.ahaohuo.presenter.contract;

import com.ahaohuo.model.LoginModel;

public interface LoginContract {
    interface Presenter {
        void login(String userPhone,String userPwd);
    }

    interface view {
        void onSuccess(LoginModel model);

        void onFail(String msg);
    }
}