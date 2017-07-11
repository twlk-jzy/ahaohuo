package com.ahaohuo.presenter.contract;

import com.ahaohuo.model.ProductModel;

import java.util.List;

/**
 * Created by xyb on 2017/7/11.
 */

public interface ProductContract {

    interface presenter{
        void getProductList(int page,int pageSize);
    }
    interface view{
        void onSuccess(ProductModel model);
        void onFail(String msg);
    }
}
