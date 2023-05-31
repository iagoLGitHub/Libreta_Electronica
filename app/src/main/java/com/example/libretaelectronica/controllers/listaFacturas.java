package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.libretaelectronica.Dao.ControladorBbdd;
import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorFactura;
import com.example.libretaelectronica.databinding.ActivityListaFacturasBinding;
import com.example.libretaelectronica.models.Factura;

import java.util.ArrayList;
import java.util.List;

public class listaFacturas extends AppCompatActivity {
    ActivityListaFacturasBinding listaFacturasBinding;
    List<Factura> listaFacturas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_facturas);
        listaFacturasBinding=ActivityListaFacturasBinding.inflate(getLayoutInflater());
        View view=listaFacturasBinding.getRoot();
        setContentView(view);
        listaFacturas=new ArrayList<>();
        busquedaTodasFacturas();
        AdaptadorFactura adaptadorFactura=new AdaptadorFactura(this,R.layout.item_factura,listaFacturas);
        listaFacturasBinding.listaFactura.setAdapter(adaptadorFactura);
    }

    private void busquedaTodasFacturas(){
        ControladorBbdd bbdd = new ControladorBbdd(this);
        SQLiteDatabase db = bbdd.getReadableDatabase();

        System.out.println(db.isOpen());

        String busqueda = "select * from 'facturas'";
        System.out.println(busqueda);
        Cursor c=db.rawQuery(busqueda,null);

        if (c.moveToFirst()) {
            do {
                Factura factura = new Factura(c.getString(1), c.getFloat(2));
                listaFacturas.add(factura);
            } while (c.moveToNext());
        } else {
            System.out.println("No se encontraron facturas");
        }

        c.close(); 
    }
}