package com.example.germanriveros.androiddb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by germanriveros on 19-05-16.
 */
public class DbHelper extends SQLiteOpenHelper
{

    private static final String DB_NAME = "CONTACTOS";
    private static final int DB_SCHEME_VERSION = 1;

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }


    public DbHelper(Context context)
    {
        super(context,DB_NAME, null, DB_SCHEME_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL(DataBaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}


}//.
