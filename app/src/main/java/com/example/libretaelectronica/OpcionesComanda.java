package com.example.libretaelectronica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.libretaelectronica.databinding.ActivityOpcionesComandaBinding;

public class OpcionesComanda extends AppCompatActivity implements View.OnClickListener{

    ActivityOpcionesComandaBinding opcionesComanda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_comanda);

        /**creamos un nuevo objeto binding**/
        opcionesComanda=ActivityOpcionesComandaBinding.inflate(getLayoutInflater());
        View view=opcionesComanda.getRoot();
        setContentView(view);
        /**Recogemos el intent enviado desde la clase seleccionarMesa*/
        String textoMesa=getIntent().getStringExtra("texto");
        opcionesComanda.textoCabecera.setText(textoMesa);

        /**Asignamos los escuchadores*/
        opcionesComanda.btnAtras.setOnClickListener(this);
        opcionesComanda.btnComida.setOnClickListener(this);
        opcionesComanda.btnBebida.setOnClickListener(this);
        opcionesComanda.btnPostre.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

    }
}