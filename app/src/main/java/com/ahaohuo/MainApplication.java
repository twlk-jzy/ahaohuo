package com.ahaohuo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;


/**
 * Created by xyb on 2017/7/7.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BuglyInit(getApplicationContext());
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
}
