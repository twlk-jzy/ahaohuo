package com.ahaohuo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ahaohuo.R;
import com.ahaohuo.activity.AboutUsActivity;
import com.ahaohuo.activity.FeedBackActivity;
import com.ahaohuo.activity.SettingActivity;
import com.ahaohuo.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by xyb on 2017/7/12.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.tv_about)
    TextView tvAbout;

    @Override
    public int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_my;
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_setting,R.id.tv_feedback,R.id.tv_about})
    public void click(View view){
        Class clazz = null;
        switch (view.getId()){
            case R.id.tv_setting:
                clazz = SettingActivity.class;
                break;
            case R.id.tv_feedback:
                clazz = FeedBackActivity.class;
                break;
            case R.id.tv_about:
                clazz = AboutUsActivity.class;
                break;
        }
        if(clazz != null){
            Intent intent = new Intent(getActivity(), clazz);
            startActivity(intent);
        }
    }
}
