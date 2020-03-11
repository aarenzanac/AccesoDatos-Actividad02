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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author USUARIO
 */
public class IncidenciasORM {
    HistorialORM hORM = new HistorialORM();
       
    
    public void insertarEmpleado(){
        Empleado empleadoInsertar = new Empleado();
        try {//CREO EL EMPLEADO PARA AÑADIR
            empleadoInsertar.setNombreusuario(PideDatos.pideString("Introduzca un nombre de usuario: \n"));
            empleadoInsertar.setNombrecompleto(PideDatos.pideString("Introduzca el nombre completo: \n"));
            empleadoInsertar.setTelefono(PideDatos.pideString("Introduzca el número de teléfono: \n"));
            empleadoInsertar.setPassword(PideDatos.pideString("Introduzca un password"));
        } catch (IOException ex) {
            System.out.println("Eror insertando empleado " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        //COMPRUEBO QUE NO EXISTE EL NOMBRE DE USUARIO
        boolean existe = comprobarExistenciaEmpleado(empleadoInsertar);
        
        if(existe){
            System.out.println("Nombre de usuario existente. Introduzca un nombre de usuario nuevo.\n");
        }else{
            //LO AGREGO
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(empleadoInsertar);
            tx.commit();
            session.close();
            System.out.println("Empleado insertado con éxito.\n");
        }
    }
    
    public void validarEmpleado(){
        Menus m = new Menus();
        boolean login = false;
        Empleado empleadoLogin = new Empleado();
        try {
            System.out.println("A continuación introduzca los datos de login: \n");
            String nombreUsuario = PideDatos.pideString("Nombre de usuario: \n");
            String password = PideDatos.pideString("Password: \n");
            empleadoLogin.setNombreusuario(nombreUsuario);
            empleadoLogin.setPassword(password);
            //LE PASO EL OBJETO EMPLEADO CON LOS DATOS DEL USUARIO Y SI EXISTE ESE USUARIO CON ESE PASSWORD DEVUELVE TRUE         
            login = comprobarLoginEmpleado(empleadoLogin);
        } catch (IOException ex) {
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(login){
            hORM.insertarEvento("I", empleadoLogin);
            m.menuLogueado(empleadoLogin);
            
        }else{
            System.out.println("Nombre de usuario o contraseña incorrectos. \n");
        }
        
    }
    
    public void modificarEmpleado(){
        Empleado empleadoModificar = crearEmpleadoComprobaciones();
            
        //COMPRUEBO SI EXISTE
        boolean existe = comprobarExistenciaEmpleado(empleadoModificar);

        if(!existe){
            System.out.println("El usuario que indica no existe con lo que no se puede modificar. Introduzca un nombre de usuario nuevo.\n");
        }else{try {
                    empleadoModificar = seleccionarEmpleado(empleadoModificar);
                    empleadoModificar.setNombrecompleto(PideDatos.pideString("Introduzca el nuevo nombre completo: \n"));
                    empleadoModificar.setTelefono(PideDatos.pideString("Introduzca el nuevo número de teléfono: \n"));
                    Session session = NewHibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    session.update(empleadoModificar);
                    tx.commit();
                    session.close();
                    System.out.println("Empleado modificado con éxito.\n");
                } catch (IOException ex) {
                Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }
    
    public void cambiarContraseña(){
        Empleado empleadoModificarContraseña = crearEmpleadoComprobaciones();

        boolean existe = comprobarExistenciaEmpleado(empleadoModificarContraseña);
        if(existe){
            try {
                empleadoModificarContraseña = seleccionarEmpleado(empleadoModificarContraseña);
                empleadoModificarContraseña.setPassword(PideDatos.pideString("Introduzca la nueva contraseña: \n"));
                Session session = NewHibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.update(empleadoModificarContraseña);
                tx.commit();
                session.close();
                System.out.println("Contraseña modificada con éxito.\n");
            } catch (IOException ex) {
                Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("El usuario al que quiere cambiar la contraseña no existe.\n");
        }        
    }
    
    public void eliminarEmpleado(){
        
        Empleado empleadoEliminar = crearEmpleadoComprobaciones();
       
        boolean existe = comprobarExistenciaEmpleado(empleadoEliminar);
        if(existe){
            empleadoEliminar = seleccionarEmpleado(empleadoEliminar);
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(empleadoEliminar);
            tx.commit(); //MAterializa la transacción
            session.close();
            System.out.println ("Empleado eliminado.\n");
        }else{
            System.out.println("El empleado que desea eliminar no existe.\n");
        }
    }
    
    public void obtenerIncidencia(){
        Incidencia incidenciaSeleccionar = new Incidencia();
        incidenciaSeleccionar.setIdincidencia(PideDatos.pideEntero("Introduzca el id de la incidencia que quiere mostrar: \n"));
        boolean existe = comprobarExistenciaIncidencia(incidenciaSeleccionar);
        if(existe){
            Incidencia incidenciaSeleccionada = seleccionarIncidencia(incidenciaSeleccionar);
            mostrarIncidencia(incidenciaSeleccionada);
    
        }else{
            System.out.println("No hay incidencias para mostrar con el id indicado.\n");
        }
        
    }
    
    public void obtenerListadoIncidencias(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select i from Incidencia i";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        for(int i = 0; i < results.size(); i++){
            Incidencia incidencia = (Incidencia) results.get(i);
            mostrarIncidencia(incidencia);
        }
        
    }
    
    public void insertarIncidencia(Empleado empleadoLogin){
        Incidencia nuevaIncidencia = crearIncidencia();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(nuevaIncidencia);
        if(nuevaIncidencia.getTipo().equals("Urgente")){
           hORM.insertarEvento("U", empleadoLogin); 
        }
        tx.commit();
        session.close();
        System.out.println("Incidencia insertada con éxito.\n");
    }
    
    public void obtenerIncidenciasEmpleadoDestino(Empleado empleadoLogin){
        Empleado empleadoDestino = crearEmpleadoComprobaciones();
        
        boolean existe = comprobarExistenciaEmpleado(empleadoDestino);
        
        if(!existe){
            System.out.println("El usuario introducido no existe. \n");
        }else{
            List incidenciasPorEmpleadoDestino =  seleccionarIncidenciasPorEmpleadoDestino(empleadoDestino);
            System.out.println("Listado de incidencias para el usuario " + empleadoDestino.getNombreusuario());
            for(int i = 0; i < incidenciasPorEmpleadoDestino.size(); i++){
                mostrarIncidencia((Incidencia) incidenciasPorEmpleadoDestino.get(i));
            }
         
        }
        hORM.insertarEvento("C", empleadoLogin);
    }
    
    public void obtenerIncidenciasEmpleadoOrigen(Empleado empleadoLogin){
        Empleado empleadoOrigen = crearEmpleadoComprobaciones();
        
        boolean existe = comprobarExistenciaEmpleado(empleadoOrigen);
        
        if(!existe){
            System.out.println("El usuario introducido no existe. \n");
        }else{
            List incidenciasPorEmpleadoOrigen =  seleccionarIncidenciasPorEmpleadoOrigen(empleadoOrigen);
            System.out.println("Listado de incidencias para el usuario " + empleadoOrigen.getNombreusuario());
            for(int i = 0; i < incidenciasPorEmpleadoOrigen.size(); i++){
                mostrarIncidencia((Incidencia) incidenciasPorEmpleadoOrigen.get(i));
            } 
        }
        hORM.insertarEvento("C", empleadoLogin);
    }
    
    public static Empleado crearEmpleadoComprobaciones(){
        Empleado empleadoComprobaciones = new Empleado();
        try {String nombreUsuario = PideDatos.pideString("Introduzca el nombre de usuario del empleado: \n");
            empleadoComprobaciones.setNombreusuario(nombreUsuario);
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleadoComprobaciones;
    }
    
    public static boolean comprobarExistenciaEmpleado(Empleado e){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select e from Empleado e WHERE nombreusuario = '" + e.getNombreusuario() + "'";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        if(!results.isEmpty()){
                return true;
            }else{
                return false;
            }
    }
    
    public static Empleado seleccionarEmpleado(Empleado e){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Empleado empleadoSeleccionado = (Empleado)session.get(Empleado.class,e.getNombreusuario());
        session.close();
        
        //String c = "select e from Empleado e WHERE nombreusuario = '" + e.getNombreusuario() + "'";
        //Query q = session.createQuery(c);
        //List results = q.list();
        //Empleado empleadoSeleccionado = (Empleado) results.get(0);
        
        return empleadoSeleccionado;
        
    }
    
    public static boolean comprobarLoginEmpleado(Empleado e){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select e from Empleado e WHERE nombreusuario = '" + e.getNombreusuario() + "' and password='" + e.getPassword() + "'";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        if(!results.isEmpty()){
                return true;
            }else{
                return false;
            }
    }
    
    public static boolean comprobarExistenciaIncidencia(Incidencia i){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select i from Incidencia i WHERE idincidencia = '" + i.getIdincidencia()+ "'";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        if(!results.isEmpty()){
            return true;
        }else{
            return false;
        }
        
    }
    
    public static Incidencia seleccionarIncidencia(Incidencia i){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Incidencia incidenciaSeleccionada = (Incidencia)session.get(Incidencia.class,i.getIdincidencia());
        session.close();
                
        //String c = "select i from Incidencia i WHERE idincidencia = " + i.getIdincidencia();
        //Query q = session.createQuery(c);
        //List results = q.list();
        
        return incidenciaSeleccionada;
    }
    
    public static void mostrarIncidencia(Incidencia i){
        //System.out.println(i.toString());
        System.out.println("Incidencia{" + "IdIncidencia= " + i.getIdincidencia() + " -- Fecha/Hora= " + i.getFechahora() + " -- Origen= " 
                + i.getEmpleadoByOrigen().getNombreusuario() + " -- Destino= " + i.getEmpleadoByDestino().getNombreusuario() + " -- Detalle= " + i.getDetalle() + " -- Tipo= " + i.getTipo() + "}\n");

    }
    
    public static Incidencia crearIncidencia(){
        Incidencia nuevaIncidencia = new Incidencia();
        Empleado empleadoOrigen = new Empleado();
        Empleado empleadoDestino = new Empleado();
        boolean existe = false;
        boolean existe1 = false;
        
        try {do{
                nuevaIncidencia.setIdincidencia(obtenerSiguienteId());
                empleadoOrigen.setNombreusuario(PideDatos.pideString("Introduzca el nombre de usuario del empleado origen; \n"));
                existe = comprobarExistenciaEmpleado(empleadoOrigen);
                if(existe){
                    empleadoOrigen = seleccionarEmpleado(empleadoOrigen);
                    nuevaIncidencia.setEmpleadoByOrigen(empleadoOrigen);
                }else{
                    System.out.println("El empleado introducido no existe. Introduzca otro. \n");
                }
            }while(!existe);
            
            do{empleadoDestino.setNombreusuario(PideDatos.pideString("Introduzca el nombre de usuario del empleado destino; \n"));
                existe1 = comprobarExistenciaEmpleado(empleadoDestino);
                if(existe1){
                    empleadoOrigen = seleccionarEmpleado(empleadoDestino);
                    nuevaIncidencia.setEmpleadoByDestino(empleadoDestino);
                }else{
                    System.out.println("El empleado introducido no existe");
                }
            }while(!existe1);
            nuevaIncidencia.setFechahora(HistorialORM.obtenerFechaHora());
            nuevaIncidencia.setDetalle(PideDatos.pideString("Introduzca la descripción de la incidencia: \n"));
            nuevaIncidencia.setTipo(FuncionesVariadas.solicitarPrioridad());
        } catch (IOException ex) {
            System.out.println("Error insertando incidencia " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nuevaIncidencia;
    }
    
    public static int obtenerSiguienteId(){
        int siguienteId = 0;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select i from Incidencia i";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        int cantidadIncidencias = results.size();
        siguienteId = cantidadIncidencias + 1;
        System.out.println(siguienteId);
        return siguienteId;
    }
    
    public static List seleccionarIncidenciasPorEmpleadoDestino(Empleado e){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select i from Incidencia i WHERE destino = '" + e.getNombreusuario() + "'";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        return results;
    }
    
    public static List seleccionarIncidenciasPorEmpleadoOrigen(Empleado e){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String c = "select i from Incidencia i WHERE origen = '" + e.getNombreusuario() + "'";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        return results;
    }
}
