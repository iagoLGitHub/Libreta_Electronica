package com.example.libretaelectronica.models;

public class Postre {

    private String nombrePostre;
    private float precio;

    public Postre(String nombrePostre, float precio) {
        this.nombrePostre = nombrePostre;
        this.precio = precio;
    }

    public String getNombrePostre() {
        return nombrePostre;
    }

    public void setNombrePostre(String nombrePostre) {
        this.nombrePostre = nombrePostre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Postre{" +
                "nombrePostre='" + nombrePostre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
