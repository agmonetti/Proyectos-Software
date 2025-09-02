package com.concesionaria.state;

import com.concesionaria.model.PedidoCompra;

public class EstadoEntrega implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Vehículo entregado al cliente");
        pedido.agregarHistorial("Vehículo entregado");
    }

    @Override
    public String getNombre() { return "Entrega"; }
}
