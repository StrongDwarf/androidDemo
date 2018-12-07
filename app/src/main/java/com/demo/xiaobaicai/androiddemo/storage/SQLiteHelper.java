package com.demo.xiaobaicai.androiddemo.storage;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;  //数据库版本号
    private static String db_name="test";  //数据库名称

    public SQLiteHelper(Context context){
        super(context,db_name,null,VERSION);
    }

    //当第一次建库的时候，调用该方法
    @Override
    public void onCreate(SQLiteDatabase db){
        //创建数据库的时候把学生表创建好
        String sql = "create table user(id int,name varchar(20),age int)";
        db.execSQL(sql);
        Log.i("sqlite","onCreate...");
    }

    //当更新数据库版本号的时候就会执行该方法
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion,int newVersion){
        Log.i("sqlite","update Database....");
    }
}