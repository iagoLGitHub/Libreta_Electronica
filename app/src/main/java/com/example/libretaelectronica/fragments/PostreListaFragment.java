package com.example.libretaelectronica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorBebida;
import com.example.libretaelectronica.adapters.AdaptadorPostre;
import com.example.libretaelectronica.controllers.ComandaLista;
import com.example.libretaelectronica.models.Bebida;
import com.example.libretaelectronica.models.Postre;

import java.util.ArrayList;
import java.util.List;

public class PostreListaFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    ListView listaPostreView;
    private ArrayList<Postre> postreLista=new ArrayList<>();
    private Button btnAceptar, btnCancelar;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PostreListaFragment() {

    }

    public static PostreListaFragment newInstance(String param1, String param2) {
        PostreListaFragment fragment = new PostreListaFragment();
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
        View view = inflater.inflate(R.layout.fragment_postre_lista, container, false);

        listaPostreView = view.findViewById(R.id.listaPostre);
        btnAceptar = view.findViewById(R.id.btnAceptarListaPostre);
        btnCancelar = view.findViewById(R.id.btnAceptarListaPostre);

        if(postreLista.isEmpty()){
            postreLista=new ArrayList<>();
            Postre p1=new Postre("Postre1",2);
            Postre p2=new Postre("Postre2",(float) 2.6);
            Postre p3=new Postre("Postre3",(float) 5.1);
            Postre p4=new Postre("Postre4",(float) 1.3);

            postreLista.add(p1);
            postreLista.add(p2);
            postreLista.add(p3);
            postreLista.add(p4);

        }


        AdaptadorPostre adaptadorPostre = new AdaptadorPostre(
                getActivity(), R.layout.item_postre, postreLista);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        listaPostreView.setAdapter(adaptadorPostre);
        listaPostreView.setOnItemClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAceptarListaPostre:

                ((ComandaLista)getActivity()).setPostreLista(postreLista);
                // Finalizar el fragment actual
                getFragmentManager().beginTransaction().remove(this).commit();
                break;
            case R.id.btnCancelarListaBebida:
                System.out.println("boton cancelar");
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println();
        Postre postre;
        postre= (Postre) parent.getItemAtPosition(position);
        int cantidad=postre.getCantidad();
        postre.setCantidad(sumaCantidad(cantidad));
        postreLista.set(position,postre);
        /**Notifica al adaptador que los datos han cambiado y debe actualizarse*/
        ((AdaptadorPostre) parent.getAdapter()).notifyDataSetChanged();
    }

    /**
     * Metodo que suma la cantidad de un objeto comida
     * @param cantidad
     * @return
     */
    private int sumaCantidad(int cantidad){
        cantidad+=1;

        return cantidad;


    }
}