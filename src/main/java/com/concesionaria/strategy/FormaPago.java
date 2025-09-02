package com.concesionaria.strategy;

public abstract class FormaPago {
    protected String tipo;

    public FormaPago(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() { return tipo; }

    public abstract void procesar(double monto);
    public abstract String getDescripcion();
}
