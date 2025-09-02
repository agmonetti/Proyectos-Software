package concesionaria.validation;

import concesionaria.model.PedidoCompra;
import concesionaria.model.Vendedor;

public class ValidadorVendedor extends ValidadorPedido {
    @Override
    protected boolean validarEspecifico(PedidoCompra pedido) {
        Vendedor vendedor = pedido.getVendedor();
        return vendedor != null &&
                vendedor.getNombre() != null && !vendedor.getNombre().trim().isEmpty() &&
                vendedor.getEmail() != null && !vendedor.getEmail().trim().isEmpty();
    }

    @Override
    protected String getMensajeError() {
        return "Datos del vendedor incompletos: se requiere nombre y email válidos";
    }
}
