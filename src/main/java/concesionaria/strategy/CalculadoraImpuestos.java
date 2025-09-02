package concesionaria.strategy;

public interface CalculadoraImpuestos {
    double calcular(double precioBase);
    String getDetalle(double precioBase);
}

