package concesionaria.strategy;

public class CalculadoraImpuestosMoto implements CalculadoraImpuestos {
    @Override
    public double calcular(double precioBase) {
        double impuestoProvincialGeneral = precioBase * 0.05; // 5%
        double impuestoProvincialAdicional = precioBase * 0.01; // 1%
        return impuestoProvincialGeneral + impuestoProvincialAdicional;
    }

    @Override
    public String getDetalle(double precioBase) {
        return String.format("Impuestos Moto: Provincial General 5%% ($%.2f) + Provincial Adicional 1%% ($%.2f)",
                precioBase * 0.05, precioBase * 0.01);
    }
}

