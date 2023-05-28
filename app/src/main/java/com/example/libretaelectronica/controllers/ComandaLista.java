package com.example.libretaelectronica.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorComanda;
import com.example.libretaelectronica.adapters.AdaptadorComida;
import com.example.libretaelectronica.fragments.BebidaListaFragment;
import com.example.libretaelectronica.databinding.ActivityListaComandaBinding;
import com.example.libretaelectronica.fragments.ComidaListaFragment;
import com.example.libretaelectronica.fragments.PostreListaFragment;
import com.example.libretaelectronica.models.Comida;
import com.example.libretaelectronica.models.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ComandaLista extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    List<Producto> productoLista;
    List<Comida>comidaLista;
    ActivityListaComandaBinding comandaBinding;
    ComidaListaFragment fragmentComida = new ComidaListaFragment();
    BebidaListaFragment fragmentBebida=new BebidaListaFragment();
    PostreListaFragment fragmentPostre=new PostreListaFragment();
    AdaptadorComanda adaptadorPersonalizado;
    Map<String, Producto> productosExistentes = new HashMap<>();

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
//        comandaBinding.listComanda.setOnItemClickListener(this);
        comandaBinding.btnAtras.setOnClickListener(this);
        comandaBinding.btnComida.setOnClickListener(this);
        comandaBinding.btnBebida.setOnClickListener(this);
        comandaBinding.btnPostre.setOnClickListener(this);
        comandaBinding.listaFragmentPrincipal.setVisibility(View.GONE);

        productoLista=new ArrayList<>();
        Producto p1=new Producto("producto1",2);
        Producto p2=new Producto("producto2", (float) 2.6);
        Producto p3=new Producto("producto3", (float) 5.1);
        Producto p4=new Producto("producto4", (float) 1.3);
        productoLista.add(p1);
        productoLista.add(p2);
        productoLista.add(p3);
        productoLista.add(p4);
        AdaptadorComanda adaptadorPersonalizado = new AdaptadorComanda(
                this, R.layout.layoutitem, productoLista);

        comandaBinding.listaComandaView.setAdapter(adaptadorPersonalizado);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucomanda, menu);
        return true;

    }
    /**
     * El boton de calculadora nos hara una llamada del sistema para que nos llevara a la aplicación calculadora*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuCalculadora:
                System.out.println("menuCalculadora");
                Intent i = new Intent();
                i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
                startActivity(i);
                return true;
            case R.id.menuCobro:
                System.out.println("menuCobro");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()){

             case R.id.btnComida:

                 fragmentTransaction.replace(R.id.listaFragmentPrincipal, fragmentComida);
                 fragmentTransaction.commit();
                 comandaBinding.listaFragmentPrincipal.setVisibility(View.VISIBLE);
                 comandaBinding.listaComandaView.setVisibility(View.GONE);
                 break;

            case R.id.btnBebida:

                fragmentTransaction.replace(R.id.listaFragmentPrincipal,fragmentBebida);
                fragmentTransaction.commit();
                comandaBinding.listaFragmentPrincipal.setVisibility(View.VISIBLE);
                comandaBinding.listaComandaView.setVisibility(View.GONE);
                break;

            case R.id.btnPostre:

                fragmentTransaction.replace(R.id.listaFragmentPrincipal,fragmentPostre);
                fragmentTransaction.commit();
                comandaBinding.listaFragmentPrincipal.setVisibility(View.VISIBLE);
                comandaBinding.listaComandaView.setVisibility(View.GONE);
                break;

        }
    }

    public void setComidaLista(List<Comida>comidaListaFragment){
        comidaLista = comidaListaFragment;
        System.out.println("pasa por aqui");

        Iterator<Comida> iterator = comidaLista.iterator();
        while (iterator.hasNext()) {
            Comida comida = iterator.next();
            if (comida.getCantidad() > 0) {
                Producto producto = comida;
                System.out.println(comida.getCantidad());
                boolean encontrado = false;

                Iterator<Producto> productoIterator = productoLista.iterator();
                while (productoIterator.hasNext()) {
                    Producto p = productoIterator.next();
                    if (p.getNombreProducto().equals(producto.getNombreProducto())) {
                        // Actualizar la cantidad del producto existente

                        p.setCantidad(producto.getCantidad());
                        System.out.println(producto.getCantidad());
                        System.out.println(p.getCantidad());
                        encontrado = true;
                        System.out.println(encontrado);
                        break;
                    }
                }

                if (!encontrado) {
                    // Agregar el nuevo producto a productoLista
                    productoLista.add(producto);
                }
            }
            System.out.println("Nombre: " + comida.getNombreProducto() + ", Precio: " + comida.getPrecioProducto());
        }

        comandaBinding.listaFragmentPrincipal.setVisibility(View.GONE);
        comandaBinding.listaComandaView.setVisibility(View.VISIBLE);

        AdaptadorComanda adaptadorPersonalizado = new AdaptadorComanda(
                this, R.layout.layoutitem, productoLista);

        comandaBinding.listaComandaView.setAdapter(adaptadorPersonalizado);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("esto es comandalista");
    }
}