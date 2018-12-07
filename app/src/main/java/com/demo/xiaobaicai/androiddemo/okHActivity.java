package com.demo.xiaobaicai.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.demo.xiaobaicai.androiddemo.okHttp.HTTPCaller;
import com.demo.xiaobaicai.androiddemo.okHttp.NameValuePair;
import com.demo.xiaobaicai.androiddemo.okHttp.RequestDataCallback;

import java.util.ArrayList;
import java.util.List;

public class okHActivity extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okh);

        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get:
                //发送get请求
                HTTPCaller.getInstance().get(User.class,"http://192.168.43.74:8083/test",null,requestDataCallback);
                break;
            case R.id.btn_post:
                //发送post请求
                List<NameValuePair> postParam = new ArrayList<>();
                postParam.add(new NameValuePair("username","xiaobaicai"));
                postParam.add(new NameValuePair("password","hello,world"));
                HTTPCaller.getInstance().post(User.class,"http://192.168.43.74:8083/test",null,postParam,requestDataCallback);
                break;
        }
    }

    private RequestDataCallback requestDataCallback = new RequestDataCallback<User>() {
        @Override
        public void dataCallback(User user) {
            if(user == null){
                Log.i("okh","请求失败");
            }else{
                Log.i("okh","获取用户信息"+user.toString());
            }
        }
    };
}