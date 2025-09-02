package concesionaria.model;

public class Moto extends Vehiculo {
    public Moto(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
    }

    @Override
    public String getTipo() { return "Moto"; }
}
