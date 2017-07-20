package com.aojing.model;

/**
 * 费用小项实体
 * Created by wxw on 2017/2/21.
 */
public class BillItem {
    private String belongBillID;//所属的大费用ID
    private String guid;//guid
    private String billID;//费用ID
    private String charge;//费用金额
    private String chargeBase;//收费根据
    private String chargeItemID;//费用项ID
    private String projectID;//项目ID
    private String unit;//单位

    public BillItem() {

    }

    public BillItem(String belongBillID, String guid, String charge,String chargeItemID) {
        this.belongBillID = belongBillID;
        this.guid = guid;
        this.charge = charge;
        this.chargeItemID = chargeItemID;
    }

    public BillItem(String belongBillID, String guid, String billID, String charge, String chargeBase, String chargeItemID, String projectID, String unit) {
        this.belongBillID = belongBillID;
        this.guid = guid;
        this.billID = billID;
        this.charge = charge;
        this.chargeBase = chargeBase;
        this.chargeItemID = chargeItemID;
        this.projectID = projectID;
        this.unit = unit;
    }

    public String getBelongBillID() {
        return belongBillID;
    }

    public void setBelongBillID(String belongBillID) {
        this.belongBillID = belongBillID;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getChargeBase() {
        return chargeBase;
    }

    public void setChargeBase(String chargeBase) {
        this.chargeBase = chargeBase;
    }

    public String getChargeItemID() {
        return chargeItemID;
    }

    public void setChargeItemID(String chargeItemID) {
        this.chargeItemID = chargeItemID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
