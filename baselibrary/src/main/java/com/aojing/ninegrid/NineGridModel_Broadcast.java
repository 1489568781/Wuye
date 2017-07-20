package com.aojing.ninegrid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 广播类
 * Created by wxw on 2017/3/24.
 */
public class NineGridModel_Broadcast implements Serializable {

    public List<String> imgUrls = new ArrayList<>();
    public boolean isShowAll = false;
    public String content = "";
    public String good = "";
    public String bad = "";
    public String broadcastID = "";
    public String comment = "";
    public String time = "";
    public String company = "";
    public String projectID = "";

    public NineGridModel_Broadcast() {
    }

    public NineGridModel_Broadcast(boolean isShowAll, String projectID, String company, String time, String comment, String broadcastID, String bad, String good, String content, List<String> imgUrls) {
        this.isShowAll = isShowAll;
        this.projectID = projectID;
        this.company = company;
        this.time = time;
        this.comment = comment;
        this.broadcastID = broadcastID;
        this.bad = bad;
        this.good = good;
        this.content = content;
        this.imgUrls = imgUrls;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public boolean isShowAll() {
        return isShowAll;
    }

    public void setShowAll(boolean showAll) {
        isShowAll = showAll;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public String getBroadcastID() {
        return broadcastID;
    }

    public void setBroadcastID(String broadcastID) {
        this.broadcastID = broadcastID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
