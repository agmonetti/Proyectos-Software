package concesionaria.model;

public class Cliente {
    private String nombre;
    private String apellido;
    private String documento;
    private String email;
    private String telefono;

    public Cliente(String nombre, String apellido, String documento, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDocumento() { return documento; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Doc: " + documento + ")";
    }
}
