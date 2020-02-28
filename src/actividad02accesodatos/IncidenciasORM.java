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

/**
 *
 * @author USUARIO
 */
public class IncidenciasORM {
    HistorialORM hORM = new HistorialORM();
    
    public void menuPrincipal(){
        System.out.println("Bienvenido al programa de Gestión de Incidencias.\n");
        System.out.println("A continuación,elija una de las siguientes opciones:\n");
        
                    
        int opcion = 7;
        do{System.out.println("Opciones: \n"
                + "1 - Insertar un nuevo empleado.\n"
                + "2 - Loguear empleado.\n"
                + "3 - Modificar un empleado.\n"
                + "4 - Cambiar contraseña a un empleado.\n"
                + "5 - Eliminar un empleado.\n"
                + "6 - Salir.\n");
             
                opcion = PideDatos.pideEntero();
                
               
                switch (opcion){
                    case 1:
                        System.out.println("Ha elegio insertar un nuevo empleado.\n");
                        insertarEmpleado();
                        continue;
                        
                    case 2:
                        System.out.println("Ha elegido loguear un empleado.\n");
                        menuLogueado();
                        validarEmpleado();
                        continue;
                        
                    case 3:
                        System.out.println("Ha elegido modificar un empleado.\n");
                        modificarEmpleado();
                        continue;
                        
                    case 4:
                        System.out.println("Ha elegido cambiar contraseña a un empleado.\n");
                        cambiarContraseña();
                        continue;
                        
                    case 5:
                        System.out.println("Ha elegido eliminar un empleado.\n");
                        eliminarEmpleado();
                        continue;
                                           
                    case 6:
                        System.out.println("Ha elegido salir de la aplicación. Hasta pronto.\n");
                        break;
                        
                    default:
                    System.out.println("La opción introducida no es correcta. Elija una opción del 1 al 6, por favor.");
                    System.out.println("-----------------------------------------------------------------------");
                }

        }while(opcion != 6);
        
    }
    
    public void menuLogueado(){
        System.out.println("Bienvenido empleado.\n");
        System.out.println("A continuación,elija una de las siguientes opciones:\n");
        
                    
        int opcion = 8;
        do{System.out.println("Opciones: \n"
                + "1 - Obtener incidencia a partir del id.\n"
                + "2 - Obtener listado de incidencias.\n"
                + "3 - Insetar nueva incidencia.\n"
                + "4 - Obtener incidencias creadas para un empleado.\n"
                + "5 - Obtener incidencias creadas por un empleado.\n"
                + "6 - Acceder al menú Historial.\n"
                + "7 - Desloguearse y salir al menú anterior.\n");
             
                opcion = PideDatos.pideEntero();
                
               
                switch (opcion){
                    case 1:
                        System.out.println("Ha elegio obtener incidencia a partir de un id.\n");
                        obtenerIncidencia();
                        continue;
                        
                    case 2:
                        System.out.println("Ha elegido obtener listado de incidencias.\n");
                        obtenerListadoIncidencias();
                        continue;
                        
                    case 3:
                        System.out.println("Ha elegido insertar una nueva incidencia.\n");
                       insertarIncidencia();
                        continue;
                        
                    case 4:
                        System.out.println("Ha elegido obtener incidencias creadas para un empleado.\n");
                        obtenerIncidenciasEmpleadoDestino();
                        continue;
                        
                    case 5:
                        System.out.println("Ha elegido Obtener incidencias creadas por un empleado.\n");
                        obtenerIncidenciasEmpleadoOrigen();
                        continue;
                    
                    case 6:
                        System.out.println("Ha elegido acceder al menú Historial.\n");
                        HistorialORM hORM = new HistorialORM();
                        hORM.menuHistorial();
                        hORM.menuHistorial();
                        continue;    
                    case 7:
                        System.out.println("Ha elegido desloguearse y retornar al menú anterior.\n");
                        break;
                        
                    default:
                    System.out.println("La opción introducida no es correcta. Elija una opción del 1 al 6, por favor.");
                    System.out.println("-----------------------------------------------------------------------");
                }

        }while(opcion != 6);
        
    }
    
    public void insertarEmpleado() throws IOException{
        Empleado empleadoInsertar = new Empleado();
        empleadoInsertar.setNombreUsuario(PideDatos.pideString("Introduzca un nombre de usuario: \n"));
        empleadoInsertar.setNombreCompleto(PideDatos.pideString("Introduzca el nombre completo: \n"));
        empleadoInsertar.setTelefono(PideDatos.pideString("Introduzca el número de teléfono: \n"));
        empleadoInsertar.setPassword(PideDatos.pideString("Introduzca un password"));
        
        //COMPROBAR SI EL NOMBRE DE USUARIO EXISTE ANTES DE INSERAR EN LA BD
    }
    
    public void validarEmpleado(){}
    
    public void modificarEmpleado() throws IOException{
        String nombreUsuarioEmpleadoParaModificar = PideDatos.pideString("Introduzca el nombre de usuario del empleado a modificar: \n");
        
        //SELECCIONAR EL EMPLEADO A MODIFICAR EN FUNCIÓN DE SU NOMBREUSUARIO
        
        //CREO OBJETO CON EMPLEADO MODIFICADO MENOS NOMBREUSUARIO QUE NO SE PUEDE MODIFICAR
        Empleado empleadoModificado = new Empleado();
        empleadoModificado.setNombreUsuario(nombreUsuarioEmpleadoParaModificar);
        empleadoModificado.setNombreCompleto(PideDatos.pideString("Introduzca el nombre completo: \n"));
        empleadoModificado.setTelefono(PideDatos.pideString("Introduzca el número de teléfono: \n"));
        empleadoModificado.setPassword(PideDatos.pideString("Introduzca un password"));
        
        //INSERTAR EL OBJETO EMPLEADO MODIFICADO
    }
    
    public void cambiarContraseña() throws IOException{
        String nombreUsuarioEmpleadoParaModificarPassword = PideDatos.pideString("Introduzca el nombre de usuario del empleado para modificar password: \n");
        String nuevaContraseña = PideDatos.pideString("Introduzca la nueva contraseña: \n");
        
    }
    
    public void eliminarEmpleado() throws IOException{
        String nombreUsuarioEliminar = PideDatos.pideString("Introduzca el nombre de usuario del empleado a eliminar: \n");
    }
    
    public void obtenerIncidencia(){}
    
    public void obtenerListadoIncidencias(){}
    
    public void insertarIncidencia() throws IOException{
        Incidencia incidenciaInsertar = new Incidencia();
        incidenciaInsertar.setIdIncidencia(PideDatos.pideEntero("Introduzca una id de la incidencia: \n"));
        incidenciaInsertar.setOrigen(PideDatos.pideString("Introduzca el nombre de usuario del empleado que genera la incidencia: \n"));
        incidenciaInsertar.setDestino(PideDatos.pideString("Introduzca el nombre de usuario del empleado destino: \n"));
        incidenciaInsertar.setFechaHora(PideDatos.pideString("Introduzca la fecha y hora de la incidencia: \n"));
        incidenciaInsertar.setDetalle(PideDatos.pideString("Introduzca la descripción de la incidencia: \n"));
        incidenciaInsertar.setTipo(FuncionesVariadas.solicitarPrioridad());
    }
    
    public void obtenerIncidenciasEmpleadoDestino() throws IOException{
        String nombreUsuarioDestinoVisualizarIncidencias = PideDatos.pideString("Introduzca el nombre de usuario del empleado a modificar: \n");
    }
    
    public void obtenerIncidenciasEmpleadoOrigen() throws IOException{
        String nombreUsuarioOrigenVisualizarIncidencias = PideDatos.pideString("Introduzca el nombre de usuario del empleado a modificar: \n");
    }
    
}
