/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesPojo;

import enums.TipoEvento;

/**
 *
 * @author USUARIO
 */
public class Historial {
    
    private int idEvento;
    private TipoEvento tipoEvento;
    private String fechaHora;
    private int idEmpleado;

    public Historial() {
    }

    public Historial(int idEvento, TipoEvento tipoEvento, String fechaHora, int idEmpleado) {
        this.idEvento = idEvento;
        this.tipoEvento = tipoEvento;
        this.fechaHora = fechaHora;
        this.idEmpleado = idEmpleado;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "Historial{" + "Id Evento= " + idEvento + " -- Tipo de Evento= " + tipoEvento + " -- Fecha/Hora= " + fechaHora + " -- Id Empleado= " + idEmpleado + '}';
    }
    
    
}
