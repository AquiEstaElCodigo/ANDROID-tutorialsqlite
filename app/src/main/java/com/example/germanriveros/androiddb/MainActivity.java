package com.example.germanriveros.androiddb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{


    //https://www.youtube.com/watch?v=zH2E1IoIXh4


    private DataBaseManager dataBaseManager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;
     TextView tv;
    private ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseManager = new DataBaseManager(this);

        btn = (ImageButton) findViewById(R.id.imageButton);
        lista = (ListView) findViewById(R.id.listView);
        tv = (TextView) findViewById(R.id.editText);

        //dataBaseManager.insertar("juan","777777");
        //dataBaseManager.insertarDos("ana","8888888");
        //dataBaseManager.insertar("ale","555555");

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                new BuscarTask().execute();

            }
        });


        String[] from = new String[]{dataBaseManager.CN_NAME, dataBaseManager.CN_PHONE};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};

        cursor = dataBaseManager.cargarCursorContactos();
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                cursor,from, to, 0);

        lista.setAdapter(adapter);

    }

    private class BuscarTask extends AsyncTask<Void,Void, Void>
    {

        @Override
        protected void onPreExecute()
        {

            Toast.makeText(getApplicationContext(),"Buscando...",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... params)
        {
            cursor = dataBaseManager.buscarContacto(tv.getText().toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            adapter.changeCursor(cursor);
            Toast.makeText(getApplicationContext(),"Busqueda finalizada",Toast.LENGTH_LONG).show();
        }


    }


}//.
