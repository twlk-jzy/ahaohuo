package com.ahaohuo.presenter;

import com.ahaohuo.api.ApiManager;
import com.ahaohuo.model.LoginModel;
import com.ahaohuo.presenter.contract.LoginContract;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.view view;

    public LoginPresenter(LoginContract.view view) {
        this.view = view;
    }


    @Override
    public void login(String userPhone, String userPwd) {
        ApiManager.getInstance().getApiService().userLogin(userPhone,userPwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginModel>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFail("登录失败");
                    }

                    @Override
                    public void onNext(LoginModel model) {
                        view.onSuccess(model);
                    }

                });
    }
}