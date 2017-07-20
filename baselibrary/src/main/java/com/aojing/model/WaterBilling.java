package com.aojing.model;

import java.io.Serializable;

/**
 * 水费实体类
 * Created by wxw on 2017/1/3.
 */
public class WaterBilling implements Serializable {

    private int id;//编号
    private String room;//房间地址
    private String theme;//账单标题
    private String bill;//费用
    private String passSum;//上一个月的量
    private String preSum;//本月的量
    private double price ;//单价
    private String date;//到期时间

    public WaterBilling() {

    }

    public WaterBilling(int id, String room, String theme, String bill, String passSum, String preSum, double price, String date) {
        this.id = id;
        this.room = room;
        this.theme = theme;
        this.bill = bill;
        this.passSum = passSum;
        this.preSum = preSum;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getPassSum() {
        return passSum;
    }

    public void setPassSum(String passSum) {
        this.passSum = passSum;
    }

    public String getPreSum() {
        return preSum;
    }

    public void setPreSum(String preSum) {
        this.preSum = preSum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
