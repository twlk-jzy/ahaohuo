package com.ahaohuo.model;

import java.util.List;

/**
 * Created by xyb on 2017/7/10.
 */

public class ProductModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pCommission : 37.53
         * pCouponEndTime : 2016-12-26
         * pCouponId : ac92aff9308c442faf28d8c09312b06e
         * pCouponLink : https://uland.taobao.com/coupon/edetail?e=1tsq8EuKnY6bhUsf2ayXDM%2FUJkUh5tUp0XlN2VdTfKbhmkfkgWSisbcZelJt%2BzjyH3fu32z5eyJd%2FNXwlyNc%2F2S4ihT3PrBb19fIGjTfiD5KXytHM6G4zz3yuBkX9TpTVn2gWBQ97tBKQmUi0muSNAQg%2FF9bUN5R&pid=mm_31082392_4466817_14800008&af=1
         * pCouponMoney : 满139元减30元
         * pCouponStartTime : 2016-12-22
         * pCouponSurplusNum : 10000
         * pCouponTotalNum : 10000
         * pCouponUrl : https://taoquan.taobao.com/coupon/unify_apply.htm?sellerId=1658594886&activityId=ac92aff9308c442faf28d8c09312b06e
         * pDetailUrl : http://item.taobao.com/item.htm?id=538695064720
         * pId : 538695064720
         * pIncomeRatio : 27.00
         * pMainImg : http://img04.taobaocdn.com/bao/uploaded/i4/TB1KJX9NVXXXXa1aXXXXXXXXXXX_!!0-item_pic.jpg
         * pMonthSale : 1124
         * pName : 2016秋冬新款女装学生吊带裙子毛呢背带裙秋季中长款粉色连衣裙女
         * pOneCategory : 女装/女士精品
         * pPlatformType : 天猫
         * pPrice : 139.00
         * pSellerId : 1658594886
         * pSellerWw : 库恩玛维旗舰店
         * pShopName : 库恩玛维旗舰店
         * pTkUrl : https://s.click.taobao.com/t?e=m%3D2%26s%3D01IkLnnpyDgcQipKwQzePOeEDrYVVa64K7Vc7tFgwiG3bLqV5UHdqeJNqBI0LN280e71iVTN2RyD9HPz4V%2F%2FOOKdq21qZOAlMa71CPA5gPDeDj0aB9vF5AuGDBZ7wUeG75xe9%2FdG7MbsayibBKoBbXMwfybyuI7CDp2Zssvyar1%2FCQajsM0aDWsBW6brclDnKIP588F8U5lUhSvaICA1vKJn5AyUbPoV
         */

        private String pCommission;
        private String pCouponEndTime;
        private String pCouponId;
        private String pCouponLink;
        private String pCouponMoney;
        private String pCouponStartTime;
        private int pCouponSurplusNum;
        private int pCouponTotalNum;
        private String pCouponUrl;
        private String pDetailUrl;
        private long pId;
        private String pIncomeRatio;
        private String pMainImg;
        private int pMonthSale;
        private String pName;
        private String pOneCategory;
        private String pPlatformType;
        private String pPrice;
        private Long pSellerId;
        private String pSellerWw;
        private String pShopName;
        private String pTkUrl;

        public String getPCommission() {
            return pCommission;
        }

        public void setPCommission(String pCommission) {
            this.pCommission = pCommission;
        }

        public String getPCouponEndTime() {
            return pCouponEndTime;
        }

        public void setPCouponEndTime(String pCouponEndTime) {
            this.pCouponEndTime = pCouponEndTime;
        }

        public String getPCouponId() {
            return pCouponId;
        }

        public void setPCouponId(String pCouponId) {
            this.pCouponId = pCouponId;
        }

        public String getPCouponLink() {
            return pCouponLink;
        }

        public void setPCouponLink(String pCouponLink) {
            this.pCouponLink = pCouponLink;
        }

        public String getPCouponMoney() {
            return pCouponMoney;
        }

        public void setPCouponMoney(String pCouponMoney) {
            this.pCouponMoney = pCouponMoney;
        }

        public String getPCouponStartTime() {
            return pCouponStartTime;
        }

        public void setPCouponStartTime(String pCouponStartTime) {
            this.pCouponStartTime = pCouponStartTime;
        }

        public int getPCouponSurplusNum() {
            return pCouponSurplusNum;
        }

        public void setPCouponSurplusNum(int pCouponSurplusNum) {
            this.pCouponSurplusNum = pCouponSurplusNum;
        }

        public int getPCouponTotalNum() {
            return pCouponTotalNum;
        }

        public void setPCouponTotalNum(int pCouponTotalNum) {
            this.pCouponTotalNum = pCouponTotalNum;
        }

        public String getPCouponUrl() {
            return pCouponUrl;
        }

        public void setPCouponUrl(String pCouponUrl) {
            this.pCouponUrl = pCouponUrl;
        }

        public String getPDetailUrl() {
            return pDetailUrl;
        }

        public void setPDetailUrl(String pDetailUrl) {
            this.pDetailUrl = pDetailUrl;
        }

        public long getPId() {
            return pId;
        }

        public void setPId(long pId) {
            this.pId = pId;
        }

        public String getPIncomeRatio() {
            return pIncomeRatio;
        }

        public void setPIncomeRatio(String pIncomeRatio) {
            this.pIncomeRatio = pIncomeRatio;
        }

        public String getPMainImg() {
            return pMainImg;
        }

        public void setPMainImg(String pMainImg) {
            this.pMainImg = pMainImg;
        }

        public int getPMonthSale() {
            return pMonthSale;
        }

        public void setPMonthSale(int pMonthSale) {
            this.pMonthSale = pMonthSale;
        }

        public String getPName() {
            return pName;
        }

        public void setPName(String pName) {
            this.pName = pName;
        }

        public String getPOneCategory() {
            return pOneCategory;
        }

        public void setPOneCategory(String pOneCategory) {
            this.pOneCategory = pOneCategory;
        }

        public String getPPlatformType() {
            return pPlatformType;
        }

        public void setPPlatformType(String pPlatformType) {
            this.pPlatformType = pPlatformType;
        }

        public String getPPrice() {
            return pPrice;
        }

        public void setPPrice(String pPrice) {
            this.pPrice = pPrice;
        }

        public Long getPSellerId() {
            return pSellerId;
        }

        public void setPSellerId(Long pSellerId) {
            this.pSellerId = pSellerId;
        }

        public String getPSellerWw() {
            return pSellerWw;
        }

        public void setPSellerWw(String pSellerWw) {
            this.pSellerWw = pSellerWw;
        }

        public String getPShopName() {
            return pShopName;
        }

        public void setPShopName(String pShopName) {
            this.pShopName = pShopName;
        }

        public String getPTkUrl() {
            return pTkUrl;
        }

        public void setPTkUrl(String pTkUrl) {
            this.pTkUrl = pTkUrl;
        }
    }
}
