package com.ahaohuo.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahaohuo.R;
import com.ahaohuo.base.BaseFragment;
import com.ahaohuo.model.GridModel;
import com.ahaohuo.presenter.FindPresenter;
import com.ahaohuo.presenter.contract.FindContract;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.RangeGridLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xyb on 2017/7/12.
 */

public class FindFragment extends BaseFragment implements FindContract.view {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private FindPresenter presenter;
    private SubAdapter subAdapter;

    @Override
    public int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_find;
    }

    @Override
    public void initData() {
        presenter = new FindPresenter(this);

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
        rangeStyle.setSpanCount(2);
        rangeStyle.setWeights(new float[]{46.665f});
        rangeStyle.setPadding(15, 15, 15, 15);
        rangeStyle.setMargin(15, 15, 15, 15);
        rangeStyle.setHGap(5);
        rangeStyle.setVGap(5);
        layoutHelper.addRangeStyle(4, 7, rangeStyle);


        RangeGridLayoutHelper.GridRangeStyle rangeStyle1 = new RangeGridLayoutHelper.GridRangeStyle();
        rangeStyle1.setBgColor(Color.YELLOW);
        rangeStyle1.setSpanCount(2);
        rangeStyle1.setWeights(new float[]{46.665f});
        rangeStyle1.setPadding(15, 15, 15, 15);
        rangeStyle1.setMargin(15, 15, 15, 15);
        rangeStyle1.setHGap(5);
        rangeStyle1.setVGap(5);
        layoutHelper.addRangeStyle(8, 11, rangeStyle1);
        subAdapter = new SubAdapter(getActivity(), layoutHelper, 16);
        adapters.add(subAdapter);


        delegateAdapter.setAdapters(adapters);

        presenter.getGridList();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getGridList();
    }

    @Override
    public void onSuccess(GridModel model) {
        List<GridModel.DataBean> gridModels = model.getData();
        if (gridModels != null) {
            subAdapter.setGridData(gridModels);
        }
    }

    @Override
    public void onFail(String msg) {
        showToast(msg);
    }


    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private LinearLayout.LayoutParams mLayoutParams;

        private List<GridModel.DataBean> dataBeans;

        private void setGridData(List<GridModel.DataBean> dataBeans) {
            this.dataBeans = dataBeans;
            notifyDataSetChanged();
        }


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull LinearLayout.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.v_layout_item, parent, false));
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new LinearLayout.LayoutParams(mLayoutParams));
        }


        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            GridModel.DataBean dataBean = dataBeans.get(position);
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(dataBean.getTName());

            ImageView ivIcon = ((ImageView) holder.itemView.findViewById(R.id.iv_icon));


            Glide.with(mContext).load(dataBean.getTImgUrl()).into(ivIcon);
        }

        @Override
        public int getItemCount() {
            if (dataBeans != null) {
                return dataBeans.size();
            }
            return 0;
        }
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public MainViewHolder(View itemView) {
            super(itemView);
            createdTimes++;
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }
}
