/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesPojo;

/**
 *
 * @author USUARIO
 */
public class Empleado {
    
    private String nombreUsuario;
    private String password;
    private String nombreCompleto;
    private String telefono;

    public Empleado() {
    }

    public Empleado(String nombreUsuario, String password, String nombreCompleto, String telefono) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Empleado{" + "Nombre de Usuario= " + nombreUsuario + " -- Password= " + password + " -- Nombre Completo= " + nombreCompleto + " -- Telefono= " + telefono + '}';
    }
    
    
    
}
