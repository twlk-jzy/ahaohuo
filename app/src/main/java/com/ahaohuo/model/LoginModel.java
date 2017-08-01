package com.ahaohuo.model;

/**
 * Created by xyb on 2017/8/1.
 */

public class LoginModel {

    /**
     * msg : 登录成功
     * code : 200
     * data : {"tCreateTime":"2017-08-01 10:33:43","tId":10,"tUpdateTime":"2017-08-01 10:33:43","tUserId":1501554823106,"tUserName":"爽肤水","tUserPhone":"18510337732","tUserPwd":"123456","tUserSex":0}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tCreateTime : 2017-08-01 10:33:43
         * tId : 10
         * tUpdateTime : 2017-08-01 10:33:43
         * tUserId : 1501554823106
         * tUserName : 爽肤水
         * tUserPhone : 18510337732
         * tUserPwd : 123456
         * tUserSex : 0
         */

        private String tCreateTime;
        private int tId;
        private String tUpdateTime;
        private long tUserId;
        private String tUserName;
        private String tUserPhone;
        private String tUserPwd;
        private int tUserSex;

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

        public String getTUpdateTime() {
            return tUpdateTime;
        }

        public void setTUpdateTime(String tUpdateTime) {
            this.tUpdateTime = tUpdateTime;
        }

        public long getTUserId() {
            return tUserId;
        }

        public void setTUserId(long tUserId) {
            this.tUserId = tUserId;
        }

        public String getTUserName() {
            return tUserName;
        }

        public void setTUserName(String tUserName) {
            this.tUserName = tUserName;
        }

        public String getTUserPhone() {
            return tUserPhone;
        }

        public void setTUserPhone(String tUserPhone) {
            this.tUserPhone = tUserPhone;
        }

        public String getTUserPwd() {
            return tUserPwd;
        }

        public void setTUserPwd(String tUserPwd) {
            this.tUserPwd = tUserPwd;
        }

        public int getTUserSex() {
            return tUserSex;
        }

        public void setTUserSex(int tUserSex) {
            this.tUserSex = tUserSex;
        }
    }
}
