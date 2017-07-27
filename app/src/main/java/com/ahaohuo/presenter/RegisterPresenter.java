package com.ahaohuo.presenter;

import com.ahaohuo.api.ApiManager;
import com.ahaohuo.base.BaseModel;
import com.ahaohuo.presenter.contract.RegisterContract;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.view view;

    public RegisterPresenter(RegisterContract.view view) {
        this.view = view;
    }

    @Override
    public void register(String userName, String userPhone, String userPwd) {
        ApiManager.getInstance().getApiService().register(userName,userPhone,userPwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFail("注册失败"+e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(BaseModel model) {
                        view.onSuccess(model);
                    }

                });
    }
}