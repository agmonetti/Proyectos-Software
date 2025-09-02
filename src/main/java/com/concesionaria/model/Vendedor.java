package com.concesionaria.model;

public class Vendedor {
    private String nombre;
    private String email;

    public Vendedor(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
}
