package com.demo.xiaobaicai.androiddemo;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

public class AndroidhighActivity extends AppCompatActivity implements  View.OnClickListener {

    private int id=1111;
    private int number=1;
    private String channelId="channelId1";//渠道id
    private NotificationManager mNotificationManager;

    private String BROADCAST_ACTION="android.intent.action.BROADCAST_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidhigh);

        findViewById(R.id.btn_create_noti).setOnClickListener(this);
        findViewById(R.id.btn_update_noti).setOnClickListener(this);
        findViewById(R.id.btn_delete_noti).setOnClickListener(this);
        findViewById(R.id.btn_custom_noti).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_noti:
                createNoti();
                break;
            case R.id.btn_update_noti:
                updateNoti();
                break;
            case R.id.btn_delete_noti:
                deleteNoti();
                break;
            case R.id.btn_custom_noti:
                customNoti();
                break;
        }
    }

    //创建通知
    private void createNoti(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)     //设置小图标
                .setContentTitle("标题")                 //设置标题
                .setContentText("内容");                  //设置内容

        Intent intent = new Intent(this,NotificationActivity.class);
        PendingIntent ClickPending = PendingIntent.getActivity(this,0,intent,0);
        mBuilder.setContentIntent(ClickPending);
        mBuilder.setAutoCancel(true);       //点击这条通知自动从通知栏取消
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id,mBuilder.build());
    }

    //更新通知
    private void updateNoti(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("更新通知-标题"+(++number))
                .setContentText("更新通知-内容").setNumber(number);

        Intent intent = new Intent(this,NotificationActivity.class);
        PendingIntent ClickPending = PendingIntent.getActivity(this,0,intent,0);
        mBuilder.setContentIntent(ClickPending);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id,mBuilder.build());
    }

    //删除通知
    private void deleteNoti(){
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //mNotificationManager.cancel(id);//根据id删除通知
            mNotificationManager.cancelAll();//删除所有通知
    }

    private void customNoti(){
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //RemoteViews加载xml
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_custom_notification);
        //设置图片 参数1:是我们xml中ImageView设置的id 参数2是资源id
        remoteViews.setImageViewResource(R.id.imageview,R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.tv_title,"这是标题");
        remoteViews.setTextViewText(R.id.tv_content,"这是内容");

        Intent intent=new Intent(this,NotificationActivity.class);
        PendingIntent clickIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new Notification();
        //必须要设置一个图标，通知区域中需要显示
        notification.icon = android.R.drawable.ic_media_play;
        notification.contentView = remoteViews;//自定义布局
        notification.contentIntent = clickIntent;//点击跳转Intent
        mNotificationManager.notify(id, notification);
    }

}