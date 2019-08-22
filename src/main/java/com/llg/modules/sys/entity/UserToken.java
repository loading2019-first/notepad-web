package com.llg.modules.sys.entity;

import java.io.Serializable;

public class UserToken implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6401662406933683921L;
    private Integer userId;
    private String token;
    private String expireTime;
    private String updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}