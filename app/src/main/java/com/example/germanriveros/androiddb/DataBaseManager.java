package com.example.germanriveros.androiddb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by germanriveros on 19-05-16.
 */
public class DataBaseManager
{

    public static final String TABLE_NAME = "contacto";
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "nombre";
    public static final String CN_PHONE = "telefono";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + CN_ID + " integer primary key autoincrement, "
            + CN_NAME + " text not null,"
            + CN_PHONE + " text); ";


    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;


    public DataBaseManager(Context context)
    {

        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();

    }


    private ContentValues generarContentValues(String nombre, String telefono)
    {

        ContentValues contentValues = new ContentValues();

        contentValues.put(CN_NAME, nombre);
        contentValues.put(CN_PHONE,telefono);

        return contentValues;
    }


    public void insertar(String nombre, String telefono)
    {

        sqLiteDatabase.insert(TABLE_NAME, null, generarContentValues(nombre,telefono));

    }

    public void insertarDos(String nombre, String telefono)
    {

        sqLiteDatabase.execSQL("insert into " +
                TABLE_NAME + " values(null, '" + nombre + "','" + telefono + "')");

    }

    public void eliminar(String nombre)
    {

        sqLiteDatabase.delete(TABLE_NAME,CN_NAME + "=?", new String[]{nombre});
    }

    public void eliminarMultiple(String nom1, String nom2)
    {
        sqLiteDatabase.delete(TABLE_NAME,CN_NAME + "IN(?,?)", new String[]{nom1,nom2});
    }

    public void modificarTelefono(String nombre, String nuevoTelefono)
    {
        sqLiteDatabase.update(TABLE_NAME, generarContentValues(nombre, nuevoTelefono),
                CN_NAME + "=?",new String[]{nombre});
    }

    public Cursor cargarCursorContactos()
    {

        String[] columnas = new String[]{CN_ID,CN_NAME,CN_PHONE};
        return sqLiteDatabase.query(TABLE_NAME, columnas,null,null,null,null,null);
    }


    public Cursor buscarContacto(String nombre)
    {
        String[] columnas = new String[]{CN_ID,CN_NAME,CN_PHONE};


        try
        {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        return sqLiteDatabase.query(TABLE_NAME, columnas, CN_NAME + "=?",
                new String[]{nombre}, null, null, null);
    }


}//.















