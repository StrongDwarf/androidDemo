package com.demo.xiaobaicai.androiddemo;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LauncherActivity extends AppCompatActivity implements  View.OnClickListener{
    public static final String FIRST_LAUNCHER = "first_launcher";  //是否第一次启动
    private final long waitTime = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    public void start(){
        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent;
                if(isFirstLauncher()){
                    //为true时第二次启动，因为第一次启动时会把FIRST_LAUNCHER的值变为true
                    intent = new Intent(LauncherActivity.this,FirstLauncherActivity.class);
                }else{
                    //第一次启动
                    intent = new Intent(LauncherActivity.this,FirstLauncherActivity.class);
                }
                startActivity(intent);

                finish();
            }
        },waitTime);
    }

    /**
     * false:第一次启动,true：第二次启动，这里不能用Activity的getPreferences方法,因为需要多个Activity
     * 使用一个SharedPreference对象,所以调用getSharedPreference方法。
     * @return
     */
    public boolean isFirstLauncher(){
        SharedPreferences sp = getSharedPreferences("xiaobaicai", Context.MODE_PRIVATE);
        return sp.getBoolean(FIRST_LAUNCHER,false);
    }
}