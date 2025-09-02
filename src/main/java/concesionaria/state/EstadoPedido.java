package concesionaria.state;

import concesionaria.model.PedidoCompra;

public interface EstadoPedido {
    void procesar(PedidoCompra pedido);
    String getNombre();
}

