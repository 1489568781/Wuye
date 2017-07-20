package com.aojing.main_module.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aojing.main_module.R;
import com.aojing.util.Get_Img_Str_From_Http;
import com.aojing.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 修改登录密码
 * Created by wxw on 2017/3/10.
 */
public class Reset_Login_Password extends AppCompatActivity {

    private TextView reset_login_password_back;//返回按钮
    private CheckBox cb_show_password;//显示密码按钮
    private EditText et_passWord ;//旧密码
    private EditText et_new_passWord ;//新密码
    private Button bt_post_reset_login_password;//提交
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_login_password);
        et_passWord = (EditText)findViewById(R.id.et_passWord);
        et_new_passWord = (EditText)findViewById(R.id.et_new_passWord);
        reset_login_password_back = (TextView)findViewById(R.id.reset_login_password_back);
        reset_login_password_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cb_show_password = (CheckBox)findViewById(R.id.cb_show_password);
//        cb_show_password.setChecked(true);
        cb_show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    et_passWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_new_passWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_passWord.setSelection(et_passWord.getText().length());
                    et_new_passWord.setSelection(et_new_passWord.getText().length());
//                    System.out.println("checked");
                }else {
                    et_passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_new_passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_passWord.setSelection(et_passWord.getText().length());
                    et_new_passWord.setSelection(et_new_passWord.getText().length());
//                    System.out.println("not checked");
                }
            }
        });
        if (cb_show_password.isChecked()){

        }
        bt_post_reset_login_password = (Button)findViewById(R.id.bt_post_reset_login_password);
        bt_post_reset_login_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_new_passWord.getText().toString().trim().equals("")&&!et_passWord.getText().toString().trim().equals("")){
                    send();//发送到服务器
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
                        Intent intent = new Intent(Reset_Login_Password.this,Reset_Login_Password_Success.class);
                        intent.putExtra("type","登录密码");
                        startActivity(intent);
                    }else {
                        Toast.makeText(Reset_Login_Password.this,"修改失败！请检查原密码是否正确~",Toast.LENGTH_SHORT).show();
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
    private  String projectID = "";
    protected void sendMsg1() {
        try {
            String userName = Utils.readString(getApplicationContext(),"key_save_userName");
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            result = get_img_str_from_http.getResetPassWord("changePwd",userName,et_passWord.getText().toString().trim(),et_new_passWord.getText().toString().trim());//获取物业公司的所有房间
            System.out.println("************changePass****" + result);

            //对房间的名称列表roomList进行保存
//            Utils.clearAllSaveInfo(Welcome.this);
//            String saveHouseInfo = StringtoListUtil.SceneList2String(housingInfoList);
//            Utils.saveString(Welcome.this,company+"houseInfo",saveHouseInfo);//以公司名称保存房产信息列表

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
