package com.ahaohuo.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahaohuo.R;
import com.ahaohuo.model.BannerModel;
import com.ahaohuo.model.GridModel;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by xyb on 2017/7/20.
 */

public class SubAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private static final int IMG_lAYOUT = 1;
    private static final int IMG_ITEM_lAYOUT = 2;

    private Context mContext;

    private LayoutHelper mLayoutHelper;


    private LinearLayout.LayoutParams mLayoutParams;
    private LinearLayout.LayoutParams mIMGLayoutParams;

    private List<GridModel.DataBean> dataBeans;
    private List<BannerModel.DataBean> bannerModels;

    public void setGridData(List<GridModel.DataBean> dataBeans) {
        this.dataBeans = dataBeans;
        notifyDataSetChanged();
    }


    public SubAdapter(Context context, LayoutHelper layoutHelper) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        this.mIMGLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == IMG_lAYOUT) {
            return new MainIMGViewHolder(LayoutInflater.from(mContext).inflate(R.layout.v_layout_item_two, parent, false));
        } else if(viewType == IMG_ITEM_lAYOUT){
            return new MainIMGItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.v_layout_item_three, parent, false));
        }else{
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.v_layout_item, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 4) {
            return IMG_lAYOUT;
        }else if(position > 4 && position < 9){
            return IMG_ITEM_lAYOUT;
        }
        return super.getItemViewType(position);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // only vertical
        int itemViewType = getItemViewType(position);
        if (itemViewType == IMG_lAYOUT) {
            holder.itemView.setLayoutParams(
                    new LinearLayout.LayoutParams(mIMGLayoutParams));
        } else {
            holder.itemView.setLayoutParams(
                    new LinearLayout.LayoutParams(mLayoutParams));
        }
    }


    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        GridModel.DataBean dataBean = dataBeans.get(position);
        int itemVIewType = getItemViewType(position);
        if (itemVIewType == IMG_lAYOUT) {
            MainIMGViewHolder imgViewHolder = (MainIMGViewHolder) holder;
            if (bannerModels != null) {
                String imgUrl = bannerModels.get(0).getTBannerUrl();
                Glide.with(mContext).load(imgUrl).into(imgViewHolder.ivImg);
            }
        }else if(itemVIewType == IMG_ITEM_lAYOUT){
            MainIMGItemViewHolder imgItemViewHolder = (MainIMGItemViewHolder) holder;
            Glide.with(mContext).load(dataBean.getTImgUrl()).into(imgItemViewHolder.ivItemImg);
        } else {
            MainViewHolder viewHolder = (MainViewHolder) holder;
            viewHolder.tvTitle.setText(dataBean.getTName());
            Glide.with(mContext).load(dataBean.getTImgUrl()).into(viewHolder.ivIcon);
        }
    }

    @Override
    public int getItemCount() {
        if (dataBeans != null) {
            return dataBeans.size();
        }
        return 0;
    }

    public void setIMGData(List<BannerModel.DataBean> bannerModels) {
        this.bannerModels = bannerModels;
        notifyDataSetChanged();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView ivIcon;

        public MainViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.title);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }

    private class MainIMGViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImg;

        public MainIMGViewHolder(View view) {
            super(view);
            ivImg = (ImageView) view.findViewById(R.id.iv_img);
        }
    }

    private class MainIMGItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivItemImg;

        public MainIMGItemViewHolder(View view) {
            super(view);
            ivItemImg = (ImageView) view.findViewById(R.id.iv_item_img);
        }
    }
}


