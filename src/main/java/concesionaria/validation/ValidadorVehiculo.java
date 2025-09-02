package concesionaria.validation;

import concesionaria.model.PedidoCompra;
import concesionaria.model.Vehiculo;

public class ValidadorVehiculo extends ValidadorPedido {
    @Override
    protected boolean validarEspecifico(PedidoCompra pedido) {
        Vehiculo vehiculo = pedido.getVehiculo();
        return vehiculo != null &&
                vehiculo.getMarca() != null && !vehiculo.getMarca().trim().isEmpty() &&
                vehiculo.getModelo() != null && !vehiculo.getModelo().trim().isEmpty() &&
                vehiculo.getNumeroChasis() != null && !vehiculo.getNumeroChasis().trim().isEmpty() &&
                vehiculo.getNumeroMotor() != null && !vehiculo.getNumeroMotor().trim().isEmpty() &&
                vehiculo.getPrecioBase() > 0;
    }

    @Override
    protected String getMensajeError() {
        return "Datos del vehículo incompletos: se requiere marca, modelo, número de chasis, número de motor y precio válido";
    }
}
