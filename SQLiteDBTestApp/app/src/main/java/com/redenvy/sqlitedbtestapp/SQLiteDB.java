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
    private static final String TAG = "R3DENVY";

    Context context;
    SQLiteDatabase sqlDB;

    public SQLiteDB(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(_id integer primary key autoincrement,car_name text, brand_name text, patch_name text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String car, String brand, String patch){
        sqlDB = getWritableDatabase();
        sqlDB.execSQL("insert into "+TABLE_NAME+" (car_name,brand_name,patch_name) values('"+car+"','"+brand+"','"+patch+"');");
    }

    public String getData(){
        sqlDB = getReadableDatabase();
        Cursor cr = sqlDB.rawQuery("select * from '"+TABLE_NAME+"';",null);
        String output = "";
        while (cr.moveToNext()){
            String car = cr.getString(1);
            String brand = cr.getString(2);
            String patch = cr.getString(3);
            output = output + car+"_"+brand+"_"+patch+".";
        }
        return output;
    }

    public void cleanSlate(){
        sqlDB = getWritableDatabase();
        sqlDB.execSQL("delete from "+TABLE_NAME);
    }
}