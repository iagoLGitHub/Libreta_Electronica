package com.example.libretaelectronica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
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
            Comida c1 = new Comida("comida1", 1);
            Comida c2 = new Comida("comida2", (float) 3);
            Comida c3 = new Comida("comida3", (float) 6.30);
            Comida c4 = new Comida("comida4", (float) 10.30);
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


        return view;
    }


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
   private int sumaCantidad(int cantidad){
        cantidad+=1;

        return cantidad;


   }
}