package com.aojing.model;

/**
 * 停车信息实体类
 * Created by wxw on 2017/3/6.
 */
public class CarInfo {
    private String carID;
    private String carColor;
    private String carDescribe;
    private String carNo;
    private String carOwner;
    private String carPhone;
    private String carType;
    private String projectID;
    private String state;

    public CarInfo() {

    }

    public CarInfo(String carID, String carColor, String carNo, String carDescribe, String carOwner, String carPhone, String carType, String projectID, String state) {
        this.carID = carID;
        this.carColor = carColor;
        this.carNo = carNo;
        this.carDescribe = carDescribe;
        this.carOwner = carOwner;
        this.carPhone = carPhone;
        this.carType = carType;
        this.projectID = projectID;
        this.state = state;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarDescribe() {
        return carDescribe;
    }

    public void setCarDescribe(String carDescribe) {
        this.carDescribe = carDescribe;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public String getCarPhone() {
        return carPhone;
    }

    public void setCarPhone(String carPhone) {
        this.carPhone = carPhone;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
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
}
