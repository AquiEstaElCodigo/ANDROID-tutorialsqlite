package com.example.germanriveros.sqlitetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by germanriveros on 30-05-16.
 */
public class ClientesDB
{

    private SQLiteDatabase db;
    private DBHelper dbHelper;


    public ClientesDB(Context context)
    {

        dbHelper = new DBHelper(context);
    }

    private void openReadableDB()
    {

        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB()
    {

        db = dbHelper.getWritableDatabase();
    }

    private void closeDB()
    {

        if(db != null)
            db.close();

    }

    protected static class DBHelper extends SQLiteOpenHelper
    {


        public DBHelper(Context context)
        {

            super(context, ConstantsDB.DB_NAME, null, ConstantsDB.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {

            db.execSQL(ConstantsDB.TABLA_CLIENTES_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }


    }//.

    private ContentValues clienteMapperContentValues(Cliente cliente)
    {

        ContentValues cv = new ContentValues();
        cv.put(ConstantsDB.CLI_NOMBRE, cliente.getName());
        cv.put(ConstantsDB.CLI_TELF, cliente.getTelf());
        cv.put(ConstantsDB.CLI_MAIL, cliente.getMail());

        return cv;
    }

    public long insertCliente(Cliente cliente)
    {

        this.openWriteableDB();
        long rowID = db.insert(ConstantsDB.TABLA_CLIENTES, null, clienteMapperContentValues(cliente));
        this.closeDB();

        return rowID;
    }


    public void updateCliente(Cliente cliente)
    {

        this.openWriteableDB();
        String where = ConstantsDB.CLI_ID + "= ?";
        db.update(ConstantsDB.TABLA_CLIENTES, clienteMapperContentValues(cliente),
                where, new String[]{String.valueOf(cliente.getId())});

        db.close();
    }

    public void deleteCliente(int id)
    {

        this.openWriteableDB();
        String where = ConstantsDB.CLI_ID + "= ?";
        db.delete(ConstantsDB.TABLA_CLIENTES, where, new String[]{String.valueOf(id)});

        this.closeDB();
    }


    public ArrayList loadClientes()
    {

        ArrayList list = new ArrayList();


        this.openReadableDB();

        String[] campos = new String[]
                { ConstantsDB.CLI_ID, ConstantsDB.CLI_NOMBRE, ConstantsDB.CLI_TELF,
                        ConstantsDB.CLI_MAIL };

        Cursor c = db.query(ConstantsDB.TABLA_CLIENTES, campos, null, null, null, null, null);

        try
        {
            while(c.moveToNext())
            {
                Cliente cliente = new Cliente();
                cliente.setId(c.getInt(0));
                cliente.setName(c.getString(1));
                cliente.setTelf(c.getString(2));
                cliente.setMail(c.getString(3));
                list.add(cliente);
            }

        }
        finally
        {
            c.close();
        }
        this.closeDB();

        return list;

    }//..


    public Cliente buscarCliente(String nombre)
    {

        Cliente cliente = new Cliente();

        this.openReadableDB();
        String where = ConstantsDB.CLI_NOMBRE + "= ?";
        String[] whereArgs = {nombre};
        Cursor c = db.query(ConstantsDB.TABLA_CLIENTES, null, where, whereArgs, null, null, null);

        if(c != null || c.getCount() <= 0)
        {
            c.moveToFirst();
            cliente.setId(c.getInt(0)); /////
            cliente.setName(c.getString(1));
            cliente.setTelf(c.getString(2));
            cliente.setMail(c.getString(3));
            c.close();
        }
        this.closeDB();
        return cliente;
    }
}















