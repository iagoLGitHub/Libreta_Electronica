package com.example.libretaelectronica.models;

public class Comida {

    private String nombreComida;
    private float precioComida;

    public Comida(String nombreComida, float precioComida) {
        this.nombreComida = nombreComida;
        this.precioComida = precioComida;
    }

    public String getNombreComida() {
        return nombreComida;
    }

    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }

    public float getPrecioComida() {
        return precioComida;
    }

    public void setPrecioComida(float precioComida) {
        this.precioComida = precioComida;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "nombreComida='" + nombreComida + '\'' +
                ", precioComida=" + precioComida +
                '}';
    }
}
