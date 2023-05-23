package com.example.libretaelectronica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorBebida;
import com.example.libretaelectronica.adapters.AdaptadorPostre;
import com.example.libretaelectronica.models.Bebida;
import com.example.libretaelectronica.models.Postre;

import java.util.ArrayList;
import java.util.List;

public class PostreListaFragment extends Fragment {

    ListView listaPostreView;
    List<Postre> postreLista;
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
        postreLista=new ArrayList<>();


        postreLista=new ArrayList<>();
        Postre p1=new Postre("Postre1",2);
        Postre p2=new Postre("Postre2",(float) 2.6);
        Postre p3=new Postre("Postre3",(float) 5.1);
        Postre p4=new Postre("Postre4",(float) 1.3);

        postreLista.add(p1);
        postreLista.add(p2);
        postreLista.add(p3);
        postreLista.add(p4);
        AdaptadorPostre adaptadorPostre = new AdaptadorPostre(
                getActivity(), R.layout.layoutitem, postreLista);

        listaPostreView.setAdapter(adaptadorPostre);


        return view;
    }
}