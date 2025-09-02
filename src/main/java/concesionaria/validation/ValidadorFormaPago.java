package concesionaria.validation;

import concesionaria.model.PedidoCompra;
import concesionaria.strategy.FormaPago;

public class ValidadorFormaPago extends ValidadorPedido {
    @Override
    protected boolean validarEspecifico(PedidoCompra pedido) {
        FormaPago formaPago = pedido.getFormaPago();
        return formaPago != null &&
                formaPago.getTipo() != null &&
                !formaPago.getTipo().trim().isEmpty();
    }

    @Override
    protected String getMensajeError() {
        return "Forma de pago no especificada o inválida";
    }
}
