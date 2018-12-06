package com.demo.xiaobaicai.androiddemo;

import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static android.provider.LiveFolders.INTENT;

public class ThreadActivity extends AppCompatActivity implements  View.OnClickListener{
    private TextView tvContent1;
    private TextView tvContent2;
    private TextView tvContent3;
    public static  final int UPDATE_UI = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        tvContent1 = findViewById(R.id.tv_content1);
        tvContent2 = findViewById(R.id.tv_content2);
        tvContent3 = findViewById(R.id.tv_content3);

        new Thread(){
            @Override
            public void run(){
                //用activity的runOnUiThread方法更新ui底层也是handler实现
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvContent1.setText("runOnUiThread更新ui");
                    }
                });
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                tvContent2.post(new Runnable(){
                    @Override
                    public void run(){
                        tvContent2.setText("View Post 方式更新ui");
                    }
                });
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=100;i++){
                    Log.i("MainActivity","当前值是:"+i);
                    Message message = handler.obtainMessage();
                    message.what = UPDATE_UI;
                    message.obj = i;
                    handler.sendMessage(message);
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    if(i==100){
                        i = 0;
                    }
                }
            }
        }).start();

        new DownloadFilesTask().execute("www.downloadfile.com");

        MyRejectedExecutionHandler handler = new MyRejectedExecutionHandler();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,30,TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(128),sThreadFactory,handler);
        for(int i=0;i<10;i++){
            final int iValue = i;
            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(1000);
                    Log.i("threadtest","当前线程id:"+android.os.Process.myTid()+"iValue:"+iValue);
                }
            };
            threadPoolExecutor.execute(runnable1);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.i("xiaobaicai","Handler handler");
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what==UPDATE_UI){
                tvContent3.setText("当前值是:"+msg.obj);
            }
        }
    };

    @Override
    public void onClick(View v) {

    }

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r,"ThreadPoolExecutor new Thread #"+mCount.getAndIncrement());
        }
    };

    private class DownloadFilesTask extends AsyncTask<String,Integer,Long> {
        @Override
        protected  void onPreExecute(){
            Log.i("DownloadFilesTask","执行任务之前");
        }

        protected Long doInBackground(String... url){
            int count = url[0].length(); //第一个字符串
            long totalSize = 0;
            for(int i=0;i<count;i++){
                totalSize += i;
                publishProgress(i);  //执行此方法，会调用onProgressUpdate方法更新下载进度
                //如果取消就结束任务
                if(isCancelled()) break;
            }

            return totalSize;
        }

        protected void onProgressUpdate(Integer... progress){
            Log.i("DownloadFilesTask","当前下载进度:"+progress[0].intValue());
        }

        protected void onPostExecute(Long result){
            Log.i("DownloadFilesTask","下载完成:"+result);
        }
    }

    public static class MyRejectedExecutionHandler implements RejectedExecutionHandler{
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e){
            throw new RejectedExecutionException("任务被拒绝");
        }
    }
}
