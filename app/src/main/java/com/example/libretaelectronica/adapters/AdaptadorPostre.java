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
import com.example.libretaelectronica.models.Postre;
import com.example.libretaelectronica.models.Producto;

import java.util.List;

public class AdaptadorPostre extends ArrayAdapter<Postre> {
    private Activity context;
    private int layoutListaPostre;
    private List<Postre> listaPostre;

    public AdaptadorPostre(@NonNull Activity context,
                           int layout,
                           List<Postre>listaPostre) {

        super(context, layout, listaPostre);
        this.context = context;
        this.layoutListaPostre = layout;
        this.listaPostre= listaPostre;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();

        View fila=layoutInflater.inflate(layoutListaPostre,null);
        TextView cantidadPostre=fila.findViewById(R.id.cantidadItemPostre);
        TextView nombrePostre=fila.findViewById(R.id.nombreItem);
        TextView precioPostre = fila.findViewById(R.id.postrePrecioItem);

        Postre itemPostre=listaPostre.get(position);

        int cantidad=itemPostre.getCantidad();
        float precio=itemPostre.getPrecioProducto();
        String cantidadString=String.valueOf(cantidad);
        String precioString = String.valueOf(precio);


        nombrePostre.setText(itemPostre.getNombreProducto());
        cantidadPostre.setText(cantidadString);
        precioPostre.setText(precioString);

        return fila;

    }
}