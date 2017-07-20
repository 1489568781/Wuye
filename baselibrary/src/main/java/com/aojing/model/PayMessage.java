package com.aojing.model;

import java.io.Serializable;

/**
 * 催收实体
 * Created by wxw on 2017/2/22.
 */
public class PayMessage implements Serializable{
    private String MessageID;
    private String title;
    private String content;
    private String houseID;
    private String housingName;
    private String userName;
    private String MessageTime;

    public PayMessage() {
    }

    public PayMessage(String messageID, String title, String content, String houseID, String housingName, String userName, String messageTime) {
        MessageID = messageID;
        this.title = title;
        this.content = content;
        this.houseID = houseID;
        this.housingName = housingName;
        this.userName = userName;
        MessageTime = messageTime;
    }

    public String getMessageID() {
        return MessageID;
    }

    public void setMessageID(String messageID) {
        MessageID = messageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHouseID() {
        return houseID;
    }

    public void setHouseID(String houseID) {
        this.houseID = houseID;
    }

    public String getHousingName() {
        return housingName;
    }

    public void setHousingName(String housingName) {
        this.housingName = housingName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageTime() {
        return MessageTime;
    }

    public void setMessageTime(String messageTime) {
        MessageTime = messageTime;
    }
}
