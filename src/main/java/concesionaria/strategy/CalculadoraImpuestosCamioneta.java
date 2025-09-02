package concesionaria.strategy;

public class CalculadoraImpuestosCamioneta implements CalculadoraImpuestos {
    @Override
    public double calcular(double precioBase) {
        double impuestoNacional = precioBase * 0.10; // 10%
        double impuestoProvincialGeneral = precioBase * 0.05; // 5%
        double impuestoProvincialAdicional = precioBase * 0.02; // 2%
        return impuestoNacional + impuestoProvincialGeneral + impuestoProvincialAdicional;
    }

    @Override
    public String getDetalle(double precioBase) {
        return String.format("Impuestos Camioneta: Nacional 10%% ($%.2f) + Provincial General 5%% ($%.2f) + Provincial Adicional 2%% ($%.2f)",
                precioBase * 0.10, precioBase * 0.05, precioBase * 0.02);
    }
}

