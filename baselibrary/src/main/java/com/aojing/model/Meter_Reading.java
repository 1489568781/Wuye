package com.aojing.model;

/**
 * 抄表记录的实体
 * Created by wxw on 2017/1/16.
 */
public class Meter_Reading {
    private String typeName;//类型名称
    private String time;//抄表时间
    private String preReader;//本次读数
    private String passReader;//上次读数

    public Meter_Reading() {
    }

    public Meter_Reading(String typeName, String time, String preReader, String passReader) {
        this.typeName = typeName;
        this.time = time;
        this.preReader = preReader;
        this.passReader = passReader;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPreReader() {
        return preReader;
    }

    public void setPreReader(String preReader) {
        this.preReader = preReader;
    }

    public String getPassReader() {
        return passReader;
    }

    public void setPassReader(String passReader) {
        this.passReader = passReader;
    }
}
