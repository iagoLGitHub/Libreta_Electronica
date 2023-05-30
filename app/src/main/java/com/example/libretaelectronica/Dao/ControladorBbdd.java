package com.example.libretaelectronica.Dao;

import android.content.Context;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ControladorBbdd extends SQLiteAssetHelper {

    private Context context;
    private static final String DATABASE_NAME = "agenda.db";


    public ControladorBbdd(Context context) {

        super(context, DATABASE_NAME, null,1);
        this.context=context;
    }



}
