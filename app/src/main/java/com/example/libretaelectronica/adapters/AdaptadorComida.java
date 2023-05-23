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
import com.example.libretaelectronica.models.Producto;


import java.util.List;

public class AdaptadorComida extends ArrayAdapter<Comida> {

    private Activity context;
    private int layoutListaComida;
    private List<Comida> listaComida;

    public AdaptadorComida(@NonNull Activity context,
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
        TextView nombreProducto=fila.findViewById(R.id.nombreItem);

        Producto itemComida=listaComida.get(position);

        nombreProducto.setText(itemComida.getNombreProducto());

        return fila;

    }

}
