package com.concesionaria.strategy;

public class CalculadoraImpuestosCamion implements CalculadoraImpuestos {
    @Override
    public double calcular(double precioBase) {
        double impuestoProvincialGeneral = precioBase * 0.05; // 5%
        double impuestoProvincialAdicional = precioBase * 0.02; // 2%
        return impuestoProvincialGeneral + impuestoProvincialAdicional;
    }

    @Override
    public String getDetalle(double precioBase) {
        return String.format("Impuestos Camión: Provincial General 5%% ($%.2f) + Provincial Adicional 2%% ($%.2f)",
                precioBase * 0.05, precioBase * 0.02);
    }
}
