package com.aojing.util;

import android.os.Environment;

import java.io.File;

/**
 * 创建文件夹相关操作
 * Created by wxw on 2017/3/20.
 */
public class CreateFileUtil {

    public String getSDPath(){
        File sdDir = null;
        File sdDir1 = null;
        File sdDir2 = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            sdDir1 = Environment.getDataDirectory();
            sdDir2 =Environment.getRootDirectory();
        }
        System.out.println("getExternalStorageDirectory(): "+sdDir.toString());
        System.out.println("getDataDirectory(): "+sdDir1.toString());
        System.out.println("getRootDirectory(): "+sdDir2.toString());
        return sdDir.toString();
    }


    /**
     * 判断是否存在文件夹
     * */
    public void createFile(String path) {
        File file = new File(path);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
    }

//    File sd=Environment.getExternalStorageDirectory();
//    String path=sd.getPath()+"/notes";
//    File file=new File(path);
//    if(!file.exists())
//            file.mkdir();

}
