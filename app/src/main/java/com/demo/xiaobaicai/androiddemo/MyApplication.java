package com.demo.xiaobaicai.androiddemo;

import android.app.Application;

import com.demo.xiaobaicai.androiddemo.okHttp.HTTPCaller;
import com.demo.xiaobaicai.androiddemo.okHttp.HttpConfig;
import com.demo.xiaobaicai.androiddemo.utils.Utils;

public class MyApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setAgent(true);  //有代理的情况能不能访问
        httpConfig.setDebug(true);  //是否为debug模式,如果是debug模式，就打印日志
        httpConfig.setTagName("xiaobaicai");  //打印日志的tagname

        //可以添加一些公共字段，每个接口都会带上
        httpConfig.addCommonField("pf","android");
        httpConfig.addCommonField("version_code",""+Utils.getVersionCode(getApplicationContext()));

        //初始化HTTPCaller类
        HTTPCaller.getInstance().setHttpConfig(httpConfig);
    }
}