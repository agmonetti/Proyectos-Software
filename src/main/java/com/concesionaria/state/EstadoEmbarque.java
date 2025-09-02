package com.concesionaria.state;

import com.concesionaria.model.PedidoCompra;

public class EstadoEmbarque implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Procesando en área de Embarque...");
        pedido.setEstado(new EstadoLogistica());
        pedido.agregarHistorial("Procesado en Embarque");
    }

    @Override
    public String getNombre() { return "Embarque"; }
}
