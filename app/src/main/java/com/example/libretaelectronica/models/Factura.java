package com.example.libretaelectronica.models;

public class Factura {

    private String mesa;
    private float total;

    public Factura(String mesa, float total) {
        this.mesa = mesa;
        this.total = total;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
