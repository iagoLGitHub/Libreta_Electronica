package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorPersonalizado;
import com.example.libretaelectronica.databinding.ActivityListaComandaBinding;
import com.example.libretaelectronica.databinding.ActivityOpcionesComandaBinding;
import com.example.libretaelectronica.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class ListaComanda extends AppCompatActivity implements View.OnClickListener {

    List<Producto> productoLista;
    ActivityListaComandaBinding comandaBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comanda);

        /**creamos un nuevo objeto binding**/
        comandaBinding= ActivityListaComandaBinding.inflate(getLayoutInflater());
        View view=comandaBinding.getRoot();
        setContentView(view);
        /**Recogemos el intent enviado desde la clase seleccionarMesa*/
        String textoMesa=getIntent().getStringExtra("texto");
        comandaBinding.textoCabecera.setText(textoMesa);

        /**Asignamos los escuchadores*/
        comandaBinding.btnAtras.setOnClickListener(this);
        comandaBinding.btnComida.setOnClickListener(this);
        comandaBinding.btnBebida.setOnClickListener(this);
        comandaBinding.btnPostre.setOnClickListener(this);



        productoLista=new ArrayList<>();
        Producto p1=new Producto("producto1",2);
        Producto p2=new Producto("producto2", (float) 2.6);
        Producto p3=new Producto("producto3", (float) 5.1);
        Producto p4=new Producto("producto4", (float) 1.3);
        productoLista.add(p1);
        productoLista.add(p2);
        productoLista.add(p3);
        productoLista.add(p4);
        AdaptadorPersonalizado adaptadorPersonalizado = new AdaptadorPersonalizado(
                this, R.layout.layoutitem, productoLista);

        comandaBinding.listComanda.setAdapter(adaptadorPersonalizado);




    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()){

             case R.id.btnComida:
                 ComidaLista fragment = new ComidaLista();
                 fragmentTransaction.add(R.id.listaFragmentPrincipal, fragment);
                 fragmentTransaction.commit();

            case R.id.btnBebida:
                break;

            case R.id.btnPostre:
                break;

        }
    }
}