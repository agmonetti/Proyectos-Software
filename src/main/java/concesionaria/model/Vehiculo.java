package concesionaria.model;

public abstract class Vehiculo {
    protected String marca;
    protected String modelo;
    protected String color;
    protected String numeroChasis;
    protected String numeroMotor;
    protected double precioBase;

    public Vehiculo(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.precioBase = precioBase;
    }

    // Getters
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getColor() { return color; }
    public String getNumeroChasis() { return numeroChasis; }
    public String getNumeroMotor() { return numeroMotor; }
    public double getPrecioBase() { return precioBase; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + color + ") - $" + precioBase;
    }
}
