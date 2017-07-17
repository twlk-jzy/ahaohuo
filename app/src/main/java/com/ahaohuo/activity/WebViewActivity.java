package com.ahaohuo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ahaohuo.R;
import com.ahaohuo.base.BaseActivity;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {
    private static final String APP_CACAHE_DIRNAME = "/webcache";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.webView)
    WebView webView;
    private String url;


    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(toolbar,"加载中...");
        url = this.getIntent().getStringExtra("url");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        boolean netWork = NetUtils.isConnected(getApplicationContext());
//        if (netWork) {
//            //有网络使用默认缓存
//            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
//        } else {
//            //没有网络的时候 从本地取去缓存
//            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式
//        }
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        webSettings.setDatabaseEnabled(true);
        //设置数据库缓存路径
        webSettings.setDatabaseEnabled(true);
        //开启 Application Caches 功能
        webSettings.setAppCacheEnabled(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setAllowFileAccess(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        webSettings.setAppCachePath(cacheDirPath);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.loadUrl(url);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initListener() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("url-->>", url);
                if (url.endsWith("zhima.html")) {
                    //授权成功
                    Intent intent = new Intent();
                    intent.putExtra("isSuc", true);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
                setTitle(toolbar,title);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
        super.onBackPressed();
    }

}
