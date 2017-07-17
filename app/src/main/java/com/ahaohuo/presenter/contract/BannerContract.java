package com.ahaohuo.presenter.contract;

import com.ahaohuo.model.BannerModel;

/**
 * Created by xyb on 2017/7/17.
 */

public interface BannerContract {
    interface Presenter{
        void getBannerList(int config);
    }
    interface view{
        void onSuccess(BannerModel model);
        void onFail(String msg);
    }
}
