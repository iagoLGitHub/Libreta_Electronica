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
import com.example.libretaelectronica.models.Bebida;
import com.example.libretaelectronica.models.Producto;

import java.util.List;

public class AdaptadorBebida extends ArrayAdapter<Bebida> {

    private Activity context;
    private int layoutListaBebida;
    private List<Bebida> listaBebida;

    public AdaptadorBebida(@NonNull Activity context,
                           int layout,
                           List<Bebida> listaBebida) {

        super(context, layout, listaBebida);
        this.context = context;
        this.layoutListaBebida = layout;
        this.listaBebida = listaBebida;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();

        View fila = layoutInflater.inflate(layoutListaBebida, null);
        TextView nombreBebida = fila.findViewById(R.id.nombreItem);
        TextView precioBebida = fila.findViewById(R.id.bebidaPrecioItem);

        Bebida itemBebida = listaBebida.get(position);

        nombreBebida.setText(itemBebida.getNombreProducto());
        float precio = itemBebida.getPrecioProducto();
        String precioString = String.valueOf(precio);
        precioBebida.setText(precioString);

        return fila;

    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

}
