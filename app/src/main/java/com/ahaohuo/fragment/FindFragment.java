package com.ahaohuo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.ahaohuo.R;
import com.ahaohuo.adapter.SubAdapter;
import com.ahaohuo.base.BaseFragment;
import com.ahaohuo.model.BannerModel;
import com.ahaohuo.model.GridModel;
import com.ahaohuo.presenter.BannerPresenter;
import com.ahaohuo.presenter.FindPresenter;
import com.ahaohuo.presenter.contract.BannerContract;
import com.ahaohuo.presenter.contract.FindContract;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.RangeGridLayoutHelper;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xyb on 2017/7/12.
 */

public class FindFragment extends BaseFragment implements FindContract.view,BannerContract.view  {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private FindPresenter presenter;
    private SubAdapter subAdapter;

    private BannerPresenter bannerPresenter;
    @Override
    public int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_find;
    }

    @Override
    public void initData() {
        presenter = new FindPresenter(this);
        bannerPresenter = new BannerPresenter(this);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        recyclerView.setAdapter(delegateAdapter);


        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        RangeGridLayoutHelper layoutHelper = new RangeGridLayoutHelper(4);
        layoutHelper.setBgColor(Color.WHITE);
        layoutHelper.setWeights(new float[]{25f, 25f, 25f, 25f});
        layoutHelper.setHGap(10);
        layoutHelper.setVGap(10);


        RangeGridLayoutHelper.GridRangeStyle rangeStyle = new RangeGridLayoutHelper.GridRangeStyle();
        rangeStyle.setBgColor(Color.RED);
        rangeStyle.setSpanCount(1);
        rangeStyle.setWeights(new float[]{100f});
        layoutHelper.addRangeStyle(4, 4, rangeStyle);

        RangeGridLayoutHelper.GridRangeStyle rangeStyle1 = new RangeGridLayoutHelper.GridRangeStyle();
        rangeStyle1.setBgColor(Color.WHITE);
        rangeStyle1.setSpanCount(2);
        rangeStyle1.setWeights(new float[]{50f,50f});
        rangeStyle1.setHGap(5);
        rangeStyle1.setVGap(5);
        layoutHelper.addRangeStyle(5, 8, rangeStyle1);

        RangeGridLayoutHelper.GridRangeStyle rangeStyle2 = new RangeGridLayoutHelper.GridRangeStyle();
        rangeStyle2.setBgColor(Color.parseColor("#BDBDBD"));
        rangeStyle2.setSpanCount(4);
        rangeStyle2.setMargin(0,15,0,0);
        rangeStyle2.setWeights(new float[]{25f,25f,25f,25f});
        rangeStyle2.setHGap(2);
        rangeStyle2.setVGap(2);
        layoutHelper.addRangeStyle(9, 17, rangeStyle2);

        subAdapter = new SubAdapter(getActivity(), layoutHelper);
        adapters.add(subAdapter);

        delegateAdapter.setAdapters(adapters);

        presenter.getGridList();
        bannerPresenter.getBannerList(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getGridList();
        bannerPresenter.getBannerList(0);
    }

    @Override
    public void onSuccess(GridModel model) {
        List<GridModel.DataBean> gridModels = model.getData();
        if (gridModels != null) {
            subAdapter.setGridData(gridModels);
        }
    }

    @Override
    public void onSuccess(BannerModel model) {
        List<BannerModel.DataBean> bannerModels = model.getData();
        if(bannerModels != null){
            subAdapter.setIMGData(bannerModels);
        }
    }

    @Override
    public void onFail(String msg) {
        showToast(msg);
    }

}
