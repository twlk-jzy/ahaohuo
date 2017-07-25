package com.ahaohuo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;

import com.ahaohuo.R;
import com.ahaohuo.base.BaseActivity;

import butterknife.BindView;

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(toolbar,"意见反馈");
    }

}
