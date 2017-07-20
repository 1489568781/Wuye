package com.aojing.model;

/**
 * 催收消息的实体
 * Created by wxw on 2017/1/18.
 */
public class CollectionMessage {

    private String theme;//标题
    private String content;//内容

    public CollectionMessage(){

    }
    public CollectionMessage(String theme, String content) {
        this.theme = theme;
        this.content = content;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
