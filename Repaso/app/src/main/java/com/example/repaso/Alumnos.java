package com.example.repaso;

import android.os.Parcelable;

public class Alumnos {

    private String nombre;
    private String curso;
    private String ciclo;
    private int imagen;


    public Alumnos(String nombre, String curso, String ciclo, int imagen) {
        this.nombre = nombre;
        this.curso = curso;
        this.ciclo = ciclo;
        this.imagen = imagen;
    }

    public Alumnos(String nombre, String curso, String ciclo) {
        this.nombre = nombre;
        this.curso = curso;
        this.ciclo = ciclo;
    }

    public Alumnos(String nombre, String curso, int imagen) {
        this.nombre = nombre;
        this.curso = curso;
        this.imagen = imagen;
    }

    public Alumnos() {
    }

    public Alumnos(String nombreAlumno) {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
