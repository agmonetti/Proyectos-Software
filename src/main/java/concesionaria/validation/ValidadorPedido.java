package concesionaria.validation;

import concesionaria.model.PedidoCompra;
import concesionaria.exception.ProcessingException;

public abstract class ValidadorPedido {
    protected ValidadorPedido siguiente;

    public void setSiguiente(ValidadorPedido siguiente) {
        this.siguiente = siguiente;
    }

    public void validar(PedidoCompra pedido) throws ProcessingException {
        if (!validarEspecifico(pedido)) {
            throw new ProcessingException(getMensajeError());
        }

        if (siguiente != null) {
            siguiente.validar(pedido);
        }
    }

    protected abstract boolean validarEspecifico(PedidoCompra pedido);
    protected abstract String getMensajeError();
}
