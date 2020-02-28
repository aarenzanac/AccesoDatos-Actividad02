/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad02accesodatos;

/**
 *
 * @author USUARIO
 */
public class HistorialORM {
    
    public void menuHistorial(){
        System.out.println("Menú Historial.\n");
        System.out.println("A continuación,elija una de las siguientes opciones:\n");
        
                    
        int opcion = 5;
        do{System.out.println("Opciones: \n"
                + "1 - Obtener fecha y hora del último inicio de sesión para un empleado.\n"
                + "2 - Obtener ranking de empleados por cantidad de incidencias urgentes creadas.\n"
                + "3 - Obtener posición deltro del ranking para un empleado concreto.\n"
                + "4 - Retornar al menú anterior.\n");
             
                opcion = PideDatos.PideDatos.pideEntero();
                
               
                switch (opcion){
                    case 1:
                        System.out.println("Ha elegio obtener fecha y hora del último inicio de sesión para un empleado.\n");
                        obtenerFechaHoraUltimaConexionPorEmpleado();
                        continue;
                        
                    case 2:
                        System.out.println("Ha elegido obtener ranking de empleados por cantidad de incidencias urgentes creadas.\n");
                        obtenerRankingCantidadIncidenciasUrgentesEmpleadoOrigen();
                        continue;
                        
                    case 3:
                        System.out.println("Ha elegido obtener posición deltro del ranking para un empleado concreto.\n");
                        obtenerPosicionRankingPorEmpleado();
                        continue;
                                                                  
                    case 4:
                        System.out.println("Ha elegido retornar al menú anteior.\n");
                        IncidenciasORM iORM = new IncidenciasORM();
                        iORM.menuLogueado();
                        break;
                        
                    default:
                    System.out.println("La opción introducida no es correcta. Elija una opción del 1 al 6, por favor.");
                    System.out.println("-----------------------------------------------------------------------");
                }

        }while(opcion != 6);
        
    }
    
    public void insertarEvento(){}
    
    public void obtenerFechaHoraUltimaConexionPorEmpleado(){}
    
    public void obtenerRankingCantidadIncidenciasUrgentesEmpleadoOrigen(){}
    
    public void obtenerPosicionRankingPorEmpleado(){}
    
}
