/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad02accesodatos;

import static actividad02accesodatos.IncidenciasORM.comprobarExistenciaEmpleado;
import static actividad02accesodatos.IncidenciasORM.crearEmpleadoComprobaciones;
import static actividad02accesodatos.IncidenciasORM.crearIncidencia;
import static actividad02accesodatos.IncidenciasORM.mostrarIncidencia;
import static actividad02accesodatos.IncidenciasORM.seleccionarIncidenciasPorEmpleadoDestino;
import clasesPojo.Empleado;
import clasesPojo.Historial;
import clasesPojo.Incidencia;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import PideDatos.PideDatos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class HistorialORM {
    
    
    
    public void insertarEvento(String tipo, Empleado e){
        
        Historial nuevoAsiento = new Historial();
        nuevoAsiento.setEmpleado(e);
        nuevoAsiento.setTipo(tipo);
        nuevoAsiento.setFechahora(obtenerFechaHora());
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(nuevoAsiento);
        tx.commit();
        session.close();
        System.out.println("Nuevo asiento de historial creado.\n");
    
    }
    
    public void obtenerFechaHoraUltimaConexionPorEmpleado(){
        Empleado empleadoUltimaConexion = crearEmpleadoComprobaciones();
        
        boolean existe = comprobarExistenciaEmpleado(empleadoUltimaConexion);
        
        if(!existe){
            System.out.println("El usuario introducido no existe. \n");
        }else{
            Historial ultimaConexion =  seleccionarUltimaConexion(empleadoUltimaConexion);
            System.out.println("Última Conexión: \n");
            mostrarEventoHistorial(ultimaConexion);
            
            }
        
    }
    
    public List<Object[]> obtenerRankingCantidadIncidenciasUrgentesEmpleadoOrigen(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select count(h.empleado), h.empleado from Historial h where h.tipo = 'U' group by h.empleado order by count(h.empleado) desc";
        Query q = session.createQuery(c);
        List<Object[]> results = q.list();
        session.close();
        System.out.println("Nº Incidencias ----- Nombre");
        for(Object[] datos : results){
            Empleado em = (Empleado) datos[1];
            System.out.println("     " + datos[0] + " ------------- " + em.getNombrecompleto());
        }
        System.out.println("\n");
        
        return results;
    }
    
    public void obtenerPosicionRankingPorEmpleado(){
        Empleado e = new Empleado();
        int posicion = 1;
        try {
            e.setNombreusuario(PideDatos.pideString("Introduzca el nombre de usuario del que desea obtener la posición: \n"));
        } catch (IOException ex) {
            Logger.getLogger(HistorialORM.class.getName()).log(Level.SEVERE, null, ex);
        }
         boolean existe = comprobarExistenciaEmpleado(e);
         System.out.println(existe);
        
        if(!existe){
            System.out.println("Nombre de usuario inexistente. Introduzca un nombre de usuario existente.\n");
        }else{
            List<Object[]> ranking = obtenerRankingCantidadIncidenciasUrgentesEmpleadoOrigen();
            for(Object[] datos : ranking){
                Empleado em = (Empleado) datos[1];
                if(em.getNombreusuario().equals(e.getNombreusuario())){
                    System.out.println("La posición en el ranking de empleados creadores de incidencias urgentes es: " + posicion + "\n");
                }else{
                    posicion ++;
                    if(posicion == ranking.size()){
                        System.out.println("El empleado introducido no está en el ranking con lo que no tiene incidencias urgentes creadas.\n");
                    }
                }
            
            }           
        }
        System.out.println("\n");
    }
    
        
    public static String obtenerFechaHora(){
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String fechaHora = hourdateFormat.format(date).toString();
        //System.out.println(fechaHora);
        return fechaHora;
    }
    
    public static Historial seleccionarUltimaConexion(Empleado e){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select h from Historial h where empleado = '" + e.getNombreusuario() + "' and tipo = 'I' order by fechahora desc";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        Historial h = (Historial) results.get(0);
        return h;
    }
    
    public static void mostrarEventoHistorial(Historial h){
        System.out.println("Evento: Id: " + h.getIdevento() + " -- Tipo: " + h.getTipo() + " -- Fecha y Hora: " + h.getFechahora() + " -- Empleado: " + h.getEmpleado().getNombreusuario());
    
    }
    
    
    
    
}