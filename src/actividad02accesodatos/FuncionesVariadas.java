/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad02accesodatos;

import enums.Prioridad;
import PideDatos.PideDatos;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class FuncionesVariadas {
    
    //NO UTILIZADO PUESTO QUE HIBERNATE HA MARCADO LA PRIORIDAD COMO STRING
    /*String prioridadString = null;
    
    public static Prioridad crearPrioridad(String prioridadString){
        Prioridad p = null;
        if(prioridadString.equals("URGENTE")){
        return p.URGENTE;
        }else{
        return p.NORMAL;
        }
    }*/
    
    public static String solicitarPrioridad() throws IOException{
        String prioridadString = null;
        do{
            prioridadString = PideDatos.pideString("Introduzca la prioridad (NORMAL O URGENTE): \n").toUpperCase();
            if(!prioridadString.equals("URGENTE") && !prioridadString.equals("NORMAL")){
                System.out.println("Valor incorrecto. Debe ser Normal o Urgente.\n");
            }
        }while(!prioridadString.equals("URGENTE") && !prioridadString.equals("NORMAL"));
                 
        String prioridadFormateada = "";
        
        String[] split = prioridadString.split("");
        for(int i = 0; i < split.length; i++){
            if(i==0){
                prioridadFormateada += split[i].toUpperCase();
            }else{
                prioridadFormateada += split[i].toLowerCase();
            }
        }
                
        return prioridadFormateada;
    }
}
