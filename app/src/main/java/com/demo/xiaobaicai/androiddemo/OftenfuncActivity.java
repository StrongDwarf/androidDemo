package com.demo.xiaobaicai.androiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class OftenfuncActivity extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oftenfunc);

        findViewById(R.id.btn_start_page).setOnClickListener(this);
        findViewById(R.id.btn_banner).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_page:
                Intent intent = new Intent(OftenfuncActivity.this,LauncherActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_banner:
                Intent intent1 = new Intent(OftenfuncActivity.this,BannerActivity.class);
                startActivity(intent1);
                break;
        }
    }

}