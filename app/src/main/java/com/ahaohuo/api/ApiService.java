package com.ahaohuo.api;


import com.ahaohuo.config.AppUrl;
import com.ahaohuo.model.BannerModel;
import com.ahaohuo.model.GridModel;
import com.ahaohuo.model.ProductModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author jzy
 * Date 2016/9/12
 */

public interface ApiService {

    @GET(AppUrl.GET_PRODUCT_LIST)
    Observable<ProductModel> getProductList(@Query("page") int page,@Query("pageSize") int pageSize);

    @GET(AppUrl.GET_BANNER_LIST)
    Observable<BannerModel> getBannerList(@Query("config") int config);

    @GET(AppUrl.GET_GRID_LIST)
    Observable<GridModel> getGridList();
}
