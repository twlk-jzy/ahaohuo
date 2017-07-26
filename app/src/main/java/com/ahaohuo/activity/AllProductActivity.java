package com.ahaohuo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.ahaohuo.R;
import com.ahaohuo.adapter.ProductViewHolder;
import com.ahaohuo.base.BaseActivity;
import com.ahaohuo.model.ProductModel;
import com.ahaohuo.presenter.ProductPresenter;
import com.ahaohuo.presenter.contract.ProductContract;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.List;

import butterknife.BindView;

public class AllProductActivity extends BaseActivity implements RecyclerArrayAdapter.OnMoreListener,ProductContract.view, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    private ProductPresenter presenter;
    private RecyclerArrayAdapter<ProductModel.DataBean> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_all_product;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(toolbar,"全部商品");

        presenter = new ProductPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, 0, 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<ProductModel.DataBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ProductViewHolder(parent);
            }
        });
        recyclerView.setRefreshingColor(getResources().getColor(R.color.colorAccent));
        recyclerView.setRefreshListener(this);
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

            public void onErrorClick() {
                adapter.resumeMore();
            }
        });

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ProductModel.DataBean dataBean = adapter.getItem(position);
                Intent intent = new Intent(AllProductActivity.this, WebViewActivity.class);
                intent.putExtra("url", dataBean.getPCouponLink());
                startActivity(intent);
            }
        });


        presenter.getProductList(0, 10);
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
    public void onFail(String msg) {
        showToast(msg);
    }

    @Override
    public void onMoreShow() {
    }

    @Override
    public void onMoreClick() {
    }

    @Override
    public void onRefresh() {
        presenter.getProductList(0, 10);
    }
}
