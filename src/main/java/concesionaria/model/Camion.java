package concesionaria.model;

public class Camion extends Vehiculo {
    public Camion(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
    }

    @Override
    public String getTipo() { return "Camion"; }
}
