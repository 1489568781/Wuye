package com.aojing.model;

import java.io.Serializable;

/**
 * Created by wxw on 2017/4/26.
 */
public class BroadCastImg implements Serializable {
    private String BroadcastImg;
    private String BroadcastID;

    public BroadCastImg(String broadcastImg, String broadcastID) {
        BroadcastImg = broadcastImg;
        BroadcastID = broadcastID;
    }

    public String getBroadcastImg() {
        return BroadcastImg;
    }

    public void setBroadcastImg(String broadcastImg) {
        BroadcastImg = broadcastImg;
    }

    public String getBroadcastID() {
        return BroadcastID;
    }

    public void setBroadcastID(String broadcastID) {
        BroadcastID = broadcastID;
    }
}
