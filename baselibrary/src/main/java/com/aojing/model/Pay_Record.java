package com.aojing.model;

import java.io.Serializable;

/**
 * 交费记录实体类
 * Created by wxw on 2017/5/8.
 */
public class Pay_Record implements Serializable{

    public static final int SHOW_ONLY_YEAR = 0;//显示年份
    public static final int SHOW_ALL_INFO = 1;//显示全部信息

    private String year;//年份
    private String month;//月
    private String day;//日
    private String chargeType;//交费类型
    private String state;//交费状态
    private String money;//金额
    private int itemType;//显示类型

    public Pay_Record() {
    }

    public Pay_Record(String year, int itemType) {
        this.year = year;
        this.itemType = itemType;
    }

    public Pay_Record(String month, String day, String chargeType, String state, int itemType, String money) {
        this.month = month;
        this.day = day;
        this.chargeType = chargeType;
        this.state = state;
        this.itemType = itemType;
        this.money = money;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
