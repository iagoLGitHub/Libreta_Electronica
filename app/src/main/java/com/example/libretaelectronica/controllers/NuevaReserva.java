package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.example.libretaelectronica.databinding.ActivityNuevaReservaBinding;
import com.example.libretaelectronica.models.Reserva;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class NuevaReserva extends AppCompatActivity implements View.OnClickListener {


    ActivityNuevaReservaBinding nuevaReservaBinding;
    private int dia;
    private int mes;
    private int anho;
    private int hora;
    private int min;
    private String nombreReserva;
    Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nuevaReservaBinding = ActivityNuevaReservaBinding.inflate(getLayoutInflater());
        View view = nuevaReservaBinding.getRoot();
        setContentView(view);
        nuevaReservaBinding.btnGuardar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Date date;
        Reserva reserva;

        if (v.getId() == nuevaReservaBinding.btnGuardar.getId()) {
            dia = nuevaReservaBinding.datePicker.getDayOfMonth();
            mes = nuevaReservaBinding.datePicker.getMonth();
            anho = nuevaReservaBinding.datePicker.getYear();
            nombreReserva = nuevaReservaBinding.editNombre.getText().toString();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                hora = nuevaReservaBinding.timePicker.getHour();
                min = nuevaReservaBinding.timePicker.getMinute();
                date=new Date(anho,mes,dia,hora,min);
                reserva=new Reserva(date,nombreReserva);
                System.out.println(reserva);
            } else {

                hora = nuevaReservaBinding.timePicker.getCurrentHour();
                min = nuevaReservaBinding.timePicker.getCurrentMinute();
                date=new Date(anho,mes,dia,hora,min);
                reserva=new Reserva(date,nombreReserva);
                System.out.println(reserva);
            }

            /**Gestionar Base de datos aqui*/

        }
    }

}