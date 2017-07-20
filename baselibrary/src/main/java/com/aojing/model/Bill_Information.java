package com.aojing.model;

import java.io.Serializable;

/**
 * 费用信息实体
 * Created by wxw on 2017/1/16.
 */
public class Bill_Information implements Serializable{
    private String guid;
    private String bill_type;//费用类型
    private String bill_final_money;//费用余额

    public Bill_Information() {
    }

    public Bill_Information(String bill_type, String bill_final_money) {
        this.bill_type = bill_type;
        this.bill_final_money = bill_final_money;
    }

    public Bill_Information(String bill_type, String bill_final_money, String guid) {
        this.bill_type = bill_type;
        this.bill_final_money = bill_final_money;
        this.guid = guid;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getBill_final_money() {
        return bill_final_money;
    }

    public void setBill_final_money(String bill_final_money) {
        this.bill_final_money = bill_final_money;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
