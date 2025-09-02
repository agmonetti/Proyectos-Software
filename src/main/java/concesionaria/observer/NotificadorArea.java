package concesionaria.observer;

import concesionaria.model.PedidoCompra;

public class NotificadorArea implements Observer {
    private String area;

    public NotificadorArea(String area) {
        this.area = area;
    }

    @Override
    public void actualizar(String mensaje, PedidoCompra pedido) {
        System.out.println("[NOTIFICACIÓN " + area + "] " + mensaje + " - Pedido #" + pedido.getNumeroPedido());
    }
}

