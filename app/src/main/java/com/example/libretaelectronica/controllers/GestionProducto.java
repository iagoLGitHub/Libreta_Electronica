package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.databinding.ActivityCobrarBinding;
import com.example.libretaelectronica.databinding.ActivityGestionProductoBinding;
import com.example.libretaelectronica.databinding.ActivityGestionReservaBinding;
import com.example.libretaelectronica.models.Bebida;
import com.example.libretaelectronica.models.Comida;
import com.example.libretaelectronica.models.Postre;

import java.util.ArrayList;
import java.util.List;

public class GestionProducto extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    ActivityGestionProductoBinding gestionProductoBinding;

    List<Comida>listaComida=new ArrayList<>();
    List<Bebida>listaBebida=new ArrayList<>();
    List<Postre>listaPostre=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_producto);
        gestionProductoBinding=ActivityGestionProductoBinding.inflate(getLayoutInflater());
        View view=gestionProductoBinding.getRoot();
        setContentView(view);
        gestionProductoBinding.spSeleccionProducto.setOnItemSelectedListener(this);
        gestionProductoBinding.listaGestion.setOnItemClickListener(this);
        registerForContextMenu(gestionProductoBinding.listaGestion);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position){

            case 0:
                /*Aqui iria la busqueda de la listacomida*/
                break;
            case 1:
                /*Aqui iria la busqueda de listaBebida*/
                break;

            case 2:
                /*Aqui iria la busqueda de lista postre*/

                break;
        }


    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_gestion_context, menu);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




        }
    }
