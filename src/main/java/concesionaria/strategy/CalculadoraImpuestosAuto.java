package concesionaria.strategy;

public class CalculadoraImpuestosAuto implements CalculadoraImpuestos {
    @Override
    public double calcular(double precioBase) {
        double impuestoNacional = precioBase * 0.21; // 21%
        double impuestoProvincialGeneral = precioBase * 0.05; // 5%
        double impuestoProvincialAdicional = precioBase * 0.01; // 1%
        return impuestoNacional + impuestoProvincialGeneral + impuestoProvincialAdicional;
    }

    @Override
    public String getDetalle(double precioBase) {
        return String.format("Impuestos Auto: Nacional 21%% ($%.2f) + Provincial General 5%% ($%.2f) + Provincial Adicional 1%% ($%.2f)",
                precioBase * 0.21, precioBase * 0.05, precioBase * 0.01);
    }
}

