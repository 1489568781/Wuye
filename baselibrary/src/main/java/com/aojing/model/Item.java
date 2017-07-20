package com.aojing.model;

import java.io.Serializable;

/**
 * Created by wxw on 2017/1/18.
 */
public class Item implements Serializable{

    private boolean isCheck;//是否被选择
    private String name;//名称
    private String money;//金额

    public Item(){

    }
    public Item(boolean isCheck, String name, String money) {
        this.isCheck = isCheck;
        this.name = name;
        this.money = money;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
