package com.example.libretaelectronica.controllers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorPersonalizado;
import com.example.libretaelectronica.models.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComidaLista#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComidaLista extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ListView listaComida;
    List<Producto> productoLista;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComidaLista() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComidaLista.
     */
    // TODO: Rename and change types and number of parameters
    public static ComidaLista newInstance(String param1, String param2) {
        ComidaLista fragment = new ComidaLista();
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
        listaComida= view.findViewById(R.id.listaComida);
        System.out.println("pasa por aqui");
        productoLista=new ArrayList<>();
        Producto p1=new Producto("comida1",2);
        Producto p2=new Producto("comida2", (float) 2.6);
        Producto p3=new Producto("comida3", (float) 5.1);
        Producto p4=new Producto("comida4", (float) 1.3);
        productoLista.add(p1);
        productoLista.add(p2);
        productoLista.add(p3);
        productoLista.add(p4);
        AdaptadorPersonalizado adaptadorPersonalizado = new AdaptadorPersonalizado(
                getActivity(), R.layout.layoutitem, productoLista);

        listaComida.setAdapter(adaptadorPersonalizado);


        return view;
    }

    @Override
    public void onClick(View v) {

    }
}