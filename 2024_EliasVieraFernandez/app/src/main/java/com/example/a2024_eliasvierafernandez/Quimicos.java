package com.example.a2024_eliasvierafernandez;

public class Quimicos {

    private String compuesto;
    private String formula;
    private int imagen;

    public Quimicos(String compuesto, String formula, int imagen) {
        this.compuesto = compuesto;
        this.formula = formula;
        this.imagen = imagen;
    }

    public Quimicos(String compuesto, String formula) {
        this.compuesto = compuesto;
        this.formula = formula;
    }

    public Quimicos() {
    }

    public String getCompuesto() {
        return compuesto;
    }

    public void setCompuesto(String compuesto) {
        this.compuesto = compuesto;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
