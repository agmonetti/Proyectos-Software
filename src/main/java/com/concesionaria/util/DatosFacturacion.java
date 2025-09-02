package com.concesionaria.util;

public class DatosFacturacion {
    private String nombreRazonSocial;
    private String direccion;
    private String cuitCuil;

    public DatosFacturacion(String nombreRazonSocial, String direccion, String cuitCuil) {
        this.nombreRazonSocial = nombreRazonSocial;
        this.direccion = direccion;
        this.cuitCuil = cuitCuil;
    }

    public String getNombreRazonSocial() { return nombreRazonSocial; }
    public String getDireccion() { return direccion; }
    public String getCuitCuil() { return cuitCuil; }

    @Override
    public String toString() {
        return nombreRazonSocial + " | " + direccion +
                (cuitCuil != null && !cuitCuil.isEmpty() ? " | CUIT/CUIL: " + cuitCuil : "");
    }
}
