package com.concesionaria.strategy;

public class PagoTarjeta extends FormaPago {
    public PagoTarjeta() {
        super("Tarjeta");
    }

    @Override
    public void procesar(double monto) {
        System.out.println("Procesando pago con tarjeta por $" + monto);
    }

    @Override
    public String getDescripcion() {
        return "Tarjeta de crédito";
    }
}
