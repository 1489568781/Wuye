package com.aojing.model;

/**
 * 对话列表
 * Created by wxw on 2017/2/26.
 */
public class DialogList_Item {
    private String date;
    private String theme;
    private String userTypeName;
    private String content;

    public DialogList_Item(String date, String theme, String userTypeName, String content) {
        this.date = date;
        this.theme = theme;
        this.userTypeName = userTypeName;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
