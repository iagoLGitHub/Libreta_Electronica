package com.example.libretaelectronica.models;

public class Bebida {

    private String nombreBebida;
    private float precioBebida;

    public Bebida(String nombreBebida, float precioBebida) {
        this.nombreBebida = nombreBebida;
        this.precioBebida = precioBebida;
    }

    public String getNombreBebida() {
        return nombreBebida;
    }

    public void setNombreBebida(String nombreBebida) {
        this.nombreBebida = nombreBebida;
    }

    public float getPrecioBebida() {
        return precioBebida;
    }

    public void setPrecioBebida(float precioBebida) {
        this.precioBebida = precioBebida;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "nombreBebida='" + nombreBebida + '\'' +
                ", precioBebida=" + precioBebida +
                '}';
    }
}
