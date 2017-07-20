package com.aojing.model;

import java.io.Serializable;

/**
 * 报修聊天实体
 * Created by wxw on 2017/3/28.
 */
public class Chatting implements Serializable{

    public static final int CHAT_MYSELF = 0;//自己
    public static final int CHAT_FRIENDS = 1;//朋友
    public static final int CHAT_MYSELF_IMG = 2;//发送图片
    public static final int CHAT_FRIEND_IMG = 3;//接收图片

    private String company;//公司
    private String projectID;//小区ID
    private String contentType;//聊天交互类型（图片/文字）
    private String content;//聊天内容
    private String repairID;//报修ID
    private String conversation;//报修人
    private String time;//发送或接收时间
    private int displayType;//显示类型
    private String state;//交互状态（成功还是失败）
    public Chatting() {
    }

    public Chatting(String company, String projectID, String contentType, String content, String repairID, String conversation, String time, int displayType, String state) {
        this.company = company;
        this.projectID = projectID;
        this.contentType = contentType;
        this.content = content;
        this.repairID = repairID;
        this.conversation = conversation;
        this.time = time;
        this.displayType = displayType;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRepairID() {
        return repairID;
    }

    public void setRepairID(String repairID) {
        this.repairID = repairID;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }
}
