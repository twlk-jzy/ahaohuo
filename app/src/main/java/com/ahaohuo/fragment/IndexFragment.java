package com.ahaohuo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahaohuo.GlideImageLoader;
import com.ahaohuo.R;
import com.ahaohuo.activity.AllProductActivity;
import com.ahaohuo.activity.WebViewActivity;
import com.ahaohuo.adapter.ProductViewHolder;
import com.ahaohuo.base.BaseFragment;
import com.ahaohuo.model.BannerModel;
import com.ahaohuo.model.ProductModel;
import com.ahaohuo.presenter.BannerPresenter;
import com.ahaohuo.presenter.ProductPresenter;
import com.ahaohuo.presenter.contract.BannerContract;
import com.ahaohuo.presenter.contract.ProductContract;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.swipe.SwipeRefreshLayout;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xyb on 2017/7/12.
 */

public class IndexFragment extends BaseFragment implements RecyclerArrayAdapter.OnMoreListener, SwipeRefreshLayout.OnRefreshListener, ProductContract.view, BannerContract.view {
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_all)
    TextView tvAll;
    private RecyclerArrayAdapter<ProductModel.DataBean> adapter;

    private ProductPresenter presenter;
    private BannerPresenter bannerPresenter;
    private List<BannerModel.DataBean> banners;

    @Override
    public int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_index;
    }

    @Override
    public void initData() {
        presenter = new ProductPresenter(this);
        bannerPresenter = new BannerPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, 0, 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        banner.setImageLoader(new GlideImageLoader());
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<ProductModel.DataBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ProductViewHolder(parent);
            }
        });
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                adapter.remove(position);
                return true;
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });

        adapter.setOnItemClickListener(position -> {
            ProductModel.DataBean dataBean = adapter.getItem(position);
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra("url", dataBean.getPCouponLink());
            startActivity(intent);
        });


        presenter.getProductList(0, 10);
        bannerPresenter.getBannerList(0);

        banner.setOnBannerListener(position -> {
            BannerModel.DataBean banner = banners.get(position);
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra("url", banner.getTTjUrl());
            startActivity(intent);
        });
    }


    @Override
    public void onRefresh() {
        presenter.getProductList(0, 10);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getProductList(0, 10);
        bannerPresenter.getBannerList(0);
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void onSuccess(ProductModel model) {
        List<ProductModel.DataBean> dataBeans = model.getData();
        if (dataBeans != null) {
            adapter.clear();
            adapter.addAll(dataBeans);
        }
    }

    @Override
    public void onSuccess(BannerModel model) {
        banners = model.getData();
        banner.setImages(model.getData());
        banner.start();
    }

    @Override
    public void onFail(String msg) {
        showToast(msg);
    }

    @Override
    public void onMoreShow() {

    }

    @Override
    public void onMoreClick() {

    }

    @OnClick({R.id.tv_all})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                Intent intent = new Intent(getActivity(), AllProductActivity.class);
                startActivity(intent);
                break;
        }
    }
}
