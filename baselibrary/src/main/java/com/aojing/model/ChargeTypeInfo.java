package com.aojing.model;

import java.io.Serializable;

/**
 * 收费类型信息
 * Created by wxw on 2017/2/19.
 */
public class ChargeTypeInfo implements Serializable{
    private String guid;
    private String chargeTypeName;
    private String companyID;
    private String projectID;
    private String chargeTime;

    public ChargeTypeInfo(String guid, String chargeTypeName) {
        this.guid = guid;
        this.chargeTypeName = chargeTypeName;
    }

    public ChargeTypeInfo(String guid, String chargeTypeName, String companyID, String projectID, String chargeTime) {
        this.guid = guid;
        this.chargeTypeName = chargeTypeName;
        this.companyID = companyID;
        this.projectID = projectID;
        this.chargeTime = chargeTime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getChargeTypeName() {
        return chargeTypeName;
    }

    public void setChargeTypeName(String chargeTypeName) {
        this.chargeTypeName = chargeTypeName;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }
}
