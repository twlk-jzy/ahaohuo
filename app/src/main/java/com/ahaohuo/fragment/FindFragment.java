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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahaohuo.R;
import com.ahaohuo.base.BaseFragment;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.RangeGridLayoutHelper;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xyb on 2017/7/12.
 */

public class FindFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_find;
    }

    @Override
    public void initData() {
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        recyclerView.setAdapter(delegateAdapter);


        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        RangeGridLayoutHelper layoutHelper = new RangeGridLayoutHelper(4);
        layoutHelper.setWeights(new float[]{25f,25f,25f,25f});
//        layoutHelper.setPadding(15, 15, 15, 15);
        layoutHelper.setHGap(10);
        layoutHelper.setVGap(10);
//
//        RangeGridLayoutHelper.GridRangeStyle rangeStyle = new RangeGridLayoutHelper.GridRangeStyle();
//        rangeStyle.setBgColor(Color.RED);
//        rangeStyle.setSpanCount(2);
//        rangeStyle.setWeights(new float[]{46.665f});
//        rangeStyle.setPadding(15, 15, 15, 15);
//        rangeStyle.setMargin(15, 15, 15, 15);
//        rangeStyle.setHGap(5);
//        rangeStyle.setVGap(5);
//        layoutHelper.addRangeStyle(4, 7, rangeStyle);
//
//        RangeGridLayoutHelper.GridRangeStyle rangeStyle1 = new RangeGridLayoutHelper.GridRangeStyle();
//        rangeStyle1.setBgColor(Color.YELLOW);
//        rangeStyle1.setSpanCount(2);
//        rangeStyle1.setWeights(new float[]{46.665f});
//        rangeStyle1.setPadding(15, 15, 15, 15);
//        rangeStyle1.setMargin(15, 15, 15, 15);
//        rangeStyle1.setHGap(5);
//        rangeStyle1.setVGap(5);
//        layoutHelper.addRangeStyle(8, 11, rangeStyle1);
        adapters.add(new SubAdapter(getActivity(), layoutHelper, 16));


        delegateAdapter.setAdapters(adapters);
    }




    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private LinearLayout.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull LinearLayout.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
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
            ((TextView) holder.itemView.findViewById(R.id.title)).setText("哈哈"+Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
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
