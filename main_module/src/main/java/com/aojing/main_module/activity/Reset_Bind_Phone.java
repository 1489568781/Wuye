package com.aojing.main_module.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aojing.main_module.R;
import com.aojing.util.Get_Img_Str_From_Http;
import com.aojing.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 修改登录密码
 * Created by wxw on 2017/3/10.
 */
public class Reset_Bind_Phone extends AppCompatActivity {

    private TextView reset_bind_phone_back;//返回按钮
//    private CheckBox cb_show_password;//显示密码按钮
    private EditText et_new_phone ;//新手机号码
    private EditText et_reset_bind_phone_password ;//密码
    private Button bt_post_reset_bind_phone;//提交

    /**
     * 正则表达式验证手机号码
     * */
    
    private boolean isPhoneNo(String phoneNo){
        Pattern pattern = null;
        Matcher matcher = null;
        boolean b = false;
        pattern = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");//验证以1开头第二位是3,4,5,8的11位手机号
        matcher = pattern.matcher(phoneNo);
        b = matcher.matches();
        return b;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_bind_phone);
        et_new_phone = (EditText)findViewById(R.id.et_new_phone);
        et_reset_bind_phone_password = (EditText)findViewById(R.id.et_reset_bind_phone_password);
        reset_bind_phone_back = (TextView)findViewById(R.id.reset_bind_phone_back);
        reset_bind_phone_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        cb_show_password = (CheckBox)findViewById(R.id.cb_show_password);
//        cb_show_password.setChecked(true);
//        cb_show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    et_passWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    et_new_passWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    et_passWord.setSelection(et_passWord.getText().length());
//                    et_new_passWord.setSelection(et_new_passWord.getText().length());
////                    System.out.println("checked");
//                }else {
//                    et_passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    et_new_passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    et_passWord.setSelection(et_passWord.getText().length());
//                    et_new_passWord.setSelection(et_new_passWord.getText().length());
////                    System.out.println("not checked");
//                }
//            }
//        });
//        if (cb_show_password.isChecked()){
//
//        }
        bt_post_reset_bind_phone = (Button)findViewById(R.id.bt_post_reset_bind_phone);
        bt_post_reset_bind_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_new_phone.getText().toString().trim().equals("")&&!et_reset_bind_phone_password.getText().toString().trim().equals("")){
                    if (isPhoneNo(et_new_phone.getText().toString().trim())){
                        send();//发送到服务器
                        if(1 == 1){
                            System.out.println("change local project!!");
                            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"输入手机不正确~",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"输入不能为空~",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    /**
     * 发送
     */
    private void send() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                sendMsg1();
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject object = new JSONObject(result);
                    String flag = object.getString("result");
                    if (flag.equals("success")){
                        Utils.saveString(Reset_Bind_Phone.this,"key_phoneNo",et_new_phone.getText().toString().trim());
                        Intent intent = new Intent(Reset_Bind_Phone.this,Reset_Login_Password_Success.class);
                        intent.putExtra("type","手机绑定");
                        startActivity(intent);
                    }else {
                        Toast.makeText(Reset_Bind_Phone.this,"修改失败！请检查原密码是否正确~",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute();
    }

    /**
     * http的post形式与服务器交互
     * 发送消息
     */

    String result = "";
    private String company = "";
    private String projectID = "";
    protected void sendMsg1() {
        try {
            String userName = Utils.readString(getApplicationContext(),"key_save_userName");
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            result = get_img_str_from_http.getBindPhone("changePhone",userName,et_reset_bind_phone_password.getText().toString().trim(),et_new_phone.getText().toString().trim());
            System.out.println("************changeBindPhone****" + result);

            //对房间的名称列表roomList进行保存
//            Utils.clearAllSaveInfo(Welcome.this);
//            String saveHouseInfo = StringtoListUtil.SceneList2String(housingInfoList);
//            Utils.saveString(Welcome.this,company+"houseInfo",saveHouseInfo);//以公司名称保存房产信息列表

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
