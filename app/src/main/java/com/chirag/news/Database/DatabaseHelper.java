package com.chirag.news.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "News.db";
    public static final String TABLE_NAME = "news_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "IMAGE";
    public static final String COL_4 = "CONTENT";
    public static final String COL_5 = "UID";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID TEXT,TITLE TEXT ,IMAGE TEXT,CONTENT TEXT, UID INTEGER PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String title,String image,String content, String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,image);
        contentValues.put(COL_4,content);
        contentValues.put(COL_5,Integer.valueOf(uid));
        long result = db.insertWithOnConflict(TABLE_NAME,null ,contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getData(String id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE columnName = ?";
        Cursor res = db.rawQuery(query, new String[] {id});
        return res;
    }



    public Integer deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null,null);
    }
}