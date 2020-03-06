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
    
    public void obtenerRankingCantidadIncidenciasUrgentesEmpleadoOrigen(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select h from Historial h where tipo = 'U' order by empleado";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        ArrayList<Historial> listaEventosUrgentes = new ArrayList<Historial>();
        for(int i = 0; i < results.size(); i++){
            Historial h = (Historial) results.get(i);
            listaEventosUrgentes.add(h);
        }
        for(int i = 0; i < listaEventosUrgentes.size(); i++){
            mostrarEventoHistorial(listaEventosUrgentes.get(i));
        }
        
        
           
    }
    
    public void obtenerPosicionRankingPorEmpleado(){}
    
        
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