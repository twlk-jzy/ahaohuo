package com.ahaohuo.model;

import com.ahaohuo.base.BaseModel;

import java.util.List;

/**
 * Created by xyb on 2017/7/17.
 */

public class BannerModel extends BaseModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * tBannerTitle : 每天10款1元强
         * tBannerUrl : http://ot2or80qp.bkt.clouddn.com/TB1JQGXRXXXXXcWXXXXXXXXXXXX-440-180.jpg
         * tConfigType : 0
         * tCreateTime : 2017-07-14 17:10:27
         * tState : 0
         * tTjUrl : https://s.click.taobao.com/BkfxYfw
         * tType : 0
         */

        private int id;
        private String tBannerTitle;
        private String tBannerUrl;
        private int tConfigType;
        private String tCreateTime;
        private int tState;
        private String tTjUrl;
        private int tType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTBannerTitle() {
            return tBannerTitle;
        }

        public void setTBannerTitle(String tBannerTitle) {
            this.tBannerTitle = tBannerTitle;
        }

        public String getTBannerUrl() {
            return tBannerUrl;
        }

        public void setTBannerUrl(String tBannerUrl) {
            this.tBannerUrl = tBannerUrl;
        }

        public int getTConfigType() {
            return tConfigType;
        }

        public void setTConfigType(int tConfigType) {
            this.tConfigType = tConfigType;
        }

        public String getTCreateTime() {
            return tCreateTime;
        }

        public void setTCreateTime(String tCreateTime) {
            this.tCreateTime = tCreateTime;
        }

        public int getTState() {
            return tState;
        }

        public void setTState(int tState) {
            this.tState = tState;
        }

        public String getTTjUrl() {
            return tTjUrl;
        }

        public void setTTjUrl(String tTjUrl) {
            this.tTjUrl = tTjUrl;
        }

        public int getTType() {
            return tType;
        }

        public void setTType(int tType) {
            this.tType = tType;
        }
    }
}
