package concesionaria.validation;

import concesionaria.model.PedidoCompra;
import concesionaria.model.Cliente;
import concesionaria.exception.ProcessingException;

public class ValidadorCliente extends ValidadorPedido {
    @Override
    protected boolean validarEspecifico(PedidoCompra pedido) {
        Cliente cliente = pedido.getCliente();
        return cliente != null &&
                cliente.getNombre() != null && !cliente.getNombre().trim().isEmpty() &&
                cliente.getApellido() != null && !cliente.getApellido().trim().isEmpty() &&
                cliente.getDocumento() != null && !cliente.getDocumento().trim().isEmpty() &&
                cliente.getEmail() != null && !cliente.getEmail().trim().isEmpty();
    }

    @Override
    protected String getMensajeError() {
        return "Datos del cliente incompletos: se requiere nombre, apellido, documento y email";
    }
}
