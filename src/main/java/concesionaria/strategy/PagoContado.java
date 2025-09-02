package concesionaria.strategy;

public class PagoContado extends FormaPago {
    public PagoContado() {
        super("Contado");
    }

    @Override
    public void procesar(double monto) {
        System.out.println("Procesando pago en efectivo por $" + monto);
    }

    @Override
    public String getDescripcion() {
        return "Pago en efectivo";
    }
}

