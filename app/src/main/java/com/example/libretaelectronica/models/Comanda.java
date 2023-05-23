package com.example.libretaelectronica.models;

import java.util.ArrayList;
import java.util.List;

public class Comanda {

    private List<Producto> listaProducto;

    public Comanda(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }
}
