package com.kits.couchmanager.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    @SuppressLint("SdCardPath")
    private static final String DATABASE_NAME = "/data/data/com.kits.fitbykaveh/databases/KowsarDb.sqlite";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;

    }


        @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}