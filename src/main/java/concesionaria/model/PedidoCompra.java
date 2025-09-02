package concesionaria.model;

import concesionaria.strategy.*;
import concesionaria.state.*;
import concesionaria.observer.*;
import concesionaria.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PedidoCompra {
    private static int contadorPedidos = 1;

    private int numeroPedido;
    private LocalDateTime fechaCreacion;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private ConfiguracionAdicional configuracionAdicional;
    private FormaPago formaPago;
    private EstadoPedido estado;
    private String estadoAnterior;
    private List<String> historial;
    private List<CambioEstado> historialEstados;
    private List<concesionaria.observer.Observer> observers;
    private Vendedor vendedor;
    private DatosFacturacion datosFacturacion;
    private double costoTotal;
    private double impuestos;
    private CalculadoraImpuestos calculadoraImpuestos;

    public PedidoCompra(Cliente cliente, Vehiculo vehiculo, ConfiguracionAdicional configuracionAdicional,
                        FormaPago formaPago, Vendedor vendedor, DatosFacturacion datosFacturacion) {
        this.numeroPedido = contadorPedidos++;
        this.fechaCreacion = LocalDateTime.now();
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.configuracionAdicional = configuracionAdicional;
        this.formaPago = formaPago;
        this.vendedor = vendedor;
        this.datosFacturacion = datosFacturacion;
        this.estado = new EstadoVentas();
        this.estadoAnterior = "";
        this.historial = new ArrayList<>();
        this.historialEstados = new ArrayList<>();
        this.observers = new ArrayList<>();

        // Configurar calculadora de impuestos según tipo de vehículo
        configurarCalculadoraImpuestos();

        // Calcular costo total
        calcularCostoTotal();

        agregarHistorial("Pedido creado");
        registrarCambioEstado("", "Ventas", "Pedido inicial creado");
    }

    private void configurarCalculadoraImpuestos() {
        String tipo = vehiculo.getTipo();
        switch (tipo) {
            case "Auto":
                this.calculadoraImpuestos = new CalculadoraImpuestosAuto();
                break;
            case "Camioneta":
                this.calculadoraImpuestos = new CalculadoraImpuestosCamioneta();
                break;
            case "Moto":
                this.calculadoraImpuestos = new CalculadoraImpuestosMoto();
                break;
            case "Camion":
                this.calculadoraImpuestos = new CalculadoraImpuestosCamion();
                break;
        }
    }

    private void calcularCostoTotal() {
        this.impuestos = calculadoraImpuestos.calcular(vehiculo.getPrecioBase());
        this.costoTotal = vehiculo.getPrecioBase() + impuestos + configuracionAdicional.getCostoAdicional();
    }

    public void agregarObserver(concesionaria.observer.Observer observer) {
        observers.add(observer);
    }

    public void removerObserver(concesionaria.observer.Observer observer) {
        observers.remove(observer);
    }

    private void notificarObservers(String mensaje) {
        for (concesionaria.observer.Observer observer : observers) {
            observer.actualizar(mensaje, this);
        }
    }

    public void setEstado(EstadoPedido nuevoEstado) {
        this.estadoAnterior = this.estado.getNombre();
        this.estado = nuevoEstado;
        registrarCambioEstado(estadoAnterior, nuevoEstado.getNombre(), "Transición automática");
        notificarObservers("Estado cambiado a: " + nuevoEstado.getNombre());
    }

    public void procesar() {
        estado.procesar(this);
    }

    public void agregarHistorial(String evento) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        historial.add(timestamp + " - " + evento);
    }

    public void registrarCambioEstado(String estadoAnterior, String estadoNuevo, String observaciones) {
        CambioEstado cambio = new CambioEstado(estadoAnterior, estadoNuevo, observaciones);
        historialEstados.add(cambio);
    }

    // Getters
    public int getNumeroPedido() { return numeroPedido; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public Cliente getCliente() { return cliente; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public ConfiguracionAdicional getConfiguracionAdicional() { return configuracionAdicional; }
    public FormaPago getFormaPago() { return formaPago; }
    public EstadoPedido getEstado() { return estado; }
    public String getAreaResponsableActual() { return estado.getNombre(); }
    public List<String> getHistorial() { return new ArrayList<>(historial); }
    public List<CambioEstado> getHistorialEstados() { return new ArrayList<>(historialEstados); }
    public Vendedor getVendedor() { return vendedor; }
    public DatosFacturacion getDatosFacturacion() { return datosFacturacion; }
    public double getCostoTotal() { return costoTotal; }
    public double getImpuestos() { return impuestos; }
    public double getPrecioBase() { return vehiculo.getPrecioBase(); }
    public double getCostoAdicionales() { return configuracionAdicional.getCostoAdicional(); }
    public CalculadoraImpuestos getCalculadoraImpuestos() { return calculadoraImpuestos; }

    @Override
    public String toString() {
        return String.format("Pedido #%d - %s - %s - Estado: %s - Total: $%.2f",
                numeroPedido, cliente.getNombre() + " " + cliente.getApellido(),
                vehiculo.getMarca() + " " + vehiculo.getModelo(),
                estado.getNombre(), costoTotal);
    }
}
