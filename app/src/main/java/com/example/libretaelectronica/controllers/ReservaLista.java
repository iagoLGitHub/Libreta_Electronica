package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorReserva;
import com.example.libretaelectronica.databinding.ActivityMainBinding;
import com.example.libretaelectronica.databinding.ActivityNuevaReservaBinding;
import com.example.libretaelectronica.databinding.ActivityReservaListaBinding;
import com.example.libretaelectronica.models.Reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaLista extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private List<Reserva> listaReservas;
    private ActivityReservaListaBinding reservaListaBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_lista);
        reservaListaBinding=ActivityReservaListaBinding.inflate(getLayoutInflater());
        View view = reservaListaBinding.getRoot();
        setContentView(view);
        listaReservas=new ArrayList<>();
        Date fechaHora1 = new Date(2023, 4, 1, 10, 30); // Fecha: 1 de mayo de 2023, Hora: 10:30 AM
        Reserva reserva1 = new Reserva(fechaHora1, "Reserva 1");

        Date fechaHora2 = new Date(2023, 4, 2, 15, 0); // Fecha: 2 de mayo de 2023, Hora: 3:00 PM
        Reserva reserva2 = new Reserva(fechaHora2, "Reserva 2");

        Date fechaHora3 = new Date(2023, 4, 3, 19, 45); // Fecha: 3 de mayo de 2023, Hora: 7:45 PM
        Reserva reserva3 = new Reserva(fechaHora3, "Reserva 3");

        Date fechaHora4 = new Date(2023, 4, 4, 12, 15); // Fecha: 4 de mayo de 2023, Hora: 12:15 PM
        Reserva reserva4 = new Reserva(fechaHora4, "Reserva 4");

        Date fechaHora5 = new Date(2023, 4, 5, 9, 0); // Fecha: 5 de mayo de 2023, Hora: 9:00 AM
        Reserva reserva5 = new Reserva(fechaHora5, "Reserva 5");

        listaReservas.add(reserva1);
        listaReservas.add(reserva2);
        listaReservas.add(reserva3);
        listaReservas.add(reserva4);
        listaReservas.add(reserva5);
        AdaptadorReserva adaptadorReserva=new AdaptadorReserva(this,R.layout.item_reserva,listaReservas);
        reservaListaBinding.listaReservaView.setAdapter(adaptadorReserva);
        reservaListaBinding.listaReservaView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("aaaa");
    }
}