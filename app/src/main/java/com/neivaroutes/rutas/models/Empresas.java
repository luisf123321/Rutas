package com.neivaroutes.rutas.models;

public class Empresas {

    String Nombre ;
    int foto;

    public Empresas(String nombre, int foto){
        this.Nombre = nombre;
        this.foto = foto;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
