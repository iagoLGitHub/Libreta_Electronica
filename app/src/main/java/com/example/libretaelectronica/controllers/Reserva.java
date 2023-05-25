package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.TimeAnimator;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.libretaelectronica.databinding.ActivityReservaBinding;

public class Reserva extends AppCompatActivity implements View.OnClickListener{


    ActivityReservaBinding reservaBinding;
    private int dia,mes,anho,hora,min;
    private String nombreReserva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reservaBinding =ActivityReservaBinding.inflate(getLayoutInflater());
        View view= reservaBinding.getRoot();
        setContentView(view);
        reservaBinding.btnGuardar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {



        if(v.getId()==reservaBinding.btnGuardar.getId()){
            dia=reservaBinding.datePicker.getDayOfMonth();
            mes=reservaBinding.datePicker.getMonth();
            anho=reservaBinding.datePicker.getYear();
            nombreReserva=reservaBinding.editNombre.getText().toString();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                hora=reservaBinding.timePicker.getHour();
                min=reservaBinding.timePicker.getMinute();
                System.out.println("dia: "+dia+" mes: "+mes+" anho: "+anho);
                System.out.println("hora: "+hora+" min: "+min);
            }else{

                hora=reservaBinding.timePicker.getCurrentHour();
                min=reservaBinding.timePicker.getCurrentMinute();
                System.out.println("dia: "+dia+" mes: "+mes+" anho: "+anho);
                System.out.println("hora: "+hora+" min: "+min);
            }

            /**Gestionar Base de datos aqui*/

        }
    }

}