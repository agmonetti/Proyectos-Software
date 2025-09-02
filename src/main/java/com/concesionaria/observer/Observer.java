package com.concesionaria.observer;

import com.concesionaria.model.PedidoCompra;

public interface Observer {
    void actualizar(String mensaje, PedidoCompra pedido);
}
