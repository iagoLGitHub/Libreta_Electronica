package com.example.libretaelectronica.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.models.Comida;

import java.util.List;

public class AdaptadorComidaGestion extends ArrayAdapter<Comida> {


    private Activity context;
    private int layoutListaComida;
    private List<Comida> listaComida;

    public AdaptadorComidaGestion(@NonNull Activity context,
                           int layout,
                           List<Comida>listaComida) {

        super(context, layout, listaComida);
        this.context = context;
        this.layoutListaComida = layout;
        this.listaComida = listaComida;
    }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=context.getLayoutInflater();

            View fila=layoutInflater.inflate(layoutListaComida,null);
            TextView nombreComida=fila.findViewById(R.id.nombreItem);
            TextView precioComida=fila.findViewById(R.id.comidaPrecioItem);


            Comida itemComida=listaComida.get(position);
            float precio=itemComida.getPrecioProducto();
            String precioString=String.valueOf(precio);


            nombreComida.setText(itemComida.getNombreProducto());
            precioComida.setText(precioString);


            return fila;

        }

    }