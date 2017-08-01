package com.ahaohuo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.ButterKnife;

/**
 * Created by xyb on 2017/7/11.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private KProgressHUD hud;

    public abstract int getLayoutId();

    public abstract void initData(@Nullable Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData(savedInstanceState);
    }

    /**
     * 设置标题
     *
     * @param toolbar 标题控件
     * @param title   标题文本
     */
    protected void setTitle(Toolbar toolbar, String title) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            actionBar = getSupportActionBar();
            if (actionBar != null && null != title && !("").equals(title)) {
                actionBar.setTitle(title);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * 显示等待框
     * @param loading
     */
    public void showLoading(String loading){
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(loading)
                .setMaxProgress(100)
                .show();
    }

    /**
     * 隐藏等待框
     */
    public void hideLoading(){
        if(hud == null){
            return;
        }
        if(hud.isShowing()){
            hud.dismiss();
        }
    }
}
