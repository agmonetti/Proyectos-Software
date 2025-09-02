package concesionaria.state;

import concesionaria.model.PedidoCompra;

public class EstadoVentas implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Procesando en área de Ventas...");
        pedido.setEstado(new EstadoCobranzas());
        pedido.agregarHistorial("Procesado en Ventas");
    }

    @Override
    public String getNombre() { return "Ventas"; }
}

