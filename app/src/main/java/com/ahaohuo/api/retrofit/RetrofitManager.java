package com.ahaohuo.api.retrofit;



import com.ahaohuo.api.ApiService;
import com.ahaohuo.config.AppConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 10371 on 2016/9/13.
 */
public class RetrofitManager {
    private static RetrofitManager ourInstance;
    private final Retrofit retrofit;
    private final ApiService apiService;

    public static RetrofitManager getInstance() {
        if (ourInstance == null) {
            synchronized (RetrofitManager.class) {
                if (ourInstance == null) {
                    ourInstance = new RetrofitManager();
                }
            }
        }
        return ourInstance;
    }

    public ApiService getApiService() {
        return apiService;
    }

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
