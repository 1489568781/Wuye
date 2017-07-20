package com.aojing.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * 检测当前是否有网络、网络是否可以上网
 * Created by wxw on 2017/2/9.
 */
public class CheckNetworkAvailable {

    // check all network connect, WIFI or mobile
    public static boolean isNetworkAvailable(final Context context) {
        boolean hasWifoCon = false;
        boolean hasMobileCon = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null){
            if (networkInfo.isConnected()){
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                    //当前连接到WIFI
                    Log.e("TAG","当前Wifi连接可用");
                    hasWifoCon = true;


                }else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                    //当前连接到移动数据
                    Log.e("TAG","当前移动网络连接可用");
                   hasMobileCon = true;
                }
            }else {
                Log.e("TAG","当前没有网络连接，请确保你已经打开网络");
            }

        }else {
            Log.e("TAG","当前没有网络连接，请确保你已经打开网络");
        }


        return hasMobileCon || hasWifoCon;
    }

    // network available cannot ensure Internet is available
//    public static boolean isNetWorkAvailable(final Context context) {
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process pingProcess = runtime.exec("/system/bin/ping -c 1 www.baidu.com");
//            int exitCode = pingProcess.waitFor();
//            return (exitCode == 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

}
