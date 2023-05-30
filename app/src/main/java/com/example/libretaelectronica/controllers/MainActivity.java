package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;
;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding activityMain = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMain.getRoot();
        setContentView(view);

        activityMain.btnComanda.setOnClickListener(this);
        activityMain.btnReserva.setOnClickListener(this);
        activityMain.btnAlmacen.setOnClickListener(this);
        activityMain.btnFacturas.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.btnComanda:

                i = new Intent(this, SeleccionarMesa.class);
                startActivity(i);
                break;

            case R.id.btnReserva:
                i = new Intent(this, ReservaLista.class);
                startActivity(i);
                break;

            case R.id.btnAlmacen:
                i=new Intent(this,GestionProducto.class);
                startActivity(i);
                break;

            case R.id.btnFacturas:
                i=new Intent(this,listaFacturas.class);
                startActivity(i);
                break;

        }
    }
}