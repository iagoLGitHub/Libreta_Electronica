package com.example.libretaelectronica.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.models.Reserva;

import java.util.List;

public class AdaptadorReserva extends ArrayAdapter<Reserva> {


    private Context context;
    private int layoutReserva;
    private List<Reserva> listaReservas;

    public AdaptadorReserva(Context context, int layoutReserva, List<Reserva> listaReservas) {
        super(context, layoutReserva, listaReservas);
        this.context = context;
        this.layoutReserva = layoutReserva;
        this.listaReservas = listaReservas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View fila = inflater.inflate(layoutReserva, parent, false);

        // Obtener referencia a los elementos de la fila
        TextView textViewNombre = fila.findViewById(R.id.itemNombreReserva);
        TextView textViewFechaHora = fila.findViewById(R.id.itemHora);

        // Obtener el objeto Reserva correspondiente a la posición actual
        Reserva reserva = listaReservas.get(position);

        // Establecer los valores en los elementos de la fila
        textViewNombre.setText(reserva.getNombre());

        return fila;
    }
}