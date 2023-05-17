package com.example.libretaelectronica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.libretaelectronica.databinding.ActivityListaComandaBinding;
import com.example.libretaelectronica.databinding.ActivityOpcionesComandaBinding;

public class ListaComanda extends AppCompatActivity implements View.OnClickListener {

    ActivityListaComandaBinding comandaBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comanda);

        /**creamos un nuevo objeto binding**/
        comandaBinding= ActivityListaComandaBinding.inflate(getLayoutInflater());
        View view=comandaBinding.getRoot();
        setContentView(view);
        /**Recogemos el intent enviado desde la clase seleccionarMesa*/
        String textoMesa=getIntent().getStringExtra("texto");
        comandaBinding.textoCabecera.setText(textoMesa);

        /**Asignamos los escuchadores*/
        comandaBinding.btnAtras.setOnClickListener(this);
        comandaBinding.btnComida.setOnClickListener(this);
        comandaBinding.btnBebida.setOnClickListener(this);
        comandaBinding.btnPostre.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}