package concesionaria.validation;

import concesionaria.model.PedidoCompra;
import concesionaria.util.DatosFacturacion;

public class ValidadorDatosFacturacion extends ValidadorPedido {
    @Override
    protected boolean validarEspecifico(PedidoCompra pedido) {
        DatosFacturacion facturacion = pedido.getDatosFacturacion();
        return facturacion != null &&
                facturacion.getNombreRazonSocial() != null && !facturacion.getNombreRazonSocial().trim().isEmpty() &&
                facturacion.getDireccion() != null && !facturacion.getDireccion().trim().isEmpty();
    }

    @Override
    protected String getMensajeError() {
        return "Datos de facturación incompletos: se requiere nombre/razón social y dirección";
    }
}
