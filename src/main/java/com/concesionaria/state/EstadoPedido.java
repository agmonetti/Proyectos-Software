package com.concesionaria.state;

import com.concesionaria.model.PedidoCompra;

public interface EstadoPedido {
    void procesar(PedidoCompra pedido);
    String getNombre();
}
