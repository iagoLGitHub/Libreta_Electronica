package com.example.libretaelectronica.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.controllers.ListaComanda;
import com.example.libretaelectronica.databinding.ActivitySeleccionarMesaBinding;

public class SeleccionarMesa extends AppCompatActivity implements View.OnClickListener {
    public final static int MESABTN1=1;
    public final static int MESABTN2=2;
    public final static int MESABTN3=3;
    public final static int MESABTN4=4;
    public final static int MESABTN5=5;
    public final static int MESABTN6=6;
    public final static int MESABTN7=7;
    public final static int MESABTN8=8;
    public final static int MESABTN9=9;
    public final static int MESABTN10=10;
    public final static int MESABTN11=11;
    public final static int MESABTN12=12;


    Button btnMesa, btnBarra;
    ActivitySeleccionarMesaBinding seleccionarMesa;
    Button btnMesa1,btnMesa2,btnMesa3,btnMesa4,btnMesa5,btnMesa6,
    btnMesa7,btnMesa8,btnMesa9,btnMesa10,btnMesa11,btnMesa12;

    Boolean btnstateMesa1,btnstateMesa2,btnstateMesa3,btnstateMesa4,
            btnstateMesa5,btnstateMesa6,btnstateMesa7,btnstateMesa8,
            btnstateMesa9,btnstateMesa10,btnstateMesa11,btnstateMesa12;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_mesa);
        /**Uso del Binding**/
        seleccionarMesa= ActivitySeleccionarMesaBinding.inflate(getLayoutInflater());
        View view=seleccionarMesa.getRoot();
        setContentView(view);


        /**Iniciamos todos los botones en true(mesa libre)**/
        btnstateMesa1=true;btnstateMesa2=true;btnstateMesa3=true;btnstateMesa4=true;
        btnstateMesa5=true;btnstateMesa6=true;btnstateMesa7=true;btnstateMesa8=true;
        btnstateMesa9=true;btnstateMesa10=true;btnstateMesa11=true;btnstateMesa12=true;

        seleccionarMesa.btnMesa1.setOnClickListener(this);
        seleccionarMesa.btnMesa2.setOnClickListener(this);
        seleccionarMesa.btnMesa3.setOnClickListener(this);
        seleccionarMesa.btnMesa4.setOnClickListener(this);
        seleccionarMesa.btnMesa5.setOnClickListener(this);
        seleccionarMesa.btnMesa6.setOnClickListener(this);
        seleccionarMesa.btnMesa7.setOnClickListener(this);
        seleccionarMesa.btnMesa8.setOnClickListener(this);
        seleccionarMesa.btnMesa9.setOnClickListener(this);
        seleccionarMesa.btnMesa10.setOnClickListener(this);
        seleccionarMesa.btnMesa11.setOnClickListener(this);
        seleccionarMesa.btnMesa12.setOnClickListener(this);
        controlColorBtn();


    }


    @Override
    public void onClick(View v) {

        Intent i=new Intent(this, ListaComanda.class);
        String texto;
        switch (v.getId()){

            case R.id.btnMesa1:
                texto="Mesa 1";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN1);
                break;

            case R.id.btnMesa2:
                texto="Mesa 2";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN2);
                break;

            case R.id.btnMesa3:
                texto="Mesa 3";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN3);
                break;
            case R.id.btnMesa4:
                texto="Mesa 4";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN4);;
                break;

            case R.id.btnMesa5:
                texto="Mesa 5";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN5);
                break;
            case R.id.btnMesa6:
                texto="Mesa 6";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN6);
                break;
            case R.id.btnMesa7:
                texto="Mesa 7";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN7);
                break;
            case R.id.btnMesa8:
                texto="Mesa 8";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN8);
                break;
            case R.id.btnMesa9:
                texto="Mesa 9";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN9);
                break;
            case R.id.btnMesa10:
                texto="Mesa 10";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN10);
                break;
            case R.id.btnMesa11:
                texto="Mesa 11";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN11);
                break;
            case R.id.btnMesa12:
                texto="Mesa 12";
                i.putExtra("texto",texto);
                startActivityForResult(i,MESABTN12);
                break;

        }

    }

    /**
     * control del color en los botones, si es verde la mesa esta libre(true) sino es roja(false)
     */
    private void controlColorBtn(){

        if(btnstateMesa1){

            seleccionarMesa.btnMesa1.setBackgroundColor(Color.GREEN);
        }else{
            seleccionarMesa.btnMesa1.setBackgroundColor(Color.RED);
        }
        if(btnstateMesa2){
            seleccionarMesa.btnMesa2.setBackgroundColor(Color.GREEN);
        }else{
            seleccionarMesa.btnMesa2.setBackgroundColor(Color.RED);
        }
        if(btnstateMesa3){
            seleccionarMesa.btnMesa3.setBackgroundColor(Color.GREEN);
        }else{
            btnMesa3.setBackgroundColor(Color.RED);
        }
        if(btnstateMesa4){
            seleccionarMesa.btnMesa4.setBackgroundColor(Color.GREEN);
        }else{
            btnMesa4.setBackgroundColor(Color.RED);
        }if(btnstateMesa5){
            seleccionarMesa.btnMesa5.setBackgroundColor(Color.GREEN);
        }else{
            seleccionarMesa.btnMesa5.setBackgroundColor(Color.RED);
        }
        if(btnstateMesa6){
            seleccionarMesa.btnMesa6.setBackgroundColor(Color.GREEN);
        }else{
            seleccionarMesa.btnMesa6.setBackgroundColor(Color.RED);
        }
        if(btnstateMesa7){
            seleccionarMesa.btnMesa7.setBackgroundColor(Color.GREEN);
        }else{
            seleccionarMesa.btnMesa7.setBackgroundColor(Color.RED);
        }
        if(btnstateMesa8){
            seleccionarMesa.btnMesa8.setBackgroundColor(Color.GREEN);
        }else{
            seleccionarMesa.btnMesa8.setBackgroundColor(Color.RED);
        }
        if(btnstateMesa9){
            seleccionarMesa.btnMesa9.setBackgroundColor(Color.GREEN);
        }else{
            seleccionarMesa.btnMesa9.setBackgroundColor(Color.RED);

        }
        if(btnstateMesa10){
            seleccionarMesa.btnMesa10.setBackgroundColor(Color.GREEN);

        }else{
            seleccionarMesa.btnMesa10.setBackgroundColor(Color.RED);
        }
        if(btnstateMesa11){
            seleccionarMesa.btnMesa11.setBackgroundColor(Color.GREEN);
        }else{
            seleccionarMesa.btnMesa11.setBackgroundColor(Color.RED);
        }
        if(btnstateMesa12){
            seleccionarMesa.btnMesa12.setBackgroundColor(Color.GREEN);
        }else{
            seleccionarMesa.btnMesa12.setBackgroundColor(Color.RED);
        }


    }
}