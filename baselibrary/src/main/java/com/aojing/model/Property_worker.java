package com.aojing.model;

import java.io.Serializable;

/**
 * 房产工作人员实体类
 * Created by wxw on 2017/5/3.
 */
public class Property_worker implements Serializable {
    private String worker_name;//姓名
    private String company;//所属公司
    private String projectID;//所属小区
    private String serverMark;//服务总评分数
    private String singleMark;//个人上次评论分数
    private String collectState;//收藏状态
    private String headImg;//头像
    private String workingZoom;//管辖区域
    private String userName;//单次评价者
    private String workerPhone;//手机号码
    private String workerRank;//头衔

    public Property_worker() {

    }

    public Property_worker(String worker_name, String company, String projectID, String sercerMark, String singleMark, String collectState, String headImg, String workingZoom, String userName, String workerPhone, String workerRank) {
        this.worker_name = worker_name;
        this.company = company;
        this.projectID = projectID;
        this.serverMark = sercerMark;
        this.singleMark = singleMark;
        this.collectState = collectState;
        this.headImg = headImg;
        this.workingZoom = workingZoom;
        this.userName = userName;
        this.workerPhone = workerPhone;
        this.workerRank = workerRank;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getSercerMark() {
        return serverMark;
    }

    public void setSercerMark(String sercerMark) {
        this.serverMark = sercerMark;
    }

    public String getSingleMark() {
        return singleMark;
    }

    public void setSingleMark(String singleMark) {
        this.singleMark = singleMark;
    }

    public String getCollectState() {
        return collectState;
    }

    public void setCollectState(String collectState) {
        this.collectState = collectState;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getWorkingZoom() {
        return workingZoom;
    }

    public void setWorkingZoom(String workingZoom) {
        this.workingZoom = workingZoom;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }

    public String getWorkerRank() {
        return workerRank;
    }

    public void setWorkerRank(String workerRank) {
        this.workerRank = workerRank;
    }
}
