package concesionaria.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CambioEstado {
    private LocalDateTime fecha;
    private String estadoAnterior;
    private String estadoNuevo;
    private String observaciones;

    public CambioEstado(String estadoAnterior, String estadoNuevo, String observaciones) {
        this.fecha = LocalDateTime.now();
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.observaciones = observaciones != null ? observaciones : "";
    }

    public LocalDateTime getFecha() { return fecha; }
    public String getEstadoAnterior() { return estadoAnterior; }
    public String getEstadoNuevo() { return estadoNuevo; }
    public String getObservaciones() { return observaciones; }

    @Override
    public String toString() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                " - " + estadoAnterior + " → " + estadoNuevo +
                (observaciones.isEmpty() ? "" : " (" + observaciones + ")");
    }
}

