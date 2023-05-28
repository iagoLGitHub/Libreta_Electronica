package com.example.libretaelectronica.models;

public class Comida extends Producto {
    public Comida(String nombreProducto, float precioProducto) {
        super(nombreProducto, precioProducto);
    }


    public Comida() {

    }

    @Override
    public String toString() {
        return "Comida{}";
    }
}
