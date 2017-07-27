package com.ahaohuo.presenter.contract;

import com.ahaohuo.base.BaseModel;

public interface RegisterContract {
    interface Presenter {
        void register(String userName, String userPhone, String userPwd);
    }

    interface view {
        void onSuccess(BaseModel model);

        void onFail(String msg);
    }
}