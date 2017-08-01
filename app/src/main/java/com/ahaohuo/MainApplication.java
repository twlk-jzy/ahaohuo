package com.ahaohuo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.ahaohuo.util.SpUtils;
import com.mob.MobApplication;
import com.tencent.bugly.Bugly;


/**
 * Created by xyb on 2017/7/7.
 */

public class MainApplication extends MobApplication {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        BuglyInit(getApplicationContext());
        SpUtils.init(getApplicationContext());
    }

    private void BuglyInit(Context context){

        Bugly.init(context, "e53847f78a", true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);


        // 安装tinker
//        Beta.installTinker();
    }

    public static Context getContext() {
        return context;
    }
}
