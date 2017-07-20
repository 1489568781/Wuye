package com.aojing.alipay_module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aojing.baselibrary.BaseActivity;

public class AlipayMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alipay_activity_main);


    }
    public void send(View view){
        Intent intent = new Intent(AlipayMainActivity.this,Second.class);
        startActivity(intent);

    }
}
