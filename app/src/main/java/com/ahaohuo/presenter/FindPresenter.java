package com.ahaohuo.presenter;

import com.ahaohuo.api.ApiManager;
import com.ahaohuo.model.GridModel;
import com.ahaohuo.presenter.contract.FindContract;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FindPresenter implements FindContract.Presenter {
    private FindContract.view view;

    public FindPresenter(FindContract.view view) {
        this.view = view;
    }


    @Override
    public void getGridList() {
        ApiManager.getInstance().getApiService().getGridList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GridModel>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFail("获取Grid数据失败");
                    }

                    @Override
                    public void onNext(GridModel model) {
                        view.onSuccess(model);
                    }

                });
    }
}