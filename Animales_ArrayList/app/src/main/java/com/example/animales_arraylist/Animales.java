package com.example.animales_arraylist;

public class Animales {

    private String nombreAnimales;
    private int imagen_Animales;
    private String info;
    private int colores_Animales;

    public Animales(String nombreAnimales, int imagen_Animales, String info, int colores_Animales) {
        this.nombreAnimales = nombreAnimales;
        this.imagen_Animales = imagen_Animales;
        this.info = info;
        this.colores_Animales = colores_Animales;
    }


    public String getNombreAnimales() {
        return nombreAnimales;
    }

    public void setNombreAnimales(String nombreAnimales) {
        this.nombreAnimales = nombreAnimales;
    }

    public int getImagen_Animales() {
        return imagen_Animales;
    }

    public void setImagen_Animales(int imagen_Animales) {
        this.imagen_Animales = imagen_Animales;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getColores_Animales() {
        return colores_Animales;
    }

    public void setColores_Animales(int colores_Animales) {
        this.colores_Animales = colores_Animales;
    }
}
