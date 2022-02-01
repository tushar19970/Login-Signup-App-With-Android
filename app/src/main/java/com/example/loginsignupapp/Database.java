package com.example.loginsignupapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String databaseName = "demo";
    private static final String user_table = "user_data";


    public Database(@Nullable Context context){
        super(context, databaseName, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String a = "CREATE TABLE " + user_table+"(id INTEGER PRIMARY KEY, name TEXT, user_name TEXT, email Text, password TEXT)";
        sqLiteDatabase.execSQL(a);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + user_table);
        onCreate(sqLiteDatabase);

    }

    public boolean insert_data(String name, String user_name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("name", name);
        c.put("user_name", user_name);
        c.put("email", email);
        c.put("password", password);
        long r = db.insert(user_table, null, c);
        if (r == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean checkuseremail(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_data where email=?", new String[]{email});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkuserEmailPassword(String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_data where email=? and password=?", new String[]{email, password});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}


