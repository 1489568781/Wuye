package com.aojing.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 显示当前网络的情况工具
 * Created by wxw on 2017/2/9.
 */
public class ShowNetTag {
    private boolean wifiAvail = false;
    private boolean mobiAvail = false;

    public boolean isWifiAvail() {
        return wifiAvail;
    }

    public void setWifiAvail(boolean wifiAvail) {
        this.wifiAvail = wifiAvail;
    }

    public boolean isMobiAvail() {
        return mobiAvail;
    }

    public void setMobiAvail(boolean mobiAvail) {
        this.mobiAvail = mobiAvail;
    }

    public void show(Context context){
//        if (wifiAvail){
//            Toast.makeText(context,"当前是Wifi网络",Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context,"当前是没有连接网络~~",Toast.LENGTH_SHORT).show();
//        }

        if (mobiAvail){
            Toast.makeText(context,"当前是移动网络",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"当前是没有连接网络~~",Toast.LENGTH_SHORT).show();
        }
    }

}
