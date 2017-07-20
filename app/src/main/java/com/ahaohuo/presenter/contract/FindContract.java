package com.ahaohuo.presenter.contract;

import com.ahaohuo.model.GridModel;

/**
 * Created by xyb on 2017/7/20.
 */

public interface FindContract {
    interface Presenter{
        void getGridList();
    }
    interface view{
        void onSuccess(GridModel model);
        void onFail(String msg);
    }
}
