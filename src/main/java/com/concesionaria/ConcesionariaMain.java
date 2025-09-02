package com.concesionaria;

import com.concesionaria.model.*;
import com.concesionaria.strategy.*;
import com.concesionaria.observer.*;
import com.concesionaria.util.*;

public class ConcesionariaMain {
    public static void main(String[] args) {
        // Crear cliente
        Cliente cliente = new Cliente("Juan", "Pérez", "12345678", "juan@email.com", "555-1234");

        // Crear vehículo
        Vehiculo vehiculo = new Auto("Toyota", "Corolla", "Blanco", "CH123456", "MT789012", 25000.0);

        // Configuraciones adicionales
        ConfiguracionAdicional config = new ConfiguracionAdicional("GPS", "Garantía extendida 3 años", "Alfombras", 2500.0);

        // Forma de pago
        FormaPago formaPago = new PagoContado();

        // Vendedor
        Vendedor vendedor = new Vendedor("María García", "maria@concesionaria.com");

        // Datos de facturación
        DatosFacturacion datosFacturacion = new DatosFacturacion("Juan Pérez", "Av. Principal 123", "20123456789");

        // Crear pedido
        PedidoCompra pedido = new PedidoCompra(cliente, vehiculo, config, formaPago, vendedor, datosFacturacion);

        // Agregar observadores
        pedido.agregarObserver(new NotificadorArea("VENTAS"));
        pedido.agregarObserver(new NotificadorArea("ADMINISTRACIÓN"));

        // Mostrar información del pedido
        System.out.println("=== PEDIDO CREADO ===");
        System.out.println(pedido);
        System.out.println("Estado actual: " + pedido.getAreaResponsableActual());
        System.out.println("Costo total: $" + pedido.getCostoTotal());

        // Procesar el pedido a través de todos los estados
        System.out.println("\n=== PROCESANDO PEDIDO ===");
        for (int i = 0; i < 6; i++) {
            pedido.procesar();
            System.out.println("Estado actual: " + pedido.getAreaResponsableActual());
        }

        // Mostrar historial
        System.out.println("\n=== HISTORIAL ===");
        for (String evento : pedido.getHistorial()) {
            System.out.println(evento);
        }
    }
}
