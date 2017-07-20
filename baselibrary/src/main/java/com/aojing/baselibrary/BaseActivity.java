package com.aojing.baselibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class BaseActivity extends AppCompatActivity {

    protected Context mContext = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 通过Class跳转界面
     **/
//    public void startActivity(Class<?> cls) {
//        startActivity(cls);
//    }

    /**
     * 通过包名跳转
     * @param context
     * @param activityName
     */
    public  void startActivityForName(Context context, String activityName) {
        try {
            Class clazz = Class.forName(activityName);
            Intent intent = new Intent(this,clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 实现IOC注入
     * */

    public void initInjectedView(Object baseActivity){
        Field[] fields = baseActivity.getClass().getDeclaredFields();
        if (fields != null && fields.length >0){
            for (Field field:fields){
                try {
                    field.setAccessible(true);
                    ViewInject viewInject = field.getAnnotation(ViewInject.class);
                    if (viewInject != null){
                        int id = viewInject.id();
                        field.set(this,((Activity)baseActivity).findViewById(id));
                        View view = (View) field.get(this);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext,"click me!!!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ViewInject{
         int id();

    }

}
