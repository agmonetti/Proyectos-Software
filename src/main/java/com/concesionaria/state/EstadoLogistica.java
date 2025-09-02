package com.concesionaria.state;

import com.concesionaria.model.PedidoCompra;

public class EstadoLogistica implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Procesando en área de Logística...");
        pedido.setEstado(new EstadoEntrega());
        pedido.agregarHistorial("Procesado en Logística");
    }

    @Override
    public String getNombre() { return "Logística"; }
}
