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
        try {
            System.out.println("A continuación introduzca los datos de login: \n");
            String nombreUsuario = PideDatos.pideString("Nombre de usuario: \n");
            String password = PideDatos.pideString("Password: \n");
            Empleado empleadoLogin = new Empleado();
            empleadoLogin.setNombreusuario(nombreUsuario);
            empleadoLogin.setPassword(password);
            //LE PASO EL OBJETO EMPLEADO CON LOS DATOS DEL USUARIO Y SI EXISTE ESE USUARIO CON ESE PASSWORD DEVUELVE TRUE         
            login = comprobarLoginEmpleado(empleadoLogin);
        } catch (IOException ex) {
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(login){
            m.menuLogueado();
        }else{
            System.out.println("Nombre de usuario o contraseña incorrectos. \n");
        }
    }
    
    public void modificarEmpleado(){
        try {Empleado empleadoModificar = new Empleado();
            //PIDO EL NOMBRE DE USUARIO PARA MODIFICAR. EL NOMBRE DE USUARIO CONSIDERO NO MODIFICABLE Y LA CONTRASEÑA
            //TAMPOCO POR CAMBIARSE CON OTRA OPCIÓN DEL MENÚ.
            String nombreUsuarioEmpleadoParaModificar = PideDatos.pideString("Introduzca el nombre de usuario del empleado a modificar: \n");
            empleadoModificar.setNombreusuario(nombreUsuarioEmpleadoParaModificar);
            //COMPRUEBO SI EXISTE
            boolean existe = comprobarExistenciaEmpleado(empleadoModificar);
            
            if(!existe){
                System.out.println("El usuario que indica no existe con lo que no se puede modificar. Introduzca un nombre de usuario nuevo.\n");
            }else{empleadoModificar = seleccionarEmpleado(empleadoModificar);
                empleadoModificar.setNombrecompleto(PideDatos.pideString("Introduzca el nuevo nombre completo: \n"));
                empleadoModificar.setTelefono(PideDatos.pideString("Introduzca el nuevo número de teléfono: \n"));
                Session session = NewHibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.update(empleadoModificar);
                tx.commit();
                session.close();
                System.out.println("Empleado modificado con éxito.\n");
            }
        } catch (IOException ex) {
            System.out.println("Error modificando empleado " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cambiarContraseña(){
        try {Empleado empleadoModificarContraseña = new Empleado();
            String nombreUsuarioEmpleadoParaModificarPassword = PideDatos.pideString("Introduzca el nombre de usuario del empleado para modificar password: \n");
            empleadoModificarContraseña.setNombreusuario(nombreUsuarioEmpleadoParaModificarPassword);
            //COMPRUEBO SI EXISTE
            boolean existe = comprobarExistenciaEmpleado(empleadoModificarContraseña);
            if(existe){
                empleadoModificarContraseña = seleccionarEmpleado(empleadoModificarContraseña);
                empleadoModificarContraseña.setPassword(PideDatos.pideString("Introduzca la nueva contraseña: \n"));
                Session session = NewHibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.update(empleadoModificarContraseña);
                tx.commit();
                session.close();
                System.out.println("Contraseña modificada con éxito.\n");
            }else{
                System.out.println("El usuario al que quiere cambiar la contraseña no existe.\n");
            }
            
        } catch (IOException ex) {
            System.out.println("Error modificando cambiando contraseña " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void eliminarEmpleado(){
        String nombreUsuarioEliminar = null;
        try {
            nombreUsuarioEliminar = PideDatos.pideString("Introduzca el nombre de usuario del empleado a eliminar: \n");
        } catch (IOException ex) {
            System.out.println("Error eliminando empleado " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        Empleado empleadoEliminar = new Empleado();
        empleadoEliminar.setNombreusuario(nombreUsuarioEliminar);
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
    
    public void insertarIncidencia(){
        Incidencia nuevaIncidencia = crearIncidencia();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(nuevaIncidencia);
        tx.commit();
        session.close();
        System.out.println("Incidencia insertada con éxito.\n");
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
        String c = "select e from Empleado e WHERE nombreusuario = '" + e.getNombreusuario() + "'";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        Empleado empleadoSeleccionado = (Empleado) results.get(0);
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
        String c = "select i from Incidencia i WHERE idincidencia = '" + i.getIdincidencia()+ "'";
        Query q = session.createQuery(c);
        List results = q.list();
        session.close();
        Incidencia incidenciaSeleccionada = (Incidencia) results.get(0);
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
                //nuevaIncidencia.setIdincidencia(PideDatos.pideEntero("Introduzca una id de la incidencia: \n"));
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
            nuevaIncidencia.setFechahora(PideDatos.pideString("Introduzca la fecha y hora de la incidencia: \n"));
            nuevaIncidencia.setDetalle(PideDatos.pideString("Introduzca la descripción de la incidencia: \n"));
            nuevaIncidencia.setTipo(FuncionesVariadas.solicitarPrioridad());
        } catch (IOException ex) {
            System.out.println("Error insertando incidencia " + ex.getMessage());
            Logger.getLogger(IncidenciasORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nuevaIncidencia;
    }
}
