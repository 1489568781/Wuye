package com.aojing.model;

import android.graphics.drawable.Drawable;

/**
 * 客服聊天的实体类
 * Created by wxw on 2017/2/27.
 */
public class ChatBean {
    public static final int CHAT_MYSELF = 0;//自己
    public static final int CHAT_FRIENDS = 1;//朋友
    public static final int CHAT_MYSELF_IMG = 2;//发送图片
    public static final int CHAT_FRIEND_IMG = 3;//接收图片
    private String message;
    private int type;
    private Drawable img;
    private boolean isSuccess = false;


    public ChatBean(){

    }
    public ChatBean(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public ChatBean(String message, int type, Drawable img) {
        this.message = message;
        this.type = type;
        this.img = img;
    }

    public ChatBean(String message, int type, Drawable img, boolean isSuccess) {
        this.message = message;
        this.type = type;
        this.img = img;
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
