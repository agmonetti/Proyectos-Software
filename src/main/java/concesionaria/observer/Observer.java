package concesionaria.observer;

import concesionaria.model.PedidoCompra;

public interface Observer {
    void actualizar(String mensaje, PedidoCompra pedido);
}

