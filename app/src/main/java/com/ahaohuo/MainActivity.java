package com.ahaohuo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ahaohuo.base.BaseActivity;
import com.ahaohuo.fragment.FindFragment;
import com.ahaohuo.fragment.IndexFragment;
import com.ahaohuo.fragment.MyFragment;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;


public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_layout)
    FrameLayout flLayout;
    @BindView(R.id.tab)
    PageBottomTabLayout tab;
    private IndexFragment indexFragment;
    private Fragment currentFragment;
    private FindFragment findFragment;
    private MyFragment myFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (findViewById(R.id.fl_layout) != null) {
            if (savedInstanceState != null) {
                return;
            }
            indexFragment = new IndexFragment();
            currentFragment = indexFragment;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_layout, indexFragment).commit();
        }

        NavigationController navigationController = tab.material()
                .addItem(R.mipmap.icon_index, "首页")
                .addItem(R.mipmap.icon_find, "发现")
                .addItem(R.mipmap.icon_my, "我的")
                .build();

        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int i, int i1) {
                switch (i) {
                    case 0:
                        if (indexFragment == null) {
                            indexFragment = new IndexFragment();
                        }
                        showFragment(currentFragment, indexFragment);
                        break;
                    case 1:
                        if (findFragment == null) {
                            findFragment = new FindFragment();
                        }
                        showFragment(currentFragment, findFragment);
                        break;
                    case 2:
                        if (myFragment == null) {
                            myFragment = new MyFragment();
                        }
                        showFragment(currentFragment, myFragment);
                        break;
                }
            }

            @Override
            public void onRepeat(int i) {

            }
        });

    }

    private void showFragment(Fragment from, Fragment to) {
        currentFragment = to;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        boolean isAdded = to.isAdded();
        if (!isAdded) {
            transaction.hide(from).add(R.id.fl_layout, to).show(to).commitAllowingStateLoss();
        } else {
            transaction.hide(from).show(to).commitAllowingStateLoss();
        }
    }

}
