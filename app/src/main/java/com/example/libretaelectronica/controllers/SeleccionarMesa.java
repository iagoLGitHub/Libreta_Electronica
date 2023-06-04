package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.Utilidades;
import com.example.libretaelectronica.databinding.ActivitySeleccionarMesaBinding;

public class SeleccionarMesa extends AppCompatActivity implements View.OnClickListener {



    ActivitySeleccionarMesaBinding seleccionarMesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_mesa);
        /**Uso del Binding**/
        seleccionarMesa= ActivitySeleccionarMesaBinding.inflate(getLayoutInflater());
        View view=seleccionarMesa.getRoot();
        setContentView(view);


        seleccionarMesa.btnMesa1.setOnClickListener(this);
        seleccionarMesa.btnMesa2.setOnClickListener(this);
        seleccionarMesa.btnMesa3.setOnClickListener(this);
        seleccionarMesa.btnMesa4.setOnClickListener(this);
        seleccionarMesa.btnMesa5.setOnClickListener(this);
        seleccionarMesa.btnMesa6.setOnClickListener(this);
        seleccionarMesa.btnMesa7.setOnClickListener(this);
        seleccionarMesa.btnMesa8.setOnClickListener(this);
        seleccionarMesa.btnMesa9.setOnClickListener(this);
        seleccionarMesa.btnMesa10.setOnClickListener(this);
        seleccionarMesa.btnMesa11.setOnClickListener(this);
        seleccionarMesa.btnMesa12.setOnClickListener(this);
        seleccionarMesa.btnAtrasSeleccionMesa.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        Intent i=new Intent(this, ComandaLista.class);
        String texto;
        switch (v.getId()){

            case R.id.btnMesa1:
                texto=getString(R.string.mesa1);
                i.putExtra("texto",texto);
                startActivity(i);
                break;

            case R.id.btnMesa2:
                texto=getString(R.string.mesa2);
                i.putExtra("texto",texto);
                startActivity(i);
                break;

            case R.id.btnMesa3:
                texto=getString(R.string.mesa3);
                i.putExtra("texto",texto);
                startActivity(i);
                break;
            case R.id.btnMesa4:
                texto=getString(R.string.mesa4);
                i.putExtra("texto",texto);
                startActivity(i);
                break;

            case R.id.btnMesa5:
                texto=getString(R.string.mesa5);
                i.putExtra("texto",texto);
                startActivity(i);
                break;
            case R.id.btnMesa6:
                texto=getString(R.string.mesa6);
                i.putExtra("texto",texto);
                startActivity(i);
                break;
            case R.id.btnMesa7:
                texto=getString(R.string.mesa7);
                i.putExtra("texto",texto);
                startActivity(i);
                break;
            case R.id.btnMesa8:
                texto=getString(R.string.mesa8);
                i.putExtra("texto",texto);
                startActivity(i);
                break;
            case R.id.btnMesa9:
                texto=getString(R.string.mesa9);
                i.putExtra("texto",texto);
                startActivity(i);
                break;
            case R.id.btnMesa10:
                texto=getString(R.string.mesa10);
                i.putExtra("texto",texto);
                startActivity(i);
                break;
            case R.id.btnMesa11:
                texto=getString(R.string.mesa11);
                i.putExtra("texto",texto);
                startActivity(i);
                break;
            case R.id.btnMesa12:
                texto=getString(R.string.mesa12);
                i.putExtra("texto",texto);
                startActivity(i);
                break;

            case R.id.btnAtrasSeleccionMesa:
                 onBackPressed();
                 break;

        }

    }

}