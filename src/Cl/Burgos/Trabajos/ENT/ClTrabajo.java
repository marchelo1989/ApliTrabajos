/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.ENT;

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

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAbono() {
        return abono;
    }

    public void setAbono(int abono) {
        this.abono = abono;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        if(fechaIngreso!=null){
            this.fechaIngreso = fechaIngreso;
        }else{
            this.fechaIngreso = null;
        }
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ClTrabajo(int idTrabajo) {
        setIdTrabajo(idTrabajo);
    }

    public ClTrabajo(String nombre, String descripcion, int abono, int pago, int total, Date fechaIngreso, Date fechaTermino, int idCliente) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setAbono(abono);
        setPago(pago);
        setTotal(total);
        setFechaIngreso(fechaIngreso);
        setFechaTermino(fechaTermino);
        setIdCliente(idCliente);
    }

    public ClTrabajo(int idTrabajo, String nombre, String descripcion, int abono, int pago, int total, Date fechaIngreso, Date fechaTermino, int idCliente) {
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
