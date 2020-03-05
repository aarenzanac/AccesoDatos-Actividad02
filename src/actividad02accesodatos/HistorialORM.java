/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad02accesodatos;

import static actividad02accesodatos.IncidenciasORM.crearIncidencia;
import clasesPojo.Empleado;
import clasesPojo.Historial;
import clasesPojo.Incidencia;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    
    public void obtenerFechaHoraUltimaConexionPorEmpleado(){}
    
    public void obtenerRankingCantidadIncidenciasUrgentesEmpleadoOrigen(){}
    
    public void obtenerPosicionRankingPorEmpleado(){}
    
    /*public static int obtenerSiguienteId(){
        int siguienteId = 0;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select h from historial h";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        int cantidadApuntes = results.size();
        siguienteId = cantidadApuntes + 1;
        System.out.println(siguienteId);
        return siguienteId;
    } */
    
    public static String obtenerFechaHora(){
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String fechaHora = hourdateFormat.format(date).toString();
        System.out.println(fechaHora);
        return fechaHora;
    }
}