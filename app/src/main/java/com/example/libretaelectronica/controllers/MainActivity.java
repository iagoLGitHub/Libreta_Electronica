package com.example.libretaelectronica.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.view.View;

import com.example.libretaelectronica.R;
import com.example.libretaelectronica.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int CALL_PERMISSION_REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding activityMain = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMain.getRoot();
        setContentView(view);

        activityMain.btnComanda.setOnClickListener(this);
        activityMain.btnReserva.setOnClickListener(this);
        activityMain.btnAlmacen.setOnClickListener(this);
        activityMain.btnFacturas.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.btnComanda:

                i = new Intent(this, SeleccionarMesa.class);
                startActivity(i);
                break;

            case R.id.btnReserva:
                hacerLlamada();

                break;

            case R.id.btnAlmacen:
                eventoLocalizacion();

                break;

            case R.id.btnFacturas:
                i = new Intent(this, listaFacturas.class);
                startActivity(i);
                break;

        }

    }



    private void eventoLocalizacion(){
        double latitude = 42.251502;
        double longitude = -8.690087;
        String label = "Ies teis";
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + label + ")";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }


    }

    private void hacerLlamada() {
        String phoneNumber = "061";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            } else {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST_CODE);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                eventoLocalizacion();

            } else {

            }
        }
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                hacerLlamada();

            } else {

            }
        }

    }}