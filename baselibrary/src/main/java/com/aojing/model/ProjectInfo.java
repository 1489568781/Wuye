package com.aojing.model;

import java.io.Serializable;

/**
 * 项目信息（物业公司、小区名称）
 * Created by wxw on 2017/2/7.
 */
public class ProjectInfo implements Serializable{
    private String company;//公司
    private String projectName;//小区
    private String projectID;//guid


    public ProjectInfo(String company, String projectName) {
        this.company = company;
        this.projectName = projectName;
    }

    public ProjectInfo(String company, String projectName, String projectID) {
        this.company = company;
        this.projectName = projectName;
        this.projectID = projectID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}
