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


/**
 * 更改姓名
 * Created by wxw on 2017/3/10.
 */
public class Reset_RealName extends AppCompatActivity {
    private EditText et_newRealName ;
    private Button bt_post_reset_realName;
    private TextView reset_realName_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_realname);
        et_newRealName = (EditText)findViewById(R.id.et_newRealName);
        bt_post_reset_realName = (Button)findViewById(R.id.bt_post_reset_realName);
        reset_realName_back = (TextView)findViewById(R.id.reset_realName_back);
        reset_realName_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_post_reset_realName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!et_newRealName.getText().toString().trim().equals("")){
                    send();
                }else {
                    Toast.makeText(getApplicationContext(),"新姓名不能为空！",Toast.LENGTH_SHORT).show();
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
                        Utils.saveString(getApplicationContext(),userName+"realName",et_newRealName.getText().toString());
                        Intent intent = new Intent(Reset_RealName.this,Reset_Login_Password_Success.class);
                        intent.putExtra("type","用户姓名");
                        startActivity(intent);
                    }else {
                        Toast.makeText(Reset_RealName.this,"修改失败！",Toast.LENGTH_SHORT).show();
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

    private String userName = "";
    String result = "";
    protected void sendMsg1() {
        try {
            userName = Utils.readString(getApplicationContext(),"key_save_userName");
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            result = get_img_str_from_http.resetRealName("changeRealName",userName,et_newRealName.getText().toString().trim());
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
