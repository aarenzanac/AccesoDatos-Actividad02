/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad02accesodatos;

import clasesPojo.Empleado;
import clasesPojo.Historial;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author USUARIO
 */
public class TestORM {

    public static void main(String[] args) {
        Menus menu = new Menus();
        menu.menuPrincipal();
        System.exit(0);

        /*Session session = NewHibernateUtil.getSessionFactory().openSession();
        String e = "select count(h.empleado), h.empleado from Historial h where h.tipo = 'U' group by h.empleado order by count(h.empleado) desc";
        String p = "select new map(empleado as e, count(*) as n)from Historial h where tipo='U'group by empleado e order by n desc";
        String sql = "SELECT COUNT(*) AS cantidad, empleado FROM historial WHERE tipo='U' GROUP BY empleado order by cantidad desc";
        Query q = session.createQuery(e);
        List<Object[]> results = q.list();
        System.out.println("Nº Incidencias ----- Nombre Usuario");
        for(Object[] datos : results){
            Empleado em = (Empleado) datos[1];
            System.out.println("     " + datos[0] + " ------------- " + em.getNombrecompleto());
        }*/
    }
}
