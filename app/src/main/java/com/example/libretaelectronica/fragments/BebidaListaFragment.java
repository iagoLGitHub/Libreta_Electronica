package com.example.libretaelectronica.fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.libretaelectronica.Dao.ControladorBbdd;
import com.example.libretaelectronica.R;
import com.example.libretaelectronica.adapters.AdaptadorBebida;
import com.example.libretaelectronica.adapters.AdaptadorComida;
import com.example.libretaelectronica.controllers.ComandaLista;
import com.example.libretaelectronica.models.Bebida;
import com.example.libretaelectronica.models.Comida;
import com.example.libretaelectronica.models.Producto;
import java.util.ArrayList;
import java.util.List;
public class BebidaListaFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    private ListView listaBebidaView;
    private List<Bebida> bebidaLista=new ArrayList<>();
    private Button btnAceptar, btnCancelar;

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
        View view = inflater.inflate(R.layout.fragment_bebida_lista, container, false);

        listaBebidaView = view.findViewById(R.id.listaBebida);
        btnAceptar = view.findViewById(R.id.btnAceptarListaBebida);
        btnCancelar = view.findViewById(R.id.btnCancelarListaBebida);
        if(bebidaLista.isEmpty()){
            busquedaBebida();
        }

        AdaptadorBebida adaptadorBebida = new AdaptadorBebida(getActivity(), R.layout.item_bebida, bebidaLista);
        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        listaBebidaView.setAdapter(adaptadorBebida);
        listaBebidaView.setOnItemClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAceptarListaBebida:

            ((ComandaLista)getActivity()).setBebidaLista(bebidaLista);
            // Finalizar el fragment actual
                getFragmentManager().beginTransaction().remove(this).commit();
            break;
            case R.id.btnCancelarListaBebida:
                getFragmentManager().beginTransaction().remove(this).commit();
                ((ComandaLista)getActivity()).setBebidaLista(bebidaLista);
                System.out.println("boton cancelar");
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bebida bebida;
        bebida= (Bebida) parent.getItemAtPosition(position);
        int cantidad=bebida.getCantidad();
        bebida.setCantidad(sumaCantidad(cantidad));
        bebidaLista.set(position,bebida);
        /**Notifica al adaptador que los datos han cambiado y debe actualizarse*/
        ((AdaptadorBebida) parent.getAdapter()).notifyDataSetChanged();
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

    private void busquedaBebida(){
        ControladorBbdd bbdd = new ControladorBbdd(getActivity());
        SQLiteDatabase db = bbdd.getReadableDatabase();

        System.out.println(db.isOpen());

        String busqueda = "select * from 'bebidas'";
        System.out.println(busqueda);
        Cursor c=db.rawQuery(busqueda,null);

        if (c.moveToFirst()) {
            do {
                Bebida bebida = new Bebida(c.getString(0), c.getFloat(1));
                bebidaLista.add(bebida);
            } while (c.moveToNext());
        } else {

        }

        c.close();
    }
}

