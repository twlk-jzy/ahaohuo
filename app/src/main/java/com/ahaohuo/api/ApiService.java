package com.ahaohuo.api;


import com.ahaohuo.api.retrofit.BaseModel;
import com.ahaohuo.config.AppUrl;
import com.ahaohuo.model.ProductModel;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author jzy
 * Date 2016/9/12
 */

public interface ApiService {

    @GET(AppUrl.GET_PRODUCT_LIST)
    Observable<ProductModel> getProductList(@Query("page") int page,@Query("pageSize") int pageSize);
}
