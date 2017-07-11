package com.ahaohuo.api.retrofit;

import rx.functions.Func1;

/**
 * Created by 10371 on 2016/9/13.
 */

public class ResultFunc<T> implements Func1<BaseModel<T>, T> {
    @Override
    public T call(BaseModel<T> tBaseModel) {
        if (!tBaseModel.code.equals("200")) {
            throw new ServerException(tBaseModel.code);
        }
        return tBaseModel.data;
    }
}
