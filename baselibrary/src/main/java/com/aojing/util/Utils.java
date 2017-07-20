package com.aojing.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 推送相关的工具类
 *
 * */
public class Utils {
    private static Context context;
    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    public static final String TAG = "PushDemoActivity";
    public static final String RESPONSE_METHOD = "method";
    public static final String RESPONSE_CONTENT = "content";
    public static final String RESPONSE_ERRCODE = "errcode";
    protected static final String ACTION_LOGIN = "com.baidu.pushdemo.action.LOGIN";
    public static final String ACTION_MESSAGE = "com.baiud.pushdemo.action.MESSAGE";
    public static final String ACTION_RESPONSE = "bccsclient.action.RESPONSE";
    public static final String ACTION_SHOW_MESSAGE = "bccsclient.action.SHOW_MESSAGE";
    protected static final String EXTRA_ACCESS_TOKEN = "access_token";
    public static final String EXTRA_MESSAGE = "message";

    public static String logStringCache = "";

    // 获取ApiKey的方法
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "error " + e.getMessage());
        }
        return apiKey;
    }

    public static List<String> getTagsList(String originalText) {
        if (originalText == null || originalText.equals("")) {
            return null;
        }
        List<String> tags = new ArrayList<String>();
        int indexOfComma = originalText.indexOf(',');
        String tag;
        while (indexOfComma != -1) {
            tag = originalText.substring(0, indexOfComma);
            tags.add(tag);

            originalText = originalText.substring(indexOfComma + 1);
            indexOfComma = originalText.indexOf(',');
        }

        tags.add(originalText);
        return tags;
    }
/*
* 获取已经保存的送达的消息的方法
* */
    public static String getLogText(Context context) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getString("log_text", "");
    }
/*
* 保存送达的消息的方法
* */
    public static void setLogText(Context context, String text) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor editor = sp.edit();
        editor.putString("log_text", text);
        editor.commit();
    }
/*
* 保存角标的数量的方法
* value 保存的值
* typeName 保存的类型是水费还是电费或者其他费
* */
    public static void saveBadge(String value,String typeName,Context context){
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor editor = sp.edit();
        editor.putString(typeName,value);
        editor.commit();
    }
/*
* 读取保存的角标的数量的方法
* typeName 类型的名称，根据此来区分获取的是水费还是电费或者其他费用
* */
    public static String readBadge(Context context,String typeName){
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getString(typeName, "");
    }

  /*
  * 把字符串保存起来
  *
  * */
    public static void saveString(Context context,String key,Object object){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        }else if (object instanceof Integer){
            editor.putInt(key, (Integer) object);
        }else if (object instanceof Boolean){
            editor.putBoolean(key,(Boolean)object);
        }
        editor.commit();

    }
    /*
    * 读取保存的string
    * */
    public static String readString(Context context,String key){
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getString(key, "");
    }
    /*
   * 读取保存的int
   * */
    public static int readInteger(Context context,String key){
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getInt(key, 0);
    }
    /*
    * 清除指定的数据
    * */
    public static void clearSaveInfo(Context context,String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
    /*
       * 清除全部sp的数据
       * */
    public static void clearAllSaveInfo(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }



    /*
    * 解析Json字符串的方法
    * */
//    private void parseJson(String json,T t,boolean isString){
//
//        if (isString){//如果是String类型就执行下面方法
//
//
//        }else {
//
//        }
//        List<Object> objects = new ArrayList<Object>();
//        try{
////            JSONArray jsonArray = new JSONArray(json);
////            for(int i = 0;i < jsonArray.length();i++){
////                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
////                Person person = new Person();
////                person.setId(i+"");
////                person.setName(jsonObject.getString("name"));
////                person.setAge(jsonObject.getString("age"));
////                persons.add(person);
//            }
//        }catch (Exception e){e.printStackTrace();}
//    }
}
