package com.demo.xiaobaicai.androiddemo;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class NetworkActivity extends AppCompatActivity implements  View.OnClickListener{
    private TextView tvcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);
        findViewById(R.id.btn_gson_fromJson).setOnClickListener(this);
        findViewById(R.id.btn_gson_to_list).setOnClickListener(this);
        findViewById(R.id.btn_gson_to_map).setOnClickListener(this);
        findViewById(R.id.btn_gson_to_string).setOnClickListener(this);
        findViewById(R.id.btn_okh).setOnClickListener(onClickListener);
        findViewById(R.id.btn_storage).setOnClickListener(onClickListener);
        tvcontent = findViewById(R.id.tv_content);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_okh:
                    Intent intent = new Intent(NetworkActivity.this,okHActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_storage:
                    Intent intent1 = new Intent(NetworkActivity.this,StorageActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        new TestGetOrPostThread(v).start();
    }

    /**
     * 使用get方法向服务器提交数据
     * @param userid
     * @return
     */
    private String getUserInfo(String userid){
        //get方式提交就是url拼接的方式
        String path="http://192.168.43.74:8083/test?userid="+userid;
        try{
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);  //设置连接超时时间
            connection.setRequestMethod("GET");  //设置以Get方式提交数据
            if(connection.getResponseCode() == 200){
                //请求成功
                InputStream is = connection.getInputStream();
                return dealResponseResult(is);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    };

    private String login(String username,String password){
        String path="http://192.168.43.74:8083/test";
        try{
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);  //设置连接超时时间
            connection.setRequestMethod("POST");  //设置一post方式提交数据
            String data = "username="+username+"&password="+password;  //请求数据

            //至少要设置的两个请求头
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length",data.length()+"");

            //post方式实际上是以流的方式提交给服务器
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes());

            if(connection.getResponseCode() == 200){
                //状态码==200 请求成功
                InputStream is = connection.getInputStream();
                return dealResponseResult(is);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理服务器的响应结果(将输入流转化成字符串)
     * @param inputStream 服务器的响应输入流
     * @return
     */
    private String dealResponseResult(InputStream inputStream){
        String resultData = null;  //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try{
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data,0,len);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }

    public class TestGetOrPostThread extends Thread{
        private View view;
        public TestGetOrPostThread(View view){
            this.view = view;
        }

        @Override
        public void run(){
            switch (view.getId()){
                case R.id.btn_get:
                    String str = "xiaobaicai";
                    final String getResult = getUserInfo(str);
                    Log.i("networkTest","get获取信息"+getResult);
                    tvcontent.post(new Runnable(){
                        @Override
                        public void run(){
                            tvcontent.setText(getResult);
                        }
                    });
                    break;
                case R.id.btn_post:
                    String username = "xiaobaicai";
                    String password = "xiaohei";
                    final String postResult = login(username,password);
                    Log.i("networkTest","post获取信息"+postResult);
                    tvcontent.post(new Runnable(){
                        @Override
                        public void run(){
                            tvcontent.setText(postResult);
                        }
                    });
                    break;
                case R.id.btn_gson_fromJson:
                    String jsonStr = "{'name':'xiaobaicai','age':21}";
                    final User user = (new Gson()).fromJson(jsonStr,User.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvcontent.setText("user:"+user.toString());
                        }
                    });
                    break;
                case R.id.btn_gson_to_list:
                    String jsonStr1="[{'name':'Uini','age':30},{'name','Lina','age':10}]";
                    final List<User> users = (new Gson()).fromJson(jsonStr1,new TypeToken<List<User>>(){}.getType());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvcontent.setText("user:懒，不显示了");
                        }
                    });
                    break;
                case R.id.btn_gson_to_string:
                    User user2 = new User();
                    user2.setAge(111);
                    user2.setName("xiaobaicai");
                    final String jsonStr2 = (new Gson()).toJson(user2);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvcontent.setText("user:"+jsonStr2);
                        }
                    });
                    break;
            }
        }
    }
}
