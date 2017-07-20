package com.ahaohuo.model;

import java.util.List;

/**
 * Created by xyb on 2017/7/20.
 */

public class GridModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tCreateTime : 2017-07-20 11:22:14
         * tId : 4
         * tImgUrl : http://ot2or80qp.bkt.clouddn.com/juhuasuan.png
         * tLinkUrl : https://s.click.taobao.com/UbWx9fw
         * tName : 聚划算
         * tState : 0
         * tUpdateTime : 2017-07-20 11:22:14
         */

        private String tCreateTime;
        private int tId;
        private String tImgUrl;
        private String tLinkUrl;
        private String tName;
        private int tState;
        private String tUpdateTime;

        public String getTCreateTime() {
            return tCreateTime;
        }

        public void setTCreateTime(String tCreateTime) {
            this.tCreateTime = tCreateTime;
        }

        public int getTId() {
            return tId;
        }

        public void setTId(int tId) {
            this.tId = tId;
        }

        public String getTImgUrl() {
            return tImgUrl;
        }

        public void setTImgUrl(String tImgUrl) {
            this.tImgUrl = tImgUrl;
        }

        public String getTLinkUrl() {
            return tLinkUrl;
        }

        public void setTLinkUrl(String tLinkUrl) {
            this.tLinkUrl = tLinkUrl;
        }

        public String getTName() {
            return tName;
        }

        public void setTName(String tName) {
            this.tName = tName;
        }

        public int getTState() {
            return tState;
        }

        public void setTState(int tState) {
            this.tState = tState;
        }

        public String getTUpdateTime() {
            return tUpdateTime;
        }

        public void setTUpdateTime(String tUpdateTime) {
            this.tUpdateTime = tUpdateTime;
        }
    }
}
