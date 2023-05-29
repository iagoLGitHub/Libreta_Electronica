package com.example.libretaelectronica.models;

import java.io.Serializable;

public class Producto implements Serializable {

    private String nombreProducto;
    private float precioProducto;
    private int cantidadProducto;

    public Producto() {

    }




    public Producto(String nombreProducto, float precioProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantidadProducto=0;
    }

    public Producto(String nombreProducto, float precioProducto, int cantidad) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidad;
    }

    public int getCantidad() {
        return cantidadProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidadProducto = cantidad;
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

