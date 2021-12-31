package com.redenvy.sqlitedbtestapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class SQLiteDB extends SQLiteOpenHelper {

    static final private String DB_NAME = "Task Database";
    static final private String TABLE_NAME = "Cars";
    static final private int DB_VERSION = 1;

    Context context;
    SQLiteDatabase sqlDB;

    public SQLiteDB(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(_id integer primary key autoincrement,car_name text, brand_name text, patch_name text);");
        Toast.makeText(context, "Database Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String car, String brand, String patch){
        sqlDB = getWritableDatabase();
        sqlDB.execSQL("insert into "+TABLE_NAME+" (car_name,brand_name,patch_name) values('"+car+"','"+brand+"','"+patch+"');");
        Toast.makeText(context, "Data Inserted", Toast.LENGTH_SHORT).show();
    }

    public void getData(){
        sqlDB = getReadableDatabase();
        Cursor cr = sqlDB.rawQuery("select * from "+DB_NAME,null);
        StringBuilder stringBuilder = new StringBuilder();

        while (cr.moveToNext()){
            String car = cr.getString(1);
            String brand = cr.getString(2);
            String patch = cr.getString(3);
            stringBuilder.append(brand+","+car+" added in "+patch+"\n");
            Log.e("DB Test", "Database: "+stringBuilder.toString());
        }
    }
}
