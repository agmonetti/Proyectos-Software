package com.concesionaria.model;

public class Moto extends com.concesionaria.model.Vehiculo {
    public Moto(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
    }

    @Override
    public String getTipo() { return "Moto"; }
}
