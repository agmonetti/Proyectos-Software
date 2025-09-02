package concesionaria.menu;

import concesionaria.facade.ConcesionariaFacade;
import concesionaria.model.*;
import concesionaria.exception.*;
import java.math.BigDecimal;

public class TestConcesionaria {
    private ConcesionariaFacade concesionaria;

    public TestConcesionaria() {
        this.concesionaria = new ConcesionariaFacade();
    }

    public void ejecutarTodasLasPruebas() {
        System.out.println("\n=== INICIANDO PRUEBAS DEL SISTEMA ===");
        
        int pruebasExitosas = 0;
        int totalPruebas = 0;
        
        totalPruebas++;
        if (probarCreacionPedidoValido()) {
            pruebasExitosas++;
            System.out.println("✓ Prueba creación pedido válido: EXITOSA");
        } else {
            System.out.println("✗ Prueba creación pedido válido: FALLIDA");
        }
        
        totalPruebas++;
        if (probarValidacionCliente()) {
            pruebasExitosas++;
            System.out.println("✓ Prueba validación cliente: EXITOSA");
        } else {
            System.out.println("✗ Prueba validación cliente: FALLIDA");
        }
        
        totalPruebas++;
        if (probarValidacionVehiculo()) {
            pruebasExitosas++;
            System.out.println("✓ Prueba validación vehículo: EXITOSA");
        } else {
            System.out.println("✗ Prueba validación vehículo: FALLIDA");
        }
        
        totalPruebas++;
        if (probarPatronStrategy()) {
            pruebasExitosas++;
            System.out.println("✓ Prueba patrón Strategy (formas de pago): EXITOSA");
        } else {
            System.out.println("✗ Prueba patrón Strategy (formas de pago): FALLIDA");
        }
        
        totalPruebas++;
        if (probarPatronState()) {
            pruebasExitosas++;
            System.out.println("✓ Prueba patrón State (estados pedido): EXITOSA");
        } else {
            System.out.println("✗ Prueba patrón State (estados pedido): FALLIDA");
        }
        
        totalPruebas++;
        if (probarPatronObserver()) {
            pruebasExitosas++;
            System.out.println("✓ Prueba patrón Observer (notificaciones): EXITOSA");
        } else {
            System.out.println("✗ Prueba patrón Observer (notificaciones): FALLIDA");
        }
        
        totalPruebas++;
        if (probarExcepciones()) {
            pruebasExitosas++;
            System.out.println("✓ Prueba manejo de excepciones: EXITOSA");
        } else {
            System.out.println("✗ Prueba manejo de excepciones: FALLIDA");
        }

        totalPruebas++;
        if (probarBusquedas()) {
            pruebasExitosas++;
            System.out.println("✓ Prueba búsquedas: EXITOSA");
        } else {
            System.out.println("✗ Prueba búsquedas: FALLIDA");
        }
        
        System.out.println("\n=== RESUMEN DE PRUEBAS ===");
        System.out.println("Pruebas exitosas: " + pruebasExitosas + "/" + totalPruebas);
        System.out.println("Porcentaje de éxito: " + (pruebasExitosas * 100 / totalPruebas) + "%");
        
        if (pruebasExitosas == totalPruebas) {
            System.out.println("🎉 ¡TODAS LAS PRUEBAS EXITOSAS! El sistema funciona correctamente.");
        } else {
            System.out.println("⚠️ Algunas pruebas fallaron. Revisar implementación.");
        }
    }

    private boolean probarCreacionPedidoValido() {
        try {
            int pedidosIniciales = concesionaria.getPedidos().size();
            concesionaria.crearPedidoCompleto(1, 1, "contado", 1, "Juan Pérez", "Av. Corrientes 1234");
            int pedidosFinales = concesionaria.getPedidos().size();
            return pedidosFinales == pedidosIniciales + 1;
        } catch (Exception e) {
            System.out.println("Error en prueba creación pedido: " + e.getMessage());
            return false;
        }
    }

    private boolean probarValidacionCliente() {
        try {
            // Intentar crear pedido con cliente inexistente
            concesionaria.crearPedidoCompleto(999, 1, "contado", 1, "Test", "Test Address");
            return false; // Si no lanza excepción, la validación falló
        } catch (ProcessingException e) {
            return e.getMessage().contains("Cliente no encontrado");
        } catch (Exception e) {
            return false;
        }
    }

    private boolean probarValidacionVehiculo() {
        try {
            // Intentar crear pedido con vehículo inexistente
            concesionaria.crearPedidoCompleto(1, 999, "contado", 1, "Test", "Test Address");
            return false; // Si no lanza excepción, la validación falló
        } catch (ProcessingException e) {
            return e.getMessage().contains("Vehículo no encontrado");
        } catch (Exception e) {
            return false;
        }
    }

    private boolean probarPatronStrategy() {
        try {
            // Crear pedidos con diferentes formas de pago
            concesionaria.crearPedidoCompleto(1, 1, "contado", 1, "Test Contado", "Address 1");
            concesionaria.crearPedidoCompleto(2, 1, "tarjeta", 2, "Test Tarjeta", "Address 2");
            concesionaria.crearPedidoCompleto(3, 1, "transferencia", 1, "Test Transferencia", "Address 3");
            return true; // Si no lanza excepción, el patrón funciona
        } catch (Exception e) {
            System.out.println("Error en prueba Strategy: " + e.getMessage());
            return false;
        }
    }

    private boolean probarPatronState() {
        try {
            // Procesar un pedido para probar cambios de estado
            concesionaria.procesarPedido(1);
            return true; // Si no lanza excepción, el patrón funciona
        } catch (Exception e) {
            System.out.println("Error en prueba State: " + e.getMessage());
            return false;
        }
    }

    private boolean probarPatronObserver() {
        try {
            // El patrón Observer se prueba indirectamente al procesar pedidos
            // Las notificaciones se muestran en consola
            concesionaria.procesarPedido(2);
            return true; // Si no lanza excepción, el patrón funciona
        } catch (Exception e) {
            System.out.println("Error en prueba Observer: " + e.getMessage());
            return false;
        }
    }

    private boolean probarExcepciones() {
        try {
            // Intentar agregar cliente duplicado
            Cliente clienteDuplicado = new Cliente("Cliente Duplicado", "Particular", "1", "Dirección Test", "");
            concesionaria.agregarCliente(clienteDuplicado);
            return false; // Si no lanza excepción, el manejo de excepciones falló
        } catch (DuplicateException e) {
            return true; // Se lanzó la excepción correcta
        } catch (Exception e) {
            return false;
        }
    }

    private boolean probarBusquedas() {
        try {
            // Probar búsquedas existentes y no existentes
            Cliente cliente = concesionaria.buscarClientePorId(1);
            if (cliente == null) return false;
            
            Vehiculo vehiculo = concesionaria.buscarVehiculoPorId(1);
            if (vehiculo == null) return false;
            
            Vendedor vendedor = concesionaria.buscarVendedorPorId(1);
            if (vendedor == null) return false;
            
            // Buscar elementos inexistentes
            Cliente clienteInexistente = concesionaria.buscarClientePorId(999);
            if (clienteInexistente != null) return false;
            
            return true;
        } catch (Exception e) {
            System.out.println("Error en prueba búsquedas: " + e.getMessage());
            return false;
        }
    }

    public void mostrarReporteSistema() {
        System.out.println("\n=== REPORTE DEL SISTEMA ===");
        
        System.out.println("\n--- Datos del sistema ---");
        concesionaria.mostrarEstadisticas();
        
        System.out.println("\n--- Clientes registrados ---");
        for (Cliente cliente : concesionaria.getClientes()) {
            System.out.println("- " + cliente.getNombre() + " (" + cliente.getTipo() + ")");
        }
        
        System.out.println("\n--- Vehículos disponibles ---");
        for (Vehiculo vehiculo : concesionaria.getVehiculos()) {
            System.out.println("- " + vehiculo.getMarca() + " " + vehiculo.getModelo() + 
                             " - $" + vehiculo.getPrecio());
        }
        
        System.out.println("\n--- Vendedores activos ---");
        for (Vendedor vendedor : concesionaria.getVendedores()) {
            System.out.println("- " + vendedor.getNombre() + " (" + vendedor.getArea() + ")");
        }
        
        System.out.println("\n--- Pedidos procesados ---");
        for (PedidoCompra pedido : concesionaria.getPedidos()) {
            System.out.println("- Pedido para " + pedido.getCliente().getNombre() + 
                             " - " + pedido.getVehiculo().getMarca() + " " + pedido.getVehiculo().getModelo());
        }
    }
}
