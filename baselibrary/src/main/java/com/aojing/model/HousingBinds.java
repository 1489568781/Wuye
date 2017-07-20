package com.aojing.model;

import java.io.Serializable;

/**
 * 绑定房产的实体
 * Created by wxw on 2017/2/15.
 */
public class HousingBinds implements Serializable{
    private String guid;//guid
    private String bindDate;//绑定时间
    private String houseID;//绑定的房间
    private String phone;//手机号码
    private String projectID;//小区（项目）
    private String state;//状态
    private String userName;//用户名
    private String whetherOwner;//是否业主
    private String housingName;//房产名称

    public HousingBinds() {
    }

    public HousingBinds(String guid, String bindDate, String houseID,String housingName, String phone, String projectID, String state, String userName, String whetherOwner) {
        this.guid = guid;
        this.bindDate = bindDate;
        this.houseID = houseID;
        this.housingName = housingName;
        this.phone = phone;
        this.projectID = projectID;
        this.state = state;
        this.userName = userName;
        this.whetherOwner = whetherOwner;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getBindDate() {
        return bindDate;
    }

    public void setBindDate(String bindDate) {
        this.bindDate = bindDate;
    }

    public String getHouseID() {
        return houseID;
    }

    public void setHouseID(String houseID) {
        this.houseID = houseID;
    }

    public String getHousingName() {
        return housingName;
    }

    public void setHousingName(String housingName) {
        this.housingName = housingName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWhetherOwner() {
        return whetherOwner;
    }

    public void setWhetherOwner(String whetherOwner) {
        this.whetherOwner = whetherOwner;
    }
}
