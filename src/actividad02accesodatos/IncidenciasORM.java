/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad02accesodatos;

import clasesPojo.Empleado;
import clasesPojo.Incidencia;
import PideDatos.PideDatos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class IncidenciasORM {
    HistorialORM hORM = new HistorialORM();
    
    
    public void insertarEmpleado(){
        Empleado empleadoInsertar = new Empleado();
        try {
            empleadoInsertar.setNombreUsuario(PideDatos.pideString("Introduzca un nombre de usuario: \n"));
            empleadoInsertar.setNombreCompleto(PideDatos.pideString("Introduzca el nombre completo: \n"));
            empleadoInsertar.setTelefono(PideDatos.pideString("Introduzca el número de teléfono: \n"));
            empleadoInsertar.setPassword(PideDatos.pideString("Introduzca un password"));
        } catch (IOException ex) {
            System.out.println("Eror insertando empleado " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //COMPROBAR SI EL NOMBRE DE USUARIO EXISTE ANTES DE INSERAR EN LA BD
    }
    
    public boolean validarEmpleado(){
        boolean existeEmpleado = true;
        return existeEmpleado;
    }
    
    public void modificarEmpleado(){
        try {
            String nombreUsuarioEmpleadoParaModificar = PideDatos.pideString("Introduzca el nombre de usuario del empleado a modificar: \n");
            
            //SELECCIONAR EL EMPLEADO A MODIFICAR EN FUNCIÓN DE SU NOMBREUSUARIO
            
            //CREO OBJETO CON EMPLEADO MODIFICADO MENOS NOMBREUSUARIO QUE NO SE PUEDE MODIFICAR
            Empleado empleadoModificado = new Empleado();
            empleadoModificado.setNombreUsuario(nombreUsuarioEmpleadoParaModificar);
            empleadoModificado.setNombreCompleto(PideDatos.pideString("Introduzca el nombre completo: \n"));
            empleadoModificado.setTelefono(PideDatos.pideString("Introduzca el número de teléfono: \n"));
            empleadoModificado.setPassword(PideDatos.pideString("Introduzca un password"));
            
            //INSERTAR EL OBJETO EMPLEADO MODIFICADO
        } catch (IOException ex) {
            System.out.println("Error modificando empleado " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cambiarContraseña(){
        try {
            String nombreUsuarioEmpleadoParaModificarPassword = PideDatos.pideString("Introduzca el nombre de usuario del empleado para modificar password: \n");
            String nuevaContraseña = PideDatos.pideString("Introduzca la nueva contraseña: \n");
        } catch (IOException ex) {
            System.out.println("Error modificando cambiando contraseña " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void eliminarEmpleado(){
        try {
            String nombreUsuarioEliminar = PideDatos.pideString("Introduzca el nombre de usuario del empleado a eliminar: \n");
        } catch (IOException ex) {
            System.out.println("Error eliminando empleado " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerIncidencia(){}
    
    public void obtenerListadoIncidencias(){}
    
    public void insertarIncidencia(){
        try {
            Incidencia incidenciaInsertar = new Incidencia();
            incidenciaInsertar.setIdIncidencia(PideDatos.pideEntero("Introduzca una id de la incidencia: \n"));
            incidenciaInsertar.setOrigen(PideDatos.pideString("Introduzca el nombre de usuario del empleado que genera la incidencia: \n"));
            incidenciaInsertar.setDestino(PideDatos.pideString("Introduzca el nombre de usuario del empleado destino: \n"));
            incidenciaInsertar.setFechaHora(PideDatos.pideString("Introduzca la fecha y hora de la incidencia: \n"));
            incidenciaInsertar.setDetalle(PideDatos.pideString("Introduzca la descripción de la incidencia: \n"));
            incidenciaInsertar.setTipo(FuncionesVariadas.solicitarPrioridad());
        } catch (IOException ex) {
            System.out.println("Error insertando incidencia " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerIncidenciasEmpleadoDestino(){
        try {
            String nombreUsuarioDestinoVisualizarIncidencias = PideDatos.pideString("Introduzca el nombre de usuario del empleado a modificar: \n");
        } catch (IOException ex) {
            System.out.println("Error obteniendo incidencias " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerIncidenciasEmpleadoOrigen(){
        try {
            String nombreUsuarioOrigenVisualizarIncidencias = PideDatos.pideString("Introduzca el nombre de usuario del empleado a modificar: \n");
        } catch (IOException ex) {
            System.out.println("Error obteniendo incidencias " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
