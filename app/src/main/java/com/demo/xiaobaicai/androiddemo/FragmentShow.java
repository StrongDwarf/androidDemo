package com.demo.xiaobaicai.androiddemo;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.provider.LiveFolders.INTENT;

public class FragmentShow extends AppCompatActivity implements  View.OnClickListener{
    private FragmentContainer fragmentOne;
    private FragmentContainer fragmentTwo;
    private FragmentContainer fragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        fragmentOne = new FragmentContainer(1);
        fragmentTwo = new FragmentContainer(2);
        fragmentThree = new FragmentContainer(3);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_remove).setOnClickListener(this);
        findViewById(R.id.btn_replace).setOnClickListener(this);
        findViewById(R.id.btn_show).setOnClickListener(this);
        findViewById(R.id.btn_hide).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //开启一个Fragment事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(v.getId() == R.id.btn_add){
            //添加两个Fragment
            transaction.add(R.id.fl_container,fragmentOne);
            transaction.add(R.id.fl_container,fragmentTwo);
        }else if(v.getId() == R.id.btn_remove){
            //删除第2个
            transaction.remove(fragmentTwo);
        }else if(v.getId() == R.id.btn_replace){
            //替换
            transaction.replace(R.id.fl_container,fragmentThree);
        }else if(v.getId() == R.id.btn_hide){
            //隐藏
            transaction.hide(fragmentThree);
        }else if(v.getId() == R.id.btn_show){
            //显示
            transaction.show(fragmentThree);
        }
        transaction.commit();
    }

}