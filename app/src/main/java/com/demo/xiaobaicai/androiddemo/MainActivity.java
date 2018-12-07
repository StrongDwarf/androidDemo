package com.demo.xiaobaicai.androiddemo;

import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.provider.LiveFolders.INTENT;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_useXMLFileDecorateView).setOnClickListener(this);
        findViewById(R.id.btn_fragment).setOnClickListener(this);
        findViewById(R.id.btn_tabbar).setOnClickListener(this);
        findViewById(R.id.btn_thread).setOnClickListener(this);
        findViewById(R.id.btn_network).setOnClickListener(this);
        findViewById(R.id.btn_android_high).setOnClickListener(this);
        findViewById(R.id.btn_often_func).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_useXMLFileDecorateView:
                Intent intent = new Intent(MainActivity.this,UseXMLFileDecorateView.class);
                startActivity(intent);
                break;
            case R.id.btn_fragment:
                Intent intent1 = new Intent(MainActivity.this,FragmentShow.class);
                startActivity(intent1);
                break;
            case R.id.btn_tabbar:
                Intent intent2 = new Intent(MainActivity.this,TabBarActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_thread:
                Intent intent3 = new Intent(MainActivity.this,ThreadActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_network:
                Intent intent4 = new Intent(MainActivity.this,NetworkActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_android_high:
                Intent intent5 = new Intent(MainActivity.this,AndroidhighActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_often_func:
                Intent intent6 = new Intent(MainActivity.this,OftenfuncActivity.class);
                startActivity(intent6);
                break;
        }
    }

}
