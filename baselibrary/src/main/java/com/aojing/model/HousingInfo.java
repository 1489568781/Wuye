package com.aojing.model;

import java.io.Serializable;

/**
 * 房产信息实体类
 * Created by wxw on 2017/2/19.
 */
public class HousingInfo implements Serializable {
    private String houseID;
    private String HousingName;
    private String projectID;//小区ID

    public HousingInfo(String houseID, String housingName) {
        this.houseID = houseID;
        HousingName = housingName;
    }

    public HousingInfo(String houseID, String housingName, String projectID) {
        this.houseID = houseID;
        HousingName = housingName;
        this.projectID = projectID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String gethouseID() {
        return houseID;
    }

    public void sethouseID(String houseID) {
        this.houseID = houseID;
    }

    public String getHousingName() {
        return HousingName;
    }

    public void setHousingName(String housingName) {
        HousingName = housingName;
    }
}
