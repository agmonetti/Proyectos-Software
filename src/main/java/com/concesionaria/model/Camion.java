package com.concesionaria.model;

public class Camion extends com.concesionaria.model.Vehiculo {
    public Camion(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
    }

    @Override
    public String getTipo() { return "Camion"; }
}
