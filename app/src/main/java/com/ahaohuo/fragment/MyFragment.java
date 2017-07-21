package com.ahaohuo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ahaohuo.R;
import com.ahaohuo.activity.LoginActivity;
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
        switch (view.getId()){
            case R.id.tv_setting:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_feedback:

                break;
            case R.id.tv_about:

                break;
        }
    }
}
