package com.example.libretaelectronica.controllers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorComanda;
import com.example.libretaelectronica.fragments.BebidaListaFragment;
import com.example.libretaelectronica.databinding.ActivityListaComandaBinding;
import com.example.libretaelectronica.fragments.ComidaListaFragment;
import com.example.libretaelectronica.fragments.PostreListaFragment;
import com.example.libretaelectronica.models.Bebida;
import com.example.libretaelectronica.models.Comida;
import com.example.libretaelectronica.models.Postre;
import com.example.libretaelectronica.models.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComandaLista extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public final static int REQUESTCOMANDA = 100;
    ArrayList<Producto> productoLista;
    List<Comida> comidaLista;
    List<Bebida> bebidaLista;
    List<Postre> postreLista;
    ActivityListaComandaBinding comandaBinding;
    ComidaListaFragment fragmentComida = new ComidaListaFragment();
    BebidaListaFragment fragmentBebida = new BebidaListaFragment();
    PostreListaFragment fragmentPostre = new PostreListaFragment();
    AdaptadorComanda adaptadorPersonalizado;
    private String textoMesa = "";
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_PRODUCT_LIST = "productList";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comanda);

        /**creamos un nuevo objeto binding**/
        comandaBinding = ActivityListaComandaBinding.inflate(getLayoutInflater());
        View view = comandaBinding.getRoot();
        setContentView(view);
        /**Recogemos el intent enviado desde la clase seleccionarMesa*/
        textoMesa = getIntent().getStringExtra("texto");
        comandaBinding.textoCabecera.setText(textoMesa);

        /**Asignamos los escuchadores*/
        comandaBinding.listaComandaView.setOnItemClickListener(this);
        comandaBinding.btnAtras.setOnClickListener(this);
        comandaBinding.btnComida.setOnClickListener(this);
        comandaBinding.btnBebida.setOnClickListener(this);
        comandaBinding.btnPostre.setOnClickListener(this);
        comandaBinding.listaFragmentPrincipal.setVisibility(View.GONE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ComandaLista.this);


       cargarListaGuardada();

            adaptadorPersonalizado = new AdaptadorComanda(
                    this, R.layout.layoutitem, productoLista);

            comandaBinding.listaComandaView.setAdapter(adaptadorPersonalizado);

            registerForContextMenu(comandaBinding.listaComandaView);



    }


    private void cargarListaGuardada() {
        if (productoLista == null) {
            sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            String json = sharedPreferences.getString("productList", "");
            if (!json.isEmpty()) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Producto>>() {
                }.getType();
                productoLista = gson.fromJson(json, type);
            } else {
                productoLista = new ArrayList<>();
            }
        }
    }
    private void guardarListaProducto() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(productoLista);
        editor.putString(KEY_PRODUCT_LIST, json);
        editor.apply();
        System.out.println("guardado");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESTCOMANDA) {
            // comprueba si el codigo del resultado es OK
            if (resultCode == RESULT_OK) {
                String mensaje = data.getStringExtra("mensaje");
                Toast.makeText(this, mensaje,
                        Toast.LENGTH_SHORT).show();
                productoLista.clear();
                if (!(productoLista == null)) {
                    productoLista.clear();
                }
                if (!(comidaLista == null)) {
                    comidaLista.clear();
                }
                if (!(bebidaLista == null)) {
                    bebidaLista.clear();
                }
                if (!(postreLista == null)) {
                    postreLista.clear();
                }
                AdaptadorComanda adaptadorPersonalizado = new AdaptadorComanda(
                        this, R.layout.layoutitem, productoLista);

                comandaBinding.listaComandaView.setAdapter(adaptadorPersonalizado);


            } else {

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucomanda, menu);
        return true;

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_context_producto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Producto p=new Producto();
        if(info.targetView==comandaBinding.listaComandaView) {
             p = productoLista.get(position);
            System.out.println(p.getNombreProducto());
        }
        switch (item.getItemId()) {
            case R.id.editar:
                // Acción para el elemento "Editar"
                return true;

            case R.id.eliminar:
                // Acción para el elemento "Eliminar"
                System.out.println("pasa por aqui");
                productoLista.remove(position);
                adaptadorPersonalizado = new AdaptadorComanda(
                        this, R.layout.layoutitem, productoLista);
                comandaBinding.listaComandaView.setAdapter(adaptadorPersonalizado);
                String texto=getString(R.string.accionEliminar);
                adaptadorPersonalizado.notifyDataSetChanged();
                Toast.makeText(this, texto + p.getNombreProducto(), Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


    /**
     * El boton de calculadora nos hara una llamada del sistema para que nos llevara a la aplicación calculadora
     */

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
                if (!productoLista.isEmpty()) {
                    Intent intentCobro = new Intent(this, Cobrar.class);
                    intentCobro.putExtra("mesa", textoMesa);
                    intentCobro.putExtra("lista", productoLista);
                    startActivityForResult(intentCobro, REQUESTCOMANDA);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {

            case R.id.btnComida:

                fragmentTransaction.replace(R.id.listaFragmentPrincipal, fragmentComida);
                fragmentTransaction.commit();
                comandaBinding.listaFragmentPrincipal.setVisibility(View.VISIBLE);
                comandaBinding.listaComandaView.setVisibility(View.GONE);
                break;

            case R.id.btnBebida:
                fragmentTransaction.replace(R.id.listaFragmentPrincipal, fragmentBebida);
                fragmentTransaction.commit();
                comandaBinding.listaFragmentPrincipal.setVisibility(View.VISIBLE);
                comandaBinding.listaComandaView.setVisibility(View.GONE);
                break;

            case R.id.btnPostre:

                fragmentTransaction.replace(R.id.listaFragmentPrincipal, fragmentPostre);
                fragmentTransaction.commit();
                comandaBinding.listaFragmentPrincipal.setVisibility(View.VISIBLE);
                comandaBinding.listaComandaView.setVisibility(View.GONE);
                break;

        }
    }



    public void setBebidaLista(List<Bebida> bebidaListaFragment, boolean aceptar) {
        bebidaLista = bebidaListaFragment;
        if (aceptar) {
            Iterator<Bebida> iterator = bebidaLista.iterator();
            while (iterator.hasNext()) {
                Bebida bebida = iterator.next();
                if (bebida.getCantidad() > 0) {
                    Producto producto = bebida;
                    boolean encontrado = false;

                    Iterator<Producto> productoIterator = productoLista.iterator();
                    while (productoIterator.hasNext()) {
                        Producto p = productoIterator.next();
                        if (p.getNombreProducto().equals(producto.getNombreProducto())) {
                            p.setCantidad(p.getCantidad() + producto.getCantidad());

                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        // Agregar el nuevo producto a productoLista
                        productoLista.add(producto);
                    }
                }
            }
        }

        comandaBinding.listaFragmentPrincipal.setVisibility(View.GONE);
        comandaBinding.listaComandaView.setVisibility(View.VISIBLE);

        AdaptadorComanda adaptadorPersonalizado = new AdaptadorComanda(
                this, R.layout.layoutitem, productoLista);

        comandaBinding.listaComandaView.setAdapter(adaptadorPersonalizado);
    }


    public void setPostreLista(List<Postre> postreListaFragment,boolean aceptar) {
        if (aceptar) {
            postreLista = postreListaFragment;

            Iterator<Postre> iterator = postreLista.iterator();
            while (iterator.hasNext()) {
                Postre postre = iterator.next();
                if (postre.getCantidad() > 0) {
                    Producto producto = postre;
                    System.out.println(postre.getCantidad());
                    boolean encontrado = false;

                    Iterator<Producto> productoIterator = productoLista.iterator();
                    while (productoIterator.hasNext()) {
                        Producto p = productoIterator.next();
                        if (p.getNombreProducto().equals(producto.getNombreProducto())) {
                            // Actualizar la cantidad del producto existente
                            p.setCantidad(p.getCantidad() + producto.getCantidad());

                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        // Agregar el nuevo producto a productoLista
                        productoLista.add(producto);
                    }
                }
            }

        }
            comandaBinding.listaFragmentPrincipal.setVisibility(View.GONE);
            comandaBinding.listaComandaView.setVisibility(View.VISIBLE);

            AdaptadorComanda adaptadorPersonalizado = new AdaptadorComanda(
                    this, R.layout.layoutitem, productoLista);

            comandaBinding.listaComandaView.setAdapter(adaptadorPersonalizado);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        guardarListaProducto();
    }


    public void setComidaLista(List<Comida> comidaListaFragment, boolean aceptar) {

        if (aceptar) {
            comidaLista = comidaListaFragment;

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

                            p.setCantidad(p.getCantidad() + producto.getCantidad());
                            encontrado = true;
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