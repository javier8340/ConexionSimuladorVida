package com.Javiergg.SimuladorVida.core;

import java.util.ArrayList;
import java.util.List;

public class Item {
    String tipo;
    private String nombre;
    private int coste;

    public Item(String tipo,String nombre, int coste){
        this.tipo = tipo;
        this.nombre = nombre;
        this.coste = coste;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return this.nombre +" - "+ this.tipo + " - " + this.coste;
    }
}
