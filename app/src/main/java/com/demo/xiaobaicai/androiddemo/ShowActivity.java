package com.demo.xiaobaicai.androiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showactivity);

        findViewById(R.id.btn_visiable_start_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_visiable_start_activity:

        }
    }

}