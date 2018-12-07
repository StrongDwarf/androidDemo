package com.demo.xiaobaicai.androiddemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.demo.xiaobaicai.androiddemo.storage.SQLiteHelper;

import java.io.File;


public class StorageActivity extends AppCompatActivity implements  View.OnClickListener {
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        findViewById(R.id.btn_set_storage).setOnClickListener(this);
        findViewById(R.id.btn_get_storage).setOnClickListener(this);
        findViewById(R.id.btn_create_db).setOnClickListener(this);
        findViewById(R.id.btn_insert_value).setOnClickListener(this);
        findViewById(R.id.btn_update_value).setOnClickListener(this);
        findViewById(R.id.btn_find_all_value).setOnClickListener(this);
        findViewById(R.id.btn_delete_value).setOnClickListener(this);
        findViewById(R.id.btn_get_sdcard).setOnClickListener(this);
        findViewById(R.id.btn_get_appcard).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_storage:
                //存储数据
                SharedPreferences sp = getApplicationContext().getSharedPreferences("filename", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("username","xiaobaicai");
                edit.commit();
                break;
            case R.id.btn_get_storage:
                SharedPreferences sp1 = getApplicationContext().getSharedPreferences("filename",Context.MODE_PRIVATE);
                String username = sp1.getString("username","");
                Log.i("storage","username:"+username);
                break;
            case R.id.btn_create_db:
                createDb(v);
                break;
            case R.id.btn_insert_value:
                addRecord(v);
                break;
            case R.id.btn_update_value:
                updateRecord(v);
                break;
            case R.id.btn_find_all_value:
                findStudent(v);
                break;
            case R.id.btn_delete_value:
                delete(v);
                break;
            case R.id.btn_get_sdcard:
                getSdCard();
                break;
            case R.id.btn_get_appcard:
                getAppCard();
                break;
        }
    }

    //创建数据库
    public void createDb(View view){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
    }

    //向学生表增加一条记录
    private void addRecord(View view){
        ContentValues values = new ContentValues();
        //参数1:表的字段 参数2:字段对应的值
        values.put("id",1);
        values.put("name","test1");
        values.put("age",11);
        //参数1:表名 参数3:插入的数据
        sqLiteDatabase.insert("user",null,values);

        Log.i("sqlite","往学生表添加一条数据");
    }

    //更新记录，将用户表name="test1"的记录年龄修改成88：
    private void updateRecord(View view){
        Log.i("sqlite","updateRecord");

        ContentValues contentValues = new ContentValues();
        contentValues.put("age",88);
        //参数1：表名 参数2：修改的值 参数3：查询条件 参数4：查询条件需要的参数
        sqLiteDatabase.update("user",contentValues,"name=?",new String[]{"test1"});
    }

    //查找用户表所有数据,并打印出来
    private void findStudent(View view){
        Cursor cursor = sqLiteDatabase.query("user",new String[]{"id","name","age"},null,null,null,null,null);
        while(cursor.moveToNext()){
            //判断下一条有没有数据
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            Log.i("sqlite","id:"+id+" name:"+name+" age:"+age);
        }
        cursor.close();
    }

    //删除一条记录的操作
    private void delete(View view){
        sqLiteDatabase.delete("user","name=?",new String[]{"test1"});
        Log.i("sqlite","删除一条数据");
    }

    //获取sd卡目录
    private void getSdCard(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //判断有没有sdcard
            File sdCard = Environment.getExternalStorageDirectory();
            Log.i("sqlite",sdCard.getPath());
        }
    }

    //获取应用程序的存储目录
    private void getAppCard(){
        File getFilesDri = getFilesDir();
        Log.i("sqlite",getFilesDri.getPath());
    }

}