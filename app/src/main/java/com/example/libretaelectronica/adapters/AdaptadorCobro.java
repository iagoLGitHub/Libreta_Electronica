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

public class AdaptadorCobro extends ArrayAdapter<Producto> {
    private Activity context;
    private int layoutCobro;
    private List<Producto> listaProductos;


    public AdaptadorCobro(@NonNull Activity context,
                            int layout,
                            List<Producto>listaProducto) {

        super(context, layout, listaProducto);
        this.context = context;
        this.layoutCobro = layout;
        this.listaProductos = listaProducto;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();

        View fila=layoutInflater.inflate(layoutCobro,null);
        TextView cantidadProducto=fila.findViewById(R.id.cantidadItemProductoCobro);
        TextView nombreProducto=fila.findViewById(R.id.nombreItemCobro);
        TextView precioProducto=fila.findViewById(R.id.productoPrecioCobroItem);

        Producto itemProducto=listaProductos.get(position);
        int cantidad=itemProducto.getCantidad();
        float precio=itemProducto.getPrecioProducto();

        String cantidadString=String.valueOf(cantidad);
        String precioString=String.valueOf(precio);
        cantidadProducto.setText(cantidadString);
        nombreProducto.setText(itemProducto.getNombreProducto());
        precioProducto.setText(precioString);

        return fila;

    }
}


