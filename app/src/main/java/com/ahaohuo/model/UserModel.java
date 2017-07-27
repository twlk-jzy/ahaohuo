package com.ahaohuo.model;

import com.ahaohuo.base.BaseModel;

import java.util.Date;

/**
 * Created by xyb on 2017/7/27.
 */

public class UserModel extends BaseModel {
    private Integer tId;

    private Long tUserId;

    private String tUserName;

    private String tUserPhone;

    private String tUserPwd;

    private Integer tUserSex;

    private Date tCreateTime;

    private Date tUpdateTime;

    private String tUserIconUrl;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public Long gettUserId() {
        return tUserId;
    }

    public void settUserId(Long tUserId) {
        this.tUserId = tUserId;
    }

    public String gettUserName() {
        return tUserName;
    }

    public void settUserName(String tUserName) {
        this.tUserName = tUserName == null ? null : tUserName.trim();
    }

    public String gettUserPhone() {
        return tUserPhone;
    }

    public void settUserPhone(String tUserPhone) {
        this.tUserPhone = tUserPhone;
    }

    public String gettUserPwd() {
        return tUserPwd;
    }

    public void settUserPwd(String tUserPwd) {
        this.tUserPwd = tUserPwd == null ? null : tUserPwd.trim();
    }

    public Integer gettUserSex() {
        return tUserSex;
    }

    public void settUserSex(Integer tUserSex) {
        this.tUserSex = tUserSex;
    }

    public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    public Date gettUpdateTime() {
        return tUpdateTime;
    }

    public void settUpdateTime(Date tUpdateTime) {
        this.tUpdateTime = tUpdateTime;
    }

    public String gettUserIconUrl() {
        return tUserIconUrl;
    }

    public void settUserIconUrl(String tUserIconUrl) {
        this.tUserIconUrl = tUserIconUrl == null ? null : tUserIconUrl.trim();
    }
}
