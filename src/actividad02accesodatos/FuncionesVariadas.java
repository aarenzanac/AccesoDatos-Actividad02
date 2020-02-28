/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad02accesodatos;

import enums.Prioridad;
import PideDatos.PideDatos;
import java.io.IOException;

/**
 *
 * @author USUARIO
 */
public class FuncionesVariadas {
    
    String prioridadString = null;
    
    public static Prioridad crearPrioridad(String prioridadString){
        Prioridad p = null;
        if(prioridadString.equals("URGENTE")){
        return p.URGENTE;
        }else{
        return p.NORMAL;
        }
    }
    
    public static Prioridad solicitarPrioridad() throws IOException{
        String prioridadString = null;
        do{
            prioridadString = PideDatos.pideString("Introduzca la prioridad (NORMAL O URGENTE): \n").toUpperCase();
            if(!prioridadString.equals("URGENTE") || !prioridadString.equals("NORMAL")){
                System.out.println("Valor incorrecto. Debe ser Normal o Urgente.\n");
            }
        }while(!prioridadString.equals("URGENTE") || !prioridadString.equals("NORMAL"));
        
        return crearPrioridad(prioridadString);
    }
}
