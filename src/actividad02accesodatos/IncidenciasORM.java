/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad02accesodatos;

import clasesPojo.Empleado;
import clasesPojo.Incidencia;

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
             
                opcion = PideDatos.PideDatos.pideEntero();
                
               
                switch (opcion){
                    case 1:
                        System.out.println("Ha elegio insertar un nuevo empleado.\n");
                        insertarEmpleado();
                        continue;
                        
                    case 2:
                        System.out.println("Ha elegido loguear un empleado.\n");
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
             
                opcion = PideDatos.PideDatos.pideEntero();
                
               
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
    
    public void insertarEmpleado(){}
    
    public void validarEmpleado(){}
    
    public void modificarEmpleado(){}
    
    public void cambiarContraseña(){}
    
    public void eliminarEmpleado(){}
    
    public void obtenerIncidencia(){}
    
    public void obtenerListadoIncidencias(){}
    
    public void insertarIncidencia(){}
    
    public void obtenerIncidenciasEmpleadoDestino(){}
    
    public void obtenerIncidenciasEmpleadoOrigen(){}
    
}
