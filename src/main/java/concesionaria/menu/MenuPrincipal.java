package concesionaria.menu;

import concesionaria.facade.ConcesionariaFacade;
import concesionaria.model.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class MenuPrincipal {
    private ConcesionariaFacade concesionaria;
    private Scanner scanner;

    public MenuPrincipal() {
        this.concesionaria = new ConcesionariaFacade();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== BIENVENIDO A LA CONCESIONARIA AUTOMAX ===");
        
        boolean continuar = true;
        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    ejecutarDemostracionCompleta();
                    break;
                case 2:
                    menuGestionClientes();
                    break;
                case 3:
                    menuGestionVehiculos();
                    break;
                case 4:
                    menuGestionVendedores();
                    break;
                case 5:
                    menuGestionPedidos();
                    break;
                case 6:
                    concesionaria.mostrarEstadisticas();
                    break;
                case 7:
                    ejecutarPruebas();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("¡Gracias por usar nuestro sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        }
        
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Demostración completa del sistema");
        System.out.println("2. Gestión de clientes");
        System.out.println("3. Gestión de vehículos");
        System.out.println("4. Gestión de vendedores");
        System.out.println("5. Gestión de pedidos");
        System.out.println("6. Ver estadísticas");
        System.out.println("7. Ejecutar pruebas del sistema");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void ejecutarDemostracionCompleta() {
        System.out.println("\n=== DEMOSTRACIÓN COMPLETA DEL SISTEMA ===");
        
        try {
            // Mostrar datos iniciales
            System.out.println("\n--- Datos iniciales ---");
            mostrarClientes();
            mostrarVehiculos();
            mostrarVendedores();
            
            // Crear un pedido de ejemplo
            System.out.println("\n--- Creando pedido de ejemplo ---");
            concesionaria.crearPedidoCompleto(1, 1, "contado", 1, "Juan Pérez", "Av. Corrientes 1234");
            
            // Procesar el pedido
            System.out.println("\n--- Procesando pedido ---");
            concesionaria.procesarPedido(1);
            
            // Mostrar estadísticas finales
            System.out.println("\n--- Estadísticas finales ---");
            concesionaria.mostrarEstadisticas();
            
        } catch (Exception e) {
            System.out.println("Error en la demostración: " + e.getMessage());
        }
    }

    private void menuGestionClientes() {
        System.out.println("\n=== GESTIÓN DE CLIENTES ===");
        System.out.println("1. Ver todos los clientes");
        System.out.println("2. Agregar nuevo cliente");
        System.out.println("3. Buscar cliente por ID");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        
        int opcion = leerOpcion();
        switch (opcion) {
            case 1:
                mostrarClientes();
                break;
            case 2:
                agregarCliente();
                break;
            case 3:
                buscarCliente();
                break;
        }
    }

    private void menuGestionVehiculos() {
        System.out.println("\n=== GESTIÓN DE VEHÍCULOS ===");
        System.out.println("1. Ver todos los vehículos");
        System.out.println("2. Agregar nuevo vehículo");
        System.out.println("3. Buscar vehículo por ID");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        
        int opcion = leerOpcion();
        switch (opcion) {
            case 1:
                mostrarVehiculos();
                break;
            case 2:
                agregarVehiculo();
                break;
            case 3:
                buscarVehiculo();
                break;
        }
    }

    private void menuGestionVendedores() {
        System.out.println("\n=== GESTIÓN DE VENDEDORES ===");
        System.out.println("1. Ver todos los vendedores");
        System.out.println("2. Agregar nuevo vendedor");
        System.out.println("3. Buscar vendedor por ID");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        
        int opcion = leerOpcion();
        switch (opcion) {
            case 1:
                mostrarVendedores();
                break;
            case 2:
                agregarVendedor();
                break;
            case 3:
                buscarVendedor();
                break;
        }
    }

    private void menuGestionPedidos() {
        System.out.println("\n=== GESTIÓN DE PEDIDOS ===");
        System.out.println("1. Ver todos los pedidos");
        System.out.println("2. Crear nuevo pedido");
        System.out.println("3. Procesar pedido existente");
        System.out.println("4. Buscar pedido por ID");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        
        int opcion = leerOpcion();
        switch (opcion) {
            case 1:
                mostrarPedidos();
                break;
            case 2:
                crearPedido();
                break;
            case 3:
                procesarPedido();
                break;
            case 4:
                buscarPedido();
                break;
        }
    }

    private void mostrarClientes() {
        System.out.println("\n--- CLIENTES REGISTRADOS ---");
        for (Cliente cliente : concesionaria.getClientes()) {
            System.out.println(cliente);
        }
    }

    private void mostrarVehiculos() {
        System.out.println("\n--- VEHÍCULOS DISPONIBLES ---");
        for (Vehiculo vehiculo : concesionaria.getVehiculos()) {
            System.out.println(vehiculo);
        }
    }

    private void mostrarVendedores() {
        System.out.println("\n--- VENDEDORES ACTIVOS ---");
        for (Vendedor vendedor : concesionaria.getVendedores()) {
            System.out.println(vendedor);
        }
    }

    private void mostrarPedidos() {
        System.out.println("\n--- PEDIDOS REGISTRADOS ---");
        for (PedidoCompra pedido : concesionaria.getPedidos()) {
            System.out.println("Pedido #" + pedido.getNumeroPedido() + " - Cliente: " + pedido.getCliente().getNombre() + 
                             " - Vehículo: " + pedido.getVehiculo().getMarca() + " " + pedido.getVehiculo().getModelo());
        }
    }

    private void agregarCliente() {
        try {
            System.out.print("ID del cliente: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Tipo (Particular/Empresa): ");
            String tipo = scanner.nextLine();
            
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            
            Cliente cliente = new Cliente(nombre, tipo, String.valueOf(id), direccion, "");
            concesionaria.agregarCliente(cliente);
            System.out.println("Cliente agregado exitosamente.");
            
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
    }

    private void agregarVehiculo() {
        try {
            System.out.print("ID del vehículo: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            
            System.out.print("Precio: ");
            BigDecimal precio = new BigDecimal(scanner.nextLine());
            
            System.out.print("Año: ");
            int anio = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Color: ");
            String color = scanner.nextLine();
            
            System.out.print("Tipo (Auto/Camioneta/Camion/Moto): ");
            String tipo = scanner.nextLine().toLowerCase();
            
            Vehiculo vehiculo = null;
            switch (tipo) {
                case "auto":
                    System.out.print("Número de puertas: ");
                    int puertas = Integer.parseInt(scanner.nextLine());
                    System.out.print("Cilindrada: ");
                    double cilindrada = Double.parseDouble(scanner.nextLine());
                    vehiculo = new Auto(marca, modelo, color, "CH" + id, "MOT" + id, precio.doubleValue());
                    break;
                case "camioneta":
                    System.out.print("Número de puertas: ");
                    puertas = Integer.parseInt(scanner.nextLine());
                    System.out.print("Cilindrada: ");
                    cilindrada = Double.parseDouble(scanner.nextLine());
                    vehiculo = new Camioneta(marca, modelo, color, "CH" + id, "MOT" + id, precio.doubleValue());
                    break;
                case "camion":
                    System.out.print("Capacidad de carga (toneladas): ");
                    double capacidad = Double.parseDouble(scanner.nextLine());
                    vehiculo = new Camion(marca, modelo, color, "CH" + id, "MOT" + id, precio.doubleValue());
                    break;
                case "moto":
                    System.out.print("Cilindrada (cc): ");
                    int cilindradaMoto = Integer.parseInt(scanner.nextLine());
                    vehiculo = new Moto(marca, modelo, color, "CH" + id, "MOT" + id, precio.doubleValue());
                    break;
                default:
                    System.out.println("Tipo de vehículo no válido.");
                    return;
            }
            
            concesionaria.agregarVehiculo(vehiculo);
            System.out.println("Vehículo agregado exitosamente.");
            
        } catch (Exception e) {
            System.out.println("Error al agregar vehículo: " + e.getMessage());
        }
    }

    private void agregarVendedor() {
        try {
            System.out.print("ID del vendedor: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Área: ");
            String area = scanner.nextLine();
            
            Vendedor vendedor = new Vendedor(nombre, area);
            concesionaria.agregarVendedor(vendedor);
            System.out.println("Vendedor agregado exitosamente.");
            
        } catch (Exception e) {
            System.out.println("Error al agregar vendedor: " + e.getMessage());
        }
    }

    private void crearPedido() {
        try {
            mostrarClientes();
            System.out.print("ID del cliente: ");
            int clienteId = Integer.parseInt(scanner.nextLine());
            
            mostrarVehiculos();
            System.out.print("ID del vehículo: ");
            int vehiculoId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Forma de pago (contado/tarjeta/transferencia): ");
            String formaPago = scanner.nextLine();
            
            mostrarVendedores();
            System.out.print("ID del vendedor: ");
            int vendedorId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Nombre para facturación: ");
            String nombreFacturacion = scanner.nextLine();
            
            System.out.print("Dirección para facturación: ");
            String direccionFacturacion = scanner.nextLine();
            
            concesionaria.crearPedidoCompleto(clienteId, vehiculoId, formaPago, vendedorId, 
                                            nombreFacturacion, direccionFacturacion);
            
        } catch (Exception e) {
            System.out.println("Error al crear pedido: " + e.getMessage());
        }
    }

    private void procesarPedido() {
        try {
            mostrarPedidos();
            System.out.print("ID del pedido a procesar: ");
            int pedidoId = Integer.parseInt(scanner.nextLine());
            
            concesionaria.procesarPedido(pedidoId);
            
        } catch (Exception e) {
            System.out.println("Error al procesar pedido: " + e.getMessage());
        }
    }

    private void buscarCliente() {
        try {
            System.out.print("ID del cliente: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Cliente cliente = concesionaria.buscarClientePorId(id);
            if (cliente != null) {
                System.out.println("Cliente encontrado: " + cliente);
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
    }

    private void buscarVehiculo() {
        try {
            System.out.print("ID del vehículo: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Vehiculo vehiculo = concesionaria.buscarVehiculoPorId(id);
            if (vehiculo != null) {
                System.out.println("Vehículo encontrado: " + vehiculo);
            } else {
                System.out.println("Vehículo no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar vehículo: " + e.getMessage());
        }
    }

    private void buscarVendedor() {
        try {
            System.out.print("ID del vendedor: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Vendedor vendedor = concesionaria.buscarVendedorPorId(id);
            if (vendedor != null) {
                System.out.println("Vendedor encontrado: " + vendedor);
            } else {
                System.out.println("Vendedor no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar vendedor: " + e.getMessage());
        }
    }

    private void buscarPedido() {
        try {
            System.out.print("ID del pedido: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            PedidoCompra pedido = concesionaria.buscarPedidoPorId(id);
            if (pedido != null) {
                System.out.println("Pedido encontrado: #" + pedido.getNumeroPedido() + 
                                 " - Cliente: " + pedido.getCliente().getNombre());
            } else {
                System.out.println("Pedido no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar pedido: " + e.getMessage());
        }
    }

    private void ejecutarPruebas() {
        System.out.println("\n=== EJECUTANDO PRUEBAS DEL SISTEMA ===");
        TestConcesionaria test = new TestConcesionaria();
        test.ejecutarTodasLasPruebas();
    }
}
