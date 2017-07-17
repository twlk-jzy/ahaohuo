package com.ahaohuo.presenter;

import com.ahaohuo.api.ApiManager;
import com.ahaohuo.model.BannerModel;
import com.ahaohuo.presenter.contract.BannerContract;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xyb on 2017/7/17.
 */

public class BannerPresenter implements BannerContract.Presenter {
    private BannerContract.view view;
    public BannerPresenter(BannerContract.view view) {
        this.view = view;
    }

    @Override
    public void getBannerList(int config) {
        ApiManager.getInstance().getApiService().getBannerList(config)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerModel>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFail("获取banner数据失败");
                    }

                    @Override
                    public void onNext(BannerModel model) {
                        view.onSuccess(model);
                    }

                });
    }
}
