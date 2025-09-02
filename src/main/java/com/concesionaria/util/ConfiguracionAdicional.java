package com.concesionaria.util;

public class ConfiguracionAdicional {
    private String equipamientoExtra;
    private String garantiasExtendidas;
    private String accesorios;
    private double costoAdicional;

    public ConfiguracionAdicional(String equipamientoExtra, String garantiasExtendidas, String accesorios, double costoAdicional) {
        this.equipamientoExtra = equipamientoExtra != null ? equipamientoExtra : "";
        this.garantiasExtendidas = garantiasExtendidas != null ? garantiasExtendidas : "";
        this.accesorios = accesorios != null ? accesorios : "";
        this.costoAdicional = costoAdicional;
    }

    public String getEquipamientoExtra() { return equipamientoExtra; }
    public String getGarantiasExtendidas() { return garantiasExtendidas; }
    public String getAccesorios() { return accesorios; }
    public double getCostoAdicional() { return costoAdicional; }

    public boolean tieneConfiguraciones() {
        return !equipamientoExtra.isEmpty() || !garantiasExtendidas.isEmpty() || !accesorios.isEmpty();
    }

    @Override
    public String toString() {
        if (!tieneConfiguraciones()) {
            return "Sin configuraciones adicionales";
        }
        StringBuilder sb = new StringBuilder();
        if (!equipamientoExtra.isEmpty()) sb.append("Equipamiento: ").append(equipamientoExtra).append(" | ");
        if (!garantiasExtendidas.isEmpty()) sb.append("Garantías: ").append(garantiasExtendidas).append(" | ");
        if (!accesorios.isEmpty()) sb.append("Accesorios: ").append(accesorios).append(" | ");
        if (costoAdicional > 0) sb.append("Costo adicional: $").append(costoAdicional);
        return sb.toString();
    }
}
