package com.aojing.model;

/**
 * 我的消息的费用实体（用于listview）
 * Created by wxw on 2017/1/17.
 */
public class My_Message_Billing {
    private String arrive_date;//到达事件
    private String content;//消息内容

    public My_Message_Billing() {
    }

    public My_Message_Billing(String arrive_date, String content) {
        this.arrive_date = arrive_date;
        this.content = content;
    }

    public String getArrive_date() {
        return arrive_date;
    }

    public void setArrive_date(String arrive_date) {
        this.arrive_date = arrive_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
