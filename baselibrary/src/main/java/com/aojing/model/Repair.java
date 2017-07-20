package com.aojing.model;

import java.io.Serializable;

/**
 * 报修实体
 * Created by wxw on 2017/3/22.
 */
public class Repair implements Serializable {
    private String repairID;
    private String time;
    private String content;
    private String address;
    private int imgSum;

    public Repair(){

    }
    public Repair(String repairID, String time, String content, String address, int imgSum) {
        this.repairID = repairID;
        this.time = time;
        this.content = content;
        this.address = address;
        this.imgSum = imgSum;
    }

    public String getRepairID() {
        return repairID;
    }

    public void setRepairID(String repairID) {
        this.repairID = repairID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImgSum() {
        return imgSum;
    }

    public void setImgSum(int imgSum) {
        this.imgSum = imgSum;
    }
}
