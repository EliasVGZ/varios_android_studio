package com.example.listados.clases;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

public class Planetas {

    private String nombre;
    private int imagen;

    public Planetas(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
