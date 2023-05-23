package com.example.libretaelectronica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorComida;
import com.example.libretaelectronica.models.Comida;

import java.util.ArrayList;
import java.util.List;

public class ComidaListaFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ListView listaComidaView;
    List<Comida> comidaLista;

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
        comidaLista=new ArrayList<>();
        Comida c1=new Comida("comida1",2);
        Comida c2=new Comida("comida2", (float) 2.6);
        Comida c3=new Comida("comida3", (float) 5.1);
        Comida c4=new Comida("comida4", (float) 1.3);
        comidaLista.add(c1);
        comidaLista.add(c2);
        comidaLista.add(c3);
        comidaLista.add(c4);
        AdaptadorComida adaptadorComida = new AdaptadorComida(
                getActivity(), R.layout.layoutitem, comidaLista);

        listaComidaView.setAdapter(adaptadorComida);


        return view;
    }

    @Override
    public void onClick(View v) {

    }
}