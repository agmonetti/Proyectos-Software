package concesionaria.model;

public class Camioneta extends Vehiculo {
    public Camioneta(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
    }

    @Override
    public String getTipo() { return "Camioneta"; }
}
