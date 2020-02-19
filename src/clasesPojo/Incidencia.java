/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesPojo;

import enums.Prioridad;

/**
 *
 * @author USUARIO
 */
public class Incidencia {
    
    private int idIncidencia;
    private String fechaHora;
    private String origen;
    private String destino;
    private String detalle;
    private Prioridad tipo;
    
    public Incidencia(){}

    public Incidencia(int idIncidencia, String fechaHora, String origen, String destino, String detalle) {
        this.idIncidencia = idIncidencia;
        this.fechaHora = fechaHora;
        this.origen = origen;
        this.destino = destino;
        this.detalle = detalle;
    }
    
    public int getIdIncidencia(){
        return idIncidencia;
    }
    
    public String getFechaHora() {
        return fechaHora;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getDetalle() {
        return detalle;
    }

    public Prioridad getTipo() {
        return tipo;
    }
    
    public void setIdIncidencia(int idIncidencia){
        this.idIncidencia = idIncidencia;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void setTipo(Prioridad tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Incidencia{" + "IdIncidencia= " + idIncidencia + " -- Fecha/Hora= " + fechaHora + " -- Origen= " + origen + " -- Destino= " + destino + " -- Detalle= " + detalle + " -- Tipo= " + tipo + '}';
    }

        
}
