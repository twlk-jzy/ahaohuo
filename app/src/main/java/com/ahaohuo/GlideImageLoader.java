package com.ahaohuo;

import android.content.Context;
import android.widget.ImageView;

import com.ahaohuo.model.BannerModel;
import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by xyb on 2017/7/17.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        BannerModel.DataBean dataBean = (BannerModel.DataBean) path;
        Glide.with(context).load(dataBean.getTBannerUrl()).into(imageView);
    }
}
