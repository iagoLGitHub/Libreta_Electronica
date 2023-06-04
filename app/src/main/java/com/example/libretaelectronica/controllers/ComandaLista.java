package com.example.libretaelectronica.controllers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
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

public class ComandaLista extends AppCompatActivity implements View.OnClickListener {
    public final static int REQUESTCOMANDA = 100;
    public final static int REQUESTNOTIFICACION=101;
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
    }

    /**
     * Devuelve un onActivityResult que si  es ok,vacia todas las listas y notifica con un Toast al usuario que la factur
     * Que el cobro ha sido realizado con exito.
     * @param requestCode
     * @param resultCode
     * @param data
     */
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
        Producto p = new Producto();

        switch (item.getItemId()) {

            case R.id.editar:

                p = productoLista.get(position);
                productoLista.set(position, restarCantidad(p));
                if (p.getCantidad() == 0) {
                    productoLista.remove(position);
                }
                adaptadorPersonalizado = new AdaptadorComanda(
                        this, R.layout.layoutitem, productoLista);
                comandaBinding.listaComandaView.setAdapter(adaptadorPersonalizado);

                adaptadorPersonalizado.notifyDataSetChanged();
                return true;

            case R.id.eliminar:
                p=productoLista.get(position);
                productoLista.remove(position);
                adaptadorPersonalizado = new AdaptadorComanda(
                        this, R.layout.layoutitem, productoLista);
                comandaBinding.listaComandaView.setAdapter(adaptadorPersonalizado);
                String texto = getString(R.string.accionEliminar);
                adaptadorPersonalizado.notifyDataSetChanged();
                Toast.makeText(this, texto +" "+
                        p.getNombreProducto(), Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * Resta 1 en el atributo de cantidad de producto, si el producto esta a 0 no resta
     * @param p
     * @return
     */
    private Producto restarCantidad(Producto p) {
        int cantidad;
        if (p.getCantidad() > 0) {
            cantidad = p.getCantidad();
            int cantidadRestada = cantidad - 1;
            p.setCantidad(cantidadRestada);
        }
        return p;
    }

    /**
     * Menu que tiene dos opciones, uno llevara a la calculadora del sistema, y el otro enviara un
     * startActivityForResult a la clase Cobrar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuCalculadora:
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

    /**
     * Control de botones, cada uno ligado aun fragmento.class tambien se controlan con visibilidad
     * @param v
     */
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

            case R.id.btnAtras:
                onBackPressed();

                break;


        }
    }

    /**
     * Vuelve a la Activity anterior guardando la lista de productos seleccionados en un sharedPreference
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!productoLista.isEmpty()) {
            System.out.println("pasa por aqui");
            notificacionListaProducto();

        }
        guardarListaProducto();
    }

    /**
     * Metodo para mostrar la notificación, esta al clickar te llevara a la pantalla de comandalista,
     * contiene control de versiones, si es mayor o igual a la versión 26 creara un canal de notificación.
     */

    private void notificacionListaProducto() {
        Notification.Builder builder = new Notification.Builder(this);
        String aviso = getString(R.string.atencion);
        String mensaje = getString(R.string.mensajeNotificacion);
        builder.setSmallIcon(R.drawable.comanda);
        builder.setTicker(aviso);
        builder.setContentTitle(aviso);
        builder.setContentText(mensaje);
        Bitmap icono = BitmapFactory.decodeResource(getResources(), R.drawable.portada);
        builder.setLargeIcon(icono);
        Intent i = new Intent(this, ComandaLista.class);
        PendingIntent pi = PendingIntent.getActivity(this, REQUESTNOTIFICACION, i, 0);
        builder.setContentIntent(pi);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String canalId = "mi_canal_id";
            String nombreCanal = "canalNotificacionComanda";


            String description = getString(R.string.mensajeNotificacion);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(canalId, nombreCanal, importance);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);

            builder.setChannelId(canalId);

        }

        Notification notificacion=builder.build();
        nm.notify(REQUESTNOTIFICACION, notificacion);

    }


    /**
     * Metodo que controla la lista de bebidas enviada desde BebidaListFragment,y la introduce en
     * productoLista
     * @param bebidaListaFragment
     * @param aceptar
     */
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

    /**
     * * Metodo que controla la lista de postres enviada desde PostreListFragment,y la introduce en
     * productoLista
     * @param postreListaFragment
     * @param aceptar
     */
    public void setPostreLista(List<Postre> postreListaFragment, boolean aceptar) {
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
                            p.setCantidad(p.getCantidad() + producto.getCantidad());

                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
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

    /**
     * * Metodo que controla la lista de comidas enviada desde ComidaListFragment,y la introduce en
     * productoLista
     * @param comidaListaFragment
     * @param aceptar
     */
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
                            p.setCantidad(p.getCantidad() + producto.getCantidad());
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
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


}