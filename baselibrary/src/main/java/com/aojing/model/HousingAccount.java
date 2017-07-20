package com.aojing.model;

import java.io.Serializable;

/**
 * 房产费用金额
 * Created by wxw on 2017/2/13.
 */
public class HousingAccount implements Serializable{
    private String chargeType;
    private String balance;
    private String chargesUnit;
    private String guid;
    private String hosingName;
    private String projectName;
    private String state;

    public HousingAccount() {
    }

    public HousingAccount(String chargeType, String balance, String chargesUnit, String guid, String hosingName, String projectName, String state) {
        this.chargeType = chargeType;
        this.balance = balance;
        this.chargesUnit = chargesUnit;
        this.guid = guid;
        this.hosingName = hosingName;
        this.projectName = projectName;
        this.state = state;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getChargesUnit() {
        return chargesUnit;
    }

    public void setChargesUnit(String chargesUnit) {
        this.chargesUnit = chargesUnit;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getHosingName() {
        return hosingName;
    }

    public void setHosingName(String hosingName) {
        this.hosingName = hosingName;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
