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
import com.example.libretaelectronica.models.Factura;
import com.example.libretaelectronica.models.Producto;

import java.util.List;

public class AdaptadorFactura extends ArrayAdapter<Factura> {
    private Activity context;
    private int layoutListaFactura;
    private List<Factura> listaFactura;

    public AdaptadorFactura(@NonNull Activity context,
                                  int layout,
                                  List<Factura>listaFactura) {

        super(context, layout, listaFactura);
        this.context = context;
        this.layoutListaFactura = layout;
        this.listaFactura = listaFactura;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();

        View fila=layoutInflater.inflate(layoutListaFactura,null);

        TextView numeroMesa=fila.findViewById(R.id.numeroMesa);
        TextView totalView=fila.findViewById(R.id.totalView);

        Factura itemFactura=listaFactura.get(position);
        String nMesa=itemFactura.getMesa();
        float facturaTotal=itemFactura.getTotal();
        System.out.println(facturaTotal);
        String totalString=String.valueOf(facturaTotal);
        numeroMesa.setText(nMesa);
        totalView.setText(totalString);


        return fila;

    }
}
