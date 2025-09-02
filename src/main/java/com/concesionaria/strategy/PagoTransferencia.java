package com.concesionaria.strategy;

public class PagoTransferencia extends FormaPago {
    public PagoTransferencia() {
        super("Transferencia");
    }

    @Override
    public void procesar(double monto) {
        System.out.println("Procesando transferencia bancaria por $" + monto);
    }

    @Override
    public String getDescripcion() {
        return "Transferencia bancaria";
    }
}
