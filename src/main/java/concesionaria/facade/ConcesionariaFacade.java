package concesionaria.facade;

import concesionaria.exception.*;
import concesionaria.model.*;
import concesionaria.observer.*;
import concesionaria.strategy.*;
import concesionaria.util.*;
import concesionaria.validation.*;
import java.util.*;

public class ConcesionariaFacade {
    private static final String NOMBRE_CONCESIONARIA = "Concesionaria AutoMax";
    private static final String CUIT_CONCESIONARIA = "30-12345678-9";

    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<PedidoCompra> pedidos;
    private ValidadorPedido cadenaValidacion;

    public ConcesionariaFacade() {
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.pedidos = new ArrayList<>();

        configurarValidadores();
        inicializarDatos();
    }

    private void configurarValidadores() {
        // Crear todos los validadores
        ValidadorCliente validadorCliente = new ValidadorCliente();
        ValidadorVehiculo validadorVehiculo = new ValidadorVehiculo();
        ValidadorFormaPago validadorFormaPago = new ValidadorFormaPago();
        ValidadorVendedor validadorVendedor = new ValidadorVendedor();
        ValidadorDatosFacturacion validadorFacturacion = new ValidadorDatosFacturacion();

        // Configurar la cadena de responsabilidad
        validadorCliente.setSiguiente(validadorVehiculo);
        validadorVehiculo.setSiguiente(validadorFormaPago);
        validadorFormaPago.setSiguiente(validadorVendedor);
        validadorVendedor.setSiguiente(validadorFacturacion);

        // El primer validador de la cadena
        this.cadenaValidacion = validadorCliente;

        System.out.println("✓ Cadena de validaciones configurada: Cliente → Vehículo → FormaPago → Vendedor → Facturación");
    }

    private void inicializarDatos() {
        inicializarClientes();
        inicializarVehiculos();
        System.out.println("Sistema inicializado con datos de ejemplo.");
    }

    private void inicializarClientes() {
        try {
            // Registrar clientes de ejemplo
            registrarCliente("Juan Carlos", "Pérez", "20123456781", "juan.perez@email.com", "11-2345-6789");
            registrarCliente("María Elena", "García", "27234567892", "maria.garcia@email.com", "11-3456-7890");
            registrarCliente("Carlos Roberto", "López", "23345678903", "carlos.lopez@email.com", "11-4567-8901");
            registrarCliente("Ana Sofía", "Martínez", "24456789014", "ana.martinez@email.com", "11-5678-9012");
            registrarCliente("Luis Fernando", "Rodríguez", "25567890125", "luis.rodriguez@email.com", "11-6789-0123");

            System.out.println("✓ " + clientes.size() + " clientes registrados correctamente");
        } catch (DuplicateException e) {
            System.err.println("Error registrando clientes: " + e.getMessage());
        }
    }

    private void inicializarVehiculos() {
        try {
            // AUTOS
            agregarVehiculo(new Auto("Toyota", "Corolla", "Blanco", "CH001", "MT001", 25000));
            agregarVehiculo(new Auto("Honda", "Civic", "Plateado", "CH005", "MT005", 28000));
            agregarVehiculo(new Auto("Volkswagen", "Golf", "Negro", "CH006", "MT006", 32000));

            // CAMIONETAS
            agregarVehiculo(new Camioneta("Ford", "Ranger", "Negro", "CH002", "MT002", 35000));
            agregarVehiculo(new Camioneta("Toyota", "Hilux", "Blanco", "CH012", "MT012", 38000));

            // MOTOS
            agregarVehiculo(new Moto("Honda", "CBR600", "Rojo", "CH003", "MT003", 8000));
            agregarVehiculo(new Moto("Yamaha", "R6", "Azul", "CH017", "MT017", 9500));

            // CAMIONES
            agregarVehiculo(new Camion("Mercedes", "Actros", "Azul", "CH004", "MT004", 80000));

            System.out.println("✓ " + vehiculos.size() + " vehículos agregados correctamente");
        } catch (DuplicateException e) {
            System.err.println("Error agregando vehículos: " + e.getMessage());
        }
    }

    public void registrarCliente(String nombre, String apellido, String documento, String email, String telefono) throws DuplicateException {
        // Verificar duplicados
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                throw new DuplicateException("Ya existe un cliente con el documento: " + documento);
            }
        }

        Cliente nuevoCliente = new Cliente(nombre, apellido, documento, email, telefono);
        clientes.add(nuevoCliente);
        System.out.println("Cliente registrado exitosamente: " + nuevoCliente);
    }

    public void agregarVehiculo(Vehiculo vehiculo) throws DuplicateException {
        // Verificar duplicados por número de chasis
        for (Vehiculo v : vehiculos) {
            if (v.getNumeroChasis().equals(vehiculo.getNumeroChasis())) {
                throw new DuplicateException("Ya existe un vehículo con el número de chasis: " + vehiculo.getNumeroChasis());
            }
        }

        vehiculos.add(vehiculo);
        System.out.println("Vehículo agregado exitosamente: " + vehiculo);
    }

    public PedidoCompra crearPedido(String documentoCliente, String numeroChasis, String tipoFormaPago,
                                    String nombreVendedor, String emailVendedor) throws ProcessingException, DuplicateException {
        ConfiguracionAdicional configDefault = new ConfiguracionAdicional("", "", "", 0);
        DatosFacturacion facturacionDefault = new DatosFacturacion("Cliente Final", "Dirección no especificada", "");
        return crearPedido(documentoCliente, numeroChasis, tipoFormaPago, nombreVendedor, emailVendedor, configDefault, facturacionDefault);
    }

    public PedidoCompra crearPedido(String documentoCliente, String numeroChasis, String tipoFormaPago,
                                    String nombreVendedor, String emailVendedor, ConfiguracionAdicional configuracionAdicional,
                                    DatosFacturacion datosFacturacion) throws ProcessingException, DuplicateException {
        // Buscar cliente
        Cliente cliente = buscarClientePorDocumento(documentoCliente);
        if (cliente == null) {
            throw new ProcessingException("Cliente no encontrado con documento: " + documentoCliente);
        }

        // Buscar vehículo
        Vehiculo vehiculo = buscarVehiculoPorChasis(numeroChasis);
        if (vehiculo == null) {
            throw new ProcessingException("Vehículo no encontrado con número de chasis: " + numeroChasis);
        }

        // Verificar si el vehículo ya está en un pedido
        for (PedidoCompra pedido : pedidos) {
            if (pedido.getVehiculo().getNumeroChasis().equals(numeroChasis)) {
                throw new DuplicateException("El vehículo ya está asignado a otro pedido");
            }
        }

        // Crear forma de pago
        FormaPago formaPago = crearFormaPago(tipoFormaPago);

        // Crear vendedor
        Vendedor vendedor = new Vendedor(nombreVendedor, emailVendedor);

        // Crear pedido
        PedidoCompra pedido = new PedidoCompra(cliente, vehiculo, configuracionAdicional, formaPago, vendedor, datosFacturacion);

        // Validar pedido
        cadenaValidacion.validar(pedido);

        // Agregar observers para notificaciones
        pedido.agregarObserver(new NotificadorArea("VENTAS"));
        pedido.agregarObserver(new NotificadorArea("COBRANZAS"));
        pedido.agregarObserver(new NotificadorArea("IMPUESTOS"));

        pedidos.add(pedido);
        System.out.println("Pedido creado exitosamente: " + pedido);

        return pedido;
    }

    private FormaPago crearFormaPago(String tipo) throws ProcessingException {
        switch (tipo.toLowerCase()) {
            case "contado":
                return new PagoContado();
            case "transferencia":
                return new PagoTransferencia();
            case "tarjeta":
                return new PagoTarjeta();
            default:
                throw new ProcessingException("Tipo de pago no válido: " + tipo);
        }
    }

    public void procesarPedido(int numeroPedido) throws ProcessingException {
        PedidoCompra pedido = buscarPedidoPorNumero(numeroPedido);
        if (pedido == null) {
            throw new ProcessingException("Pedido no encontrado: " + numeroPedido);
        }

        pedido.procesar();
    }

    public Cliente buscarClientePorDocumento(String documento) {
        return clientes.stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst()
                .orElse(null);
    }

    public Vehiculo buscarVehiculoPorChasis(String numeroChasis) {
        return vehiculos.stream()
                .filter(v -> v.getNumeroChasis().equals(numeroChasis))
                .findFirst()
                .orElse(null);
    }

    public PedidoCompra buscarPedidoPorNumero(int numeroPedido) {
        return pedidos.stream()
                .filter(p -> p.getNumeroPedido() == numeroPedido)
                .findFirst()
                .orElse(null);
    }

    // Getters para acceso a las listas
    public List<Cliente> getClientes() { return new ArrayList<>(clientes); }
    public List<Vehiculo> getVehiculos() { return new ArrayList<>(vehiculos); }
    public List<PedidoCompra> getPedidos() { return new ArrayList<>(pedidos); }
    public static String getNombreConcesionaria() { return NOMBRE_CONCESIONARIA; }
    public static String getCuitConcesionaria() { return CUIT_CONCESIONARIA; }

    // Métodos adicionales requeridos por MenuPrincipal
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DE LA CONCESIONARIA ===");
        System.out.println("Total de clientes: " + clientes.size());
        System.out.println("Total de vehículos: " + vehiculos.size());
        System.out.println("Total de pedidos: " + pedidos.size());
        
        if (!pedidos.isEmpty()) {
            double montoTotal = pedidos.stream().mapToDouble(PedidoCompra::getCostoTotal).sum();
            System.out.println("Monto total vendido: $" + String.format("%.2f", montoTotal));
            
            Map<String, Long> estadosPedidos = new HashMap<>();
            for (PedidoCompra pedido : pedidos) {
                String estado = pedido.getEstado().getNombre();
                estadosPedidos.put(estado, estadosPedidos.getOrDefault(estado, 0L) + 1);
            }
            
            System.out.println("\nPedidos por estado:");
            for (Map.Entry<String, Long> entry : estadosPedidos.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public PedidoCompra crearPedidoCompleto(int clienteId, int vehiculoId, String formaPago, int vendedorId, 
                                           String nombreCliente, String direccionCliente) throws ProcessingException, DuplicateException {
        // Buscar cliente por ID (simulado con el documento)
        Cliente cliente = null;
        if (clienteId <= clientes.size() && clienteId > 0) {
            cliente = clientes.get(clienteId - 1);
        }
        if (cliente == null) {
            throw new ProcessingException("Cliente no encontrado con ID: " + clienteId);
        }

        // Buscar vehículo por ID (simulado con el índice)
        Vehiculo vehiculo = null;
        if (vehiculoId <= vehiculos.size() && vehiculoId > 0) {
            vehiculo = vehiculos.get(vehiculoId - 1);
        }
        if (vehiculo == null) {
            throw new ProcessingException("Vehículo no encontrado con ID: " + vehiculoId);
        }

        // Crear vendedor temporal
        Vendedor vendedor = new Vendedor("Vendedor " + vendedorId, "vendedor" + vendedorId + "@automax.com");

        // Configuración por defecto
        ConfiguracionAdicional config = new ConfiguracionAdicional("", "", "", 0);
        DatosFacturacion facturacion = new DatosFacturacion(nombreCliente, direccionCliente, "");

        return crearPedido(cliente.getDocumento(), vehiculo.getNumeroChasis(), formaPago, 
                          vendedor.getNombre(), vendedor.getEmail(), config, facturacion);
    }

    public List<Vendedor> getVendedores() {
        // Devuelve una lista de vendedores simulada
        List<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(new Vendedor("Carlos Vendedor", "carlos@automax.com"));
        vendedores.add(new Vendedor("Ana Vendedora", "ana@automax.com"));
        vendedores.add(new Vendedor("Luis Vendedor", "luis@automax.com"));
        vendedores.add(new Vendedor("María Vendedora", "maria@automax.com"));
        return vendedores;
    }

    public void agregarCliente(Cliente cliente) throws DuplicateException {
        registrarCliente(cliente.getNombre(), cliente.getApellido(), cliente.getDocumento(), 
                        cliente.getEmail(), cliente.getTelefono());
    }

    public void agregarVendedor(Vendedor vendedor) {
        // Los vendedores se crean dinámicamente, no se almacenan
        System.out.println("Vendedor registrado: " + vendedor.getNombre());
    }

    public Cliente buscarClientePorId(int id) {
        if (id > 0 && id <= clientes.size()) {
            return clientes.get(id - 1);
        }
        return null;
    }

    public Vehiculo buscarVehiculoPorId(int id) {
        if (id > 0 && id <= vehiculos.size()) {
            return vehiculos.get(id - 1);
        }
        return null;
    }

    public Vendedor buscarVendedorPorId(int id) {
        List<Vendedor> vendedores = getVendedores();
        if (id > 0 && id <= vendedores.size()) {
            return vendedores.get(id - 1);
        }
        return null;
    }

    public PedidoCompra buscarPedidoPorId(int id) {
        if (id > 0 && id <= pedidos.size()) {
            return pedidos.get(id - 1);
        }
        return null;
    }
}
