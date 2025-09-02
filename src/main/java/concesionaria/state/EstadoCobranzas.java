package concesionaria.state;

import concesionaria.model.PedidoCompra;

public class EstadoCobranzas implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Procesando en área de Cobranzas...");
        pedido.setEstado(new EstadoImpuestos());
        pedido.agregarHistorial("Procesado en Cobranzas");
    }

    @Override
    public String getNombre() { return "Cobranzas"; }
}

