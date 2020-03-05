package clasesPojo;
// Generated 04-mar-2020 12:52:09 by Hibernate Tools 4.3.1



/**
 * Incidencia generated by hbm2java
 */
public class Incidencia  implements java.io.Serializable {


     private int idincidencia;
     private Empleado empleadoByOrigen;
     private Empleado empleadoByDestino;
     private String fechahora;
     private String detalle;
     private String tipo;

    public Incidencia() {
    }

    public Incidencia(int idincidencia, Empleado empleadoByOrigen, Empleado empleadoByDestino, String fechahora, String detalle, String tipo) {
       this.idincidencia = idincidencia;
       this.empleadoByOrigen = empleadoByOrigen;
       this.empleadoByDestino = empleadoByDestino;
       this.fechahora = fechahora;
       this.detalle = detalle;
       this.tipo = tipo;
    }
   
    public int getIdincidencia() {
        return this.idincidencia;
    }
    
    public void setIdincidencia(int idincidencia) {
        this.idincidencia = idincidencia;
    }
    public Empleado getEmpleadoByOrigen() {
        return this.empleadoByOrigen;
    }
    
    public void setEmpleadoByOrigen(Empleado empleadoByOrigen) {
        this.empleadoByOrigen = empleadoByOrigen;
    }
    public Empleado getEmpleadoByDestino() {
        return this.empleadoByDestino;
    }
    
    public void setEmpleadoByDestino(Empleado empleadoByDestino) {
        this.empleadoByDestino = empleadoByDestino;
    }
    public String getFechahora() {
        return this.fechahora;
    }
    
    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }
    public String getDetalle() {
        return this.detalle;
    }
    
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    

}


