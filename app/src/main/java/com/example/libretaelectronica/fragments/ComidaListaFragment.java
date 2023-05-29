package com.example.libretaelectronica.fragments;

import android.app.AlertDialog;
import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorComida;
import com.example.libretaelectronica.controllers.ComandaLista;
import com.example.libretaelectronica.models.Bebida;
import com.example.libretaelectronica.models.Comida;
import com.example.libretaelectronica.models.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComidaListaFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ListView listaComidaView;
    List<Comida> comidaLista=new ArrayList<>();
    Button btnAceptar, btnCancelar;

    private String mParam1;
    private String mParam2;

    public ComidaListaFragment() {

    }
    public static ComidaListaFragment newInstance(String param1, String param2) {
        ComidaListaFragment fragment = new ComidaListaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comida_lista, container, false);

        listaComidaView = view.findViewById(R.id.listaComida);
        btnAceptar=view.findViewById(R.id.btnAceptarListaComida);
        btnCancelar=view.findViewById(R.id.btnCancelarListaComida);



        // Verificar si la lista de comida ya contiene elementos
        if (comidaLista.isEmpty()) {
            // La lista está vacía, crear y agregar elementos
            Comida c1 = new Comida("comida1", 1,"ingrediente1,ingrediente2,ingrediente3");
            Comida c2 = new Comida("comida2", (float) 3,"ingrediente 7,ingrediente1,ingrediente3");
            Comida c3 = new Comida("comida3", (float) 6.30,"ingrediente 7,ingrediente1,ingrediente2");
            Comida c4 = new Comida("comida4", (float) 10.30,"ingrediente 9,ingrediente11,ingrediente1");
            comidaLista.add(c1);
            comidaLista.add(c2);
            comidaLista.add(c3);
            comidaLista.add(c4);
        }

        AdaptadorComida adaptadorComida = new AdaptadorComida(
                getActivity(), R.layout.item_comida, comidaLista);
        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        listaComidaView.setAdapter(adaptadorComida);
        listaComidaView.setOnItemClickListener(this);
        registerForContextMenu(listaComidaView);

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        requireActivity().getMenuInflater().inflate(R.menu.menu_context_listacomida, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // Obtener el ContextMenuInfo
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // Obtener la posición del elemento seleccionado
        int position = info.position;

          Comida comidaSeleccionada = comidaLista.get(position);




        switch (item.getItemId()) {
            case R.id.item_ingrediente:
                // Acción cuando se selecciona "Ingredientes"
                String nombreComida=comidaSeleccionada.getNombreProducto();
                String ingredientes = comidaSeleccionada.getIngredientes();
                mensajeIngredientes(nombreComida,ingredientes);
                Toast.makeText(getActivity(), "Ingredientes de " + ingredientes, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * Escuchador de los botones Aceptar y Cancelar
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnAceptarListaComida:
                ((ComandaLista)getActivity()).setComidaLista(comidaLista);
                for(Comida comida:comidaLista){
                    System.out.println(comida.getCantidad());
                }
                getFragmentManager().beginTransaction().remove(this).commit();
                System.out.println("boton aceptar");
                break;
            case R.id.btnCancelarListaComida:
                System.out.println("boton cancelar");
                break;

        }
    }

    /**
     * Ventana Dialog que recibe el nombreComida e ingredientes de la comida seleccionada
     * @param nombreComida
     * @param ingredientes
     */
    public void mensajeIngredientes(String nombreComida,String ingredientes){

        AlertDialog.Builder ventana = new AlertDialog.Builder(getActivity());
        ventana.setTitle(nombreComida);
        ventana.setMessage(ingredientes);
        ventana.setIcon(R.drawable.comanda);
        ventana.show();
    }

    /**
     * Escuchador del itemclick de listaComida
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Comida comida;
        comida= comidaLista.get(position);
        int cantidad= comida.getCantidad();
        comida.setCantidad(sumaCantidad(cantidad));
        comidaLista.set(position,comida);

        /**Notifica al adaptador que los datos han cambiado y debe actualizarse*/
        ((AdaptadorComida) parent.getAdapter()).notifyDataSetChanged();




   }

    /**
     * Metodo que suma cuando se hace
     * @param cantidad
     * @return
     */
   private int sumaCantidad(int cantidad){
        cantidad+=1;

        return cantidad;


   }
}