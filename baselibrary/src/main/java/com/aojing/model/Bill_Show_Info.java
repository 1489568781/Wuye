package com.aojing.model;

import java.io.Serializable;

/**
 * 账单的实体
 * Created by wxw on 2017/1/20.
 */
public class Bill_Show_Info implements Serializable {
    private String guid ;//账单guid
    private String houseID;//房产ID
    private String projectID;//项目（小区）ID
    private String chargeTypeID;//收费类型ID
    private String limitTime;//交费截止日期
    private String billDate;//生成日期
    private String state;//交费状态
    private String chargeItemsContent;//费用里面的小项内容
    private String billCharge;//账单总费用

    public Bill_Show_Info() {

    }


    public Bill_Show_Info(String guid, String houseID, String projectID, String chargeTypeID, String limitTime, String billDate,String chargeItemsContent, String state, String billCharge) {
        this.guid = guid;
        this.houseID = houseID;
        this.projectID = projectID;
        this.chargeTypeID = chargeTypeID;
        this.limitTime = limitTime;
        this.billDate = billDate;
        this.chargeItemsContent = chargeItemsContent;
        this.state = state;
        this.billCharge = billCharge;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getHouseID() {
        return houseID;
    }

    public void setHouseID(String houseID) {
        this.houseID = houseID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getChargeTypeID() {
        return chargeTypeID;
    }

    public void setChargeTypeID(String chargeTypeID) {
        this.chargeTypeID = chargeTypeID;
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }


    public String getChargeItemsContent() {
        return chargeItemsContent;
    }

    public void setChargeItemsContent(String chargeItemsContent) {
        this.chargeItemsContent = chargeItemsContent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBillCharge() {
        return billCharge;
    }

    public void setBillCharge(String billCharge) {
        this.billCharge = billCharge;
    }
}
