package com.example.libretaelectronica.models;

public class Producto {

    private String nombreProducto;
    private float precioProducto;

    public Producto(String nombreProducto, float precioProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }
}
