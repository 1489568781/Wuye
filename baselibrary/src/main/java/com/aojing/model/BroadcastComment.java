package com.aojing.model;

import java.io.Serializable;

/**
 * 广播评论实体类
 * Created by wxw on 2017/4/11.
 */
public class BroadcastComment implements Serializable{

    public static final int COMMENT = 0;
    public static final int GOOD = 1;
    public static final int BAD = 2;

    private String comment_userName;
    private String commnet_content;
    private String broadcastID;
    private String company;
    private String projectID;
    private String time;
    private String headImgUrl;
    private int Type;
    private String broadcastCommentID;


    public BroadcastComment() {
    }


    public BroadcastComment(String comment_userName, int type) {
        this.comment_userName = comment_userName;
        Type = type;
    }

    public BroadcastComment(String comment_userName, String commnet_content) {
        this.comment_userName = comment_userName;
        this.commnet_content = commnet_content;
    }

    public BroadcastComment(String comment_userName, String commnet_content, String headImgUrl) {
        this.comment_userName = comment_userName;
        this.commnet_content = commnet_content;
        this.headImgUrl = headImgUrl;
    }

//    public BroadcastComment(String comment_userName, String commnet_content, int type, String headImgUrl,String broadcastCommentID) {
//        this.comment_userName = comment_userName;
//        this.commnet_content = commnet_content;
//        Type = type;
//        this.broadcastCommentID = broadcastCommentID;
//        this.headImgUrl = headImgUrl;
//    }

    public BroadcastComment(String comment_userName, String commnet_content, String broadcastID, String company, String projectID, String time, String headImgUrl, int type, String broadcastCommentID) {
        this.comment_userName = comment_userName;
        this.commnet_content = commnet_content;
        this.broadcastID = broadcastID;
        this.company = company;
        this.projectID = projectID;
        this.time = time;
        this.headImgUrl = headImgUrl;
        Type = type;
        this.broadcastCommentID = broadcastCommentID;
    }

    public String getBroadcastCommentID() {
        return broadcastCommentID;
    }

    public void setBroadcastCommentID(String broadcastCommentID) {
        this.broadcastCommentID = broadcastCommentID;
    }

    public String getComment_userName() {
        return comment_userName;
    }

    public void setComment_userName(String comment_userName) {
        this.comment_userName = comment_userName;
    }

    public String getCommnet_content() {
        return commnet_content;
    }

    public void setCommnet_content(String commnet_content) {
        this.commnet_content = commnet_content;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getBroadcastID() {
        return broadcastID;
    }

    public void setBroadcastID(String broadcastID) {
        this.broadcastID = broadcastID;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
