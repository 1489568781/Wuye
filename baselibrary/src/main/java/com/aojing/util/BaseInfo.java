package com.aojing.util;

/**
 * 基本信息
 * Created by wxw on 2016/11/3.
 */
public class BaseInfo {
    private final static String IPAndPort="http://192.168.1.160:8080/wuye/";
    private final static String company="aojing";
    public static String getIPAndPort() {
        return IPAndPort;
    }
    public static String getCompany() {
        return company;
    }

}
