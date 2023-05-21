package com.example.libretaelectronica.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.models.Producto;

import java.util.List;

public class AdaptadorPersonalizado extends ArrayAdapter<Producto>{

    private Activity context;
    private int layoutPersonalizado;
    private List<Producto> listaProductos;

    public AdaptadorPersonalizado(@NonNull Activity context,
                                  int layout,
                                  List<Producto>listaProducto) {

        super(context, layout, listaProducto);
        this.context = context;
        this.layoutPersonalizado = layout;
        this.listaProductos = listaProducto;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();

        View fila=layoutInflater.inflate(layoutPersonalizado,null);
        TextView nombreProducto=fila.findViewById(R.id.nombreItem);

        Producto itemProducto=listaProductos.get(position);

        nombreProducto.setText(itemProducto.getNombreProducto());

       return fila;

    }
}
