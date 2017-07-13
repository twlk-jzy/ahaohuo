package com.ahaohuo.api;


import com.ahaohuo.api.retrofit.CacheInterceptor;
import com.ahaohuo.api.retrofit.HttpLoggingInterceptor;
import com.ahaohuo.api.retrofit.JsonConverterFactory;
import com.ahaohuo.config.AppConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by 10371 on 2016/9/13.
 */
public class ApiManager {
    private static ApiManager ourInstance;
    private static ApiService apiService;

    /**
     * 获取ApiManager实例
     *
     * @return
     */
    public static ApiManager getInstance() {
        if (ourInstance == null) {
            synchronized (ApiManager.class) {
                if (ourInstance == null) {
                    ourInstance = new ApiManager();
                }
            }
        }
        return ourInstance;
    }

    /**
     * 获取ApiService实例
     *
     * @return
     */
    public ApiService getApiService() {
        if (apiService == null) {
            synchronized (ApiManager.class) {
                if (apiService == null) {
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(loggingInterceptor)
                            .addNetworkInterceptor(new CacheInterceptor())
                            .build();
                    apiService = new Retrofit.Builder()
                            .baseUrl(AppConfig.BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(JsonConverterFactory.create())
                            .client(client)
                            .build().create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
