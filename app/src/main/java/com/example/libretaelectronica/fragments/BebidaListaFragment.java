package com.example.libretaelectronica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorBebida;
import com.example.libretaelectronica.models.Bebida;

import java.util.ArrayList;
import java.util.List;

public class BebidaListaFragment extends Fragment {

    ListView listaBebidaView;
    List<Bebida> bebidaLista;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BebidaListaFragment() {

    }

    public static BebidaListaFragment newInstance(String param1, String param2) {
        BebidaListaFragment fragment = new BebidaListaFragment();
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
        View view = inflater.inflate(R.layout.fragment_bebida_lista, container, false);
        listaBebidaView = view.findViewById(R.id.listaBebida);
        bebidaLista=new ArrayList<>();
        Bebida b1=new Bebida("bebida1",2);
        Bebida b2=new Bebida("bebida2", (float) 2.6);
        Bebida b3=new Bebida("bebida3", (float) 5.1);
        Bebida b4=new Bebida("bebida4", (float) 1.3);
        bebidaLista.add(b1);
        bebidaLista.add(b2);
        bebidaLista.add(b3);
        bebidaLista.add(b4);
        AdaptadorBebida adaptadorBebida = new AdaptadorBebida(
                getActivity(), R.layout.layoutitem, bebidaLista);

        listaBebidaView.setAdapter(adaptadorBebida);


        return view;
    }
}