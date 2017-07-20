package com.aojing.model;

import java.io.Serializable;

/**
 * 用户信息实体类
 * Created by wxw on 2017/1/21.
 */
public class UserInfo implements Serializable{

    private String nickName;//昵称
    private String phone;//手机号码
    private String realName;//真是姓名
    private String userName;//用户名
    private String identityNo;//身份证

    public UserInfo(){

    }
    public UserInfo(String nickName, String phone, String realName, String userName, String identityNo) {
        this.nickName = nickName;
        this.phone = phone;
        this.realName = realName;
        this.userName = userName;
        this.identityNo = identityNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }
}
