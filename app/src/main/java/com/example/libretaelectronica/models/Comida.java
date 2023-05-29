package com.example.libretaelectronica.models;

public class Comida extends Producto {

    String ingredientes;

    public Comida(String nombreProducto, float precioProducto) {
        super(nombreProducto, precioProducto);
    }

    public Comida(String nombreProducto, float precioProducto, String ingredientes) {
        super(nombreProducto, precioProducto);
        this.ingredientes = ingredientes;
    }

    public Comida() {

    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "ingredientes='" + ingredientes + '\'' +
                '}';
    }
}
