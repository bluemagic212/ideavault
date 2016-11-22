package com.example.randomestudios.ideavaultapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mrinali on 11/22/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME="IdeaVault.db";
    public  static final String TABLE_NAME = "idea_table";
    public  static final String COL_1 = "ID";
    public  static final String COL_2 = "TYPE";
    public  static final String COL_3 = "TITLE";
    public  static final String COL_4 = "IDEA";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
        /* To check database
        SQLiteDatabase db = this.getWritableDatabase();*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, IDEA TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String idea){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,title);
        contentValues.put(COL_3, idea);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return result;
    }
}
