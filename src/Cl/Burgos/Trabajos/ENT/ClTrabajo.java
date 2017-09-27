/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.ENT;

import Cl.Burgos.Trabajos.EXP.ExpTrabajo;
import java.sql.Date;

/**
 *
 * @author march
 */
public class ClTrabajo {
    private int idTrabajo;
    private String nombre;
    private String descripcion;
    private int abono;
    private int pago;
    private int total;
    private Date fechaIngreso;
    private Date fechaTermino;
    private int idCliente;

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(int idTrabajo) throws Exception {
        if(idTrabajo<=0){
            throw new ExpTrabajo(ExpTrabajo.ERR_idTrabajo);
        }else{
            this.idTrabajo = idTrabajo;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if(nombre.length()<=3 || nombre.length()>=46){
            throw new ExpTrabajo(ExpTrabajo.ERR_nombre);
        }else{
            this.nombre = nombre;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) throws Exception {
        if(descripcion.length()==0){
            this.descripcion = descripcion;
        }else{
            if(descripcion.length()<=3 || descripcion.length()>=201){
                throw new ExpTrabajo(ExpTrabajo.ERR_descripcion);
            }else{
                this.descripcion = descripcion;
            }
        }
    }

    public int getAbono() {
        return abono;
    }

    public void setAbono(int abono) throws Exception {
        if(abono==0){
            this.abono = abono;
        }else{
            if(abono<=999 || abono>=99999999){
                throw new ExpTrabajo(ExpTrabajo.ERR_abono);
            }else{
                this.abono = abono;
            }
        }
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) throws Exception {
        if(pago==0){
            this.pago = pago;
        }else{
            if(pago<=999 || pago>=99999999){
                throw new ExpTrabajo(ExpTrabajo.ERR_pago);
            }else{
                this.pago = pago;
            }
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) throws Exception {
        if(total==0){
            this.total = total;
        }else{
            if(total<=999 || total>=99999999){
                throw new ExpTrabajo(ExpTrabajo.ERR_total);
            }else{
                this.total = total;
            }
        }
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    java.sql.Date fa = new java.sql.Date(new java.util.Date().getTime());
    public void setFechaIngreso(Date fechaIngreso) throws Exception {
        if(fechaIngreso==null){
            throw new ExpTrabajo(ExpTrabajo.ERR_fechaIngreso);
        }else{
            if(fechaIngreso.before(fa)){
                this.fechaIngreso=fechaIngreso;
            }else{
                throw new ExpTrabajo(ExpTrabajo.ERR_fechaIngreso);
            }
        }
        
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) throws Exception {
        if(fechaTermino==null){
            this.fechaTermino = fechaTermino;
        }else{
            if(fechaTermino.before(fa)){
                this.fechaTermino = fechaTermino;
            }else{
                throw new ExpTrabajo(ExpTrabajo.ERR_fechaTermino);
            }
        }
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) throws Exception {
        if(idCliente==0){
            throw new ExpTrabajo(ExpTrabajo.ERR_idCliente);
        }else{
            this.idCliente = idCliente;
        }
    }

    public ClTrabajo(int idTrabajo) throws Exception {
        setIdTrabajo(idTrabajo);
    }

    public ClTrabajo(String nombre, String descripcion, int abono, int pago, int total, Date fechaIngreso, Date fechaTermino, int idCliente) throws Exception {
        setNombre(nombre);
        setDescripcion(descripcion);
        setAbono(abono);
        setPago(pago);
        setTotal(total);
        setFechaIngreso(fechaIngreso);
        setFechaTermino(fechaTermino);
        setIdCliente(idCliente);
    }

    public ClTrabajo(int idTrabajo, String nombre, String descripcion, int abono, int pago, int total, Date fechaIngreso, Date fechaTermino, int idCliente) throws Exception {
        setIdTrabajo(idTrabajo);
        setNombre(nombre);
        setDescripcion(descripcion);
        setAbono(abono);
        setPago(pago);
        setTotal(total);
        setFechaIngreso(fechaIngreso);
        setFechaTermino(fechaTermino);
        setIdCliente(idCliente);
    }
    
    
}
