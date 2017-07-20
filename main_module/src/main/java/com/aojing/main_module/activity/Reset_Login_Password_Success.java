package com.aojing.main_module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aojing.main_module.R;

/**
 * 修改密码成功页
 * Created by wxw on 2017/3/10.
 */
public class Reset_Login_Password_Success extends AppCompatActivity {

    private TextView show_type_name;
    private String typeName = "";
    private Button bt_back_home;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_login_pass_success);
        typeName = getIntent().getStringExtra("type");
        show_type_name = (TextView)findViewById(R.id.show_type_name);
        show_type_name.setText(typeName+"修改成功~");
        bt_back_home = (Button)findViewById(R.id.bt_back_home);
        bt_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reset_Login_Password_Success.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
