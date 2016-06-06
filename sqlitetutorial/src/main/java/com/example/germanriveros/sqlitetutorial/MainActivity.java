package com.example.germanriveros.sqlitetutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private ClientesDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new ClientesDB(this);

        Cliente cliente1 = new Cliente("Ana","11111","ana@ana.cl");
        Cliente cliente2 = new Cliente("Isa","22222", "isa@isa.net");
        Cliente cliente3 = new Cliente("Abel","33333","abel@abel.org");
        Cliente cliente4 = new Cliente("Cain","44444","cain@cain.bl");


        Log.i("Base de Datos", "Insertando clientes...");

        db.insertCliente(cliente1);
        db.insertCliente(cliente2);
        db.insertCliente(cliente3);
        db.insertCliente(cliente4);

        Log.i(" Base de Datos: ", "Mostrando Clientes...");
        mostrarClientesLog();


            Log.i(" Base de Datos: ", "Borrando Cliente con id 1...");
            db.deleteCliente(1);
            mostrarClientesLog();

        Log.i(" Base de Datos: ", "cambiando el nombre de cliente 2...");
        cliente2.setName("Juanita");
          db.updateCliente(cliente2);
          mostrarClientesLog();

         Log.i(" Base de Datos: ", "Buscando datos de cliente...");
         Cliente cliente = new Cliente();
         cliente = db.buscarCliente("Abel");
        Log.i("---> Base de Datos: ", "Los datos de Abel son: " + cliente.toString() );

          Log.i(" Base de Datos: ", "Cambiando el nombre de Cain...");
         cliente.setName("David");
         db.updateCliente(cliente4);
         mostrarClientesLog();



    }

    private void mostrarClientesLog()
    {


        List lista = db.loadClientes();

        for (Object cliente : lista )
            Log.i("---> Base de Datos: ", cliente.toString());

    }

}
