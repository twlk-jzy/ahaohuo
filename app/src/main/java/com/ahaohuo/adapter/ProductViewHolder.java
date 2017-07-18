package com.ahaohuo.adapter;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.icu.math.BigDecimal;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahaohuo.R;
import com.ahaohuo.model.ProductModel;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by xyb on 2017/7/10.
 */

public class ProductViewHolder extends BaseViewHolder<ProductModel.DataBean> {
    private ImageView proMainImg;
    private TextView proTitle;
    private TextView proSrcPrice;
    private TextView proMonthSale;
    private TextView couponMoney;
    private TextView couponAfterPrice;
    public ProductViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_product);
        proMainImg = $(R.id.iv_pro_main_img);
        proTitle = $(R.id.tv_pro_title);
        proSrcPrice = $(R.id.tv_pro_src_price);
        proMonthSale = $(R.id.tv_pro_month_sale);
        couponMoney = $(R.id.tv_coupon_money);
        couponAfterPrice = $(R.id.tv_coupon_after_price);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void setData(ProductModel.DataBean data) {
        super.setData(data);
        Glide.with(getContext()).load(data.getPMainImg()).into(proMainImg);

        String platformType = data.getPPlatformType();

        String pName = data.getPName();
        if(!TextUtils.isEmpty(platformType)){
            if(platformType.equals("天猫")){
                setTextTitle(proTitle,R.mipmap.icon_tmall_logo,pName);
            }else if(platformType.equals("淘宝")){
                setTextTitle(proTitle,R.mipmap.icon_taobao,pName);
            }
        }

        proSrcPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        String srcPrice = data.getPPrice();
        proSrcPrice.setText(srcPrice);
        proMonthSale.setText("月销 "+data.getPMonthSale()+" 件");
        String couponMoneyText = data.getPCouponMoney();
        if(!TextUtils.isEmpty(couponMoneyText)){
            String[] coupons = couponMoneyText.split("减");
            couponMoney.setText(coupons[1]);

            String couponMoney = null;
            if(coupons[1]!= null && coupons[1].contains("元")){
                couponMoney = coupons[1].replace("元","");

                if(!TextUtils.isEmpty(srcPrice)){
                    BigDecimal srcBigDecimal = new BigDecimal(srcPrice);
                    BigDecimal couponMoneyDecimal = new BigDecimal(couponMoney);
                    BigDecimal couponAfterPriceText = srcBigDecimal.subtract(couponMoneyDecimal);
                    couponAfterPrice.setText("¥"+couponAfterPriceText);
                }
            }

        }
    }

    private void setTextTitle(TextView showTV,int resId,String pName){
        Bitmap b = BitmapFactory.decodeResource(getContext().getResources(), resId);
        ImageSpan imgSpan = new ImageSpan(getContext(), b);
        SpannableString spanString = new SpannableString("icon");
        spanString.setSpan(imgSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        showTV.setText(spanString);
        showTV.append(" ");
        showTV.append(pName);
    }
}
