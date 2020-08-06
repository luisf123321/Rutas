package com.neivaroutes.rutas.models;

public class MRutas {
    String Nombre ;
    int foto;

    public MRutas(String nombre, int foto) {
        Nombre = nombre;
        this.foto = foto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
