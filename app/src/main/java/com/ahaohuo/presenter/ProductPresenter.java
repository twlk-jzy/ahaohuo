package com.ahaohuo.presenter;

import com.ahaohuo.api.ApiManager;
import com.ahaohuo.model.ProductModel;
import com.ahaohuo.presenter.contract.ProductContract;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xyb on 2017/7/11.
 */

public class ProductPresenter implements ProductContract.presenter {

    private final ProductContract.view view;

    public ProductPresenter(ProductContract.view view) {
        this.view = view;
    }

    @Override
    public void getProductList(int page, int pageSize) {
        ApiManager.getInstance().getApiService().getProductList(page,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductModel>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFail("获取数据失败");
                    }

                    @Override
                    public void onNext(ProductModel model) {
                        view.onSuccess(model);
                    }

                });
    }
}
