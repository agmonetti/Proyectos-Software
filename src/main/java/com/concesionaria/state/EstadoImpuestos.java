package com.concesionaria.state;

import com.concesionaria.model.PedidoCompra;

public class EstadoImpuestos implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Procesando en área de Impuestos...");
        pedido.setEstado(new EstadoEmbarque());
        pedido.agregarHistorial("Procesado en Impuestos");
    }

    @Override
    public String getNombre() { return "Impuestos"; }
}
