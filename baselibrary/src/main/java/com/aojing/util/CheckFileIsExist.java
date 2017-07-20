package com.aojing.util;

import java.io.File;

/**
 * 检查文件是否存在SD卡
 * Created by wxw on 2017/4/24.
 */
public class CheckFileIsExist {
    boolean is_exist;
    public boolean isExist(String strUrl){
        File file = new File(strUrl);
        if (file.exists()){
            is_exist = true;
        }else {
            is_exist = false;
        }

        return is_exist;
    }


}
