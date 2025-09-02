package concesionaria.model;

public class Auto extends Vehiculo {
    public Auto(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
    }

    @Override
    public String getTipo() { return "Auto"; }
}
