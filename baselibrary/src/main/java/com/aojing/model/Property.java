package com.aojing.model;

import java.io.Serializable;

/**
 * 房产实体类
 * Created by wxw on 2017/1/12.
 */
public class Property implements Serializable{
    private String project;//项目名称
    private String housingName;//房间

    public Property() {
    }

    public Property(String project, String housingName) {
        this.project = project;
        this.housingName = housingName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getHousingName() {
        return housingName;
    }

    public void setHousingName(String housingName) {
        this.housingName = housingName;
    }
}
