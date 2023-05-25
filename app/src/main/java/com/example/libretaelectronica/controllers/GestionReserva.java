package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.databinding.ActivityGestionReservaBinding;

public class GestionReserva extends AppCompatActivity implements View.OnClickListener {

    ActivityGestionReservaBinding gestionReservaBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_reserva);

       gestionReservaBinding=ActivityGestionReservaBinding.inflate(getLayoutInflater());
        View view=gestionReservaBinding.getRoot();
        setContentView(view);
        gestionReservaBinding.btnNuevaReserva.setOnClickListener(this);
        gestionReservaBinding.btnCambiarReserva.setOnClickListener(this);
        gestionReservaBinding.btnEliminarReserva.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){

            case R.id.btnNuevaReserva:
                i=new Intent(this, NuevaReserva.class);
                startActivity(i);
                break;
            case R.id.btnCambiarReserva:
                break;
            case R.id.btnEliminarReserva:
                break;
        }
    }
}