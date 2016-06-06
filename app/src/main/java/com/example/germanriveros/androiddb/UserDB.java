package com.example.germanriveros.androiddb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by germanriveros on 19-05-16.
 */
public class UserDB extends SQLiteOpenHelper
{

    static final String name = "SqliteExample";
    Context context;
    static final int version = 1;

                                                //Qué es CursorFactory??
    public UserDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }


    public UserDB(Context context)
    {

        super(context, name, null, version );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("CREATE TABLE IF NOT EXISTS Users (id INT(11) NOT NULL, name TEXT, PRIMARY KEY(id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
    /**
     *  este método (onUpgrade) sirve para la actualización de la base de datos en caso de
     *  modificar algún elemento (modificación de tipo DDL o DML) que se requiera para una
     *  nueva versión de la aplicación.
     */


    public void create(int id, String name)
    {

        ContentValues values = new ContentValues();

        try
        {
            SQLiteDatabase db = this.getWritableDatabase();

            //si hemos abierto correctamente la base de datos
            if(db != null)
            {

                values.put("id", id);
                values.put("name", name);
                db.insert("Users",null,values);

            }
            db.close();

        }
        catch(SQLiteException e)
        {

            Log.e("Error DB: ", e.toString());
        }

    }//..


    public ArrayList getAllUsers()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> Users = new ArrayList<>();
        User user = new User();

        //si hemos abierto correctamente la base de datos
        if( db != null)
        {

            Cursor c = db.rawQuery("SELECT * FROM User",null);

            if(c.moveToFirst())
            {
                //Se recorre el cursor hasta que no existan más registros
                do
                {
                    user.setId(c.getInt(0));
                    user.setName(c.getString(1));

                    Users.add(user);
                    user = new User();

                }while(c.moveToNext());

            }
        }
        db.close();

        return Users;
    }



}//.











