package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorCobro;
import com.example.libretaelectronica.databinding.ActivityCobrarBinding;
import com.example.libretaelectronica.models.Producto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cobrar extends AppCompatActivity {
    ActivityCobrarBinding cobrarBinding;
    ArrayList<Producto> listaProducto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobrar);
        cobrarBinding = ActivityCobrarBinding.inflate(getLayoutInflater());
        View view = cobrarBinding.getRoot();
        setContentView(view);
        String numeroMesa = getIntent().getStringExtra("mesa");

        listaProducto = (ArrayList<Producto>) getIntent().getSerializableExtra("lista");


        cobrarBinding.numeroMesa.setText(numeroMesa);
        AdaptadorCobro adaptadorCobro = new AdaptadorCobro(this, R.layout.item_cobro, listaProducto);
        cobrarBinding.listaFactura.setAdapter(adaptadorCobro);
        calculoTotal(listaProducto);
    }

    private void calculoTotal(ArrayList<Producto>listaProducto){
        float resultado= 0.0F;
        Iterator<Producto>iterator=listaProducto.iterator();

        while(iterator.hasNext()){
            Producto producto=iterator.next();
            int cantidad=producto.getCantidad();
            float precio=producto.getPrecioProducto();
            resultado+=multiplicacion(cantidad,precio);

        }

        float resultadoLimitado=limiteFloat(resultado);
        String textoTotal=String.valueOf(resultadoLimitado);
        cobrarBinding.totalCobro.setText(textoTotal);
    }

    private float multiplicacion(int cantidad,float precio){
        float resultado=cantidad*precio;

        return resultado;
    }

    private float limiteFloat(float numero){

        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Define el formato con dos decimales
        String formattedNumber = decimalFormat.format(numero);
        return Float.parseFloat(formattedNumber);
    }
}
