/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.ENT;

import Cl.Burgos.Trabajos.EXP.ExpCliente;
import Cl.Burgos.Trabajos.FUN.Metodos;

/**
 *
 * @author march
 */
public class ClCliente {
    private int idCliente;
    private String nombre;
    private String celular;
    private String correo;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) throws Exception {
        if(idCliente<0){
            throw new ExpCliente(ExpCliente.ERR_idCliente);
        }else{
            this.idCliente = idCliente;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if(nombre.length()<=3 || nombre.length()>=26){
            throw new ExpCliente(ExpCliente.ERR_nombre);
        }else{
            this.nombre = nombre;
        }
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) throws Exception {
        if(celular.length()<9){
            throw new ExpCliente(ExpCliente.ERR_celular);
        }else{
            this.celular = celular;
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) throws Exception {
        if(!Metodos.validateEmail(correo)){
            throw new ExpCliente(ExpCliente.ERR_correo);
        }else{
            this.correo = correo;
        }
    }

    public ClCliente(int idCliente) throws Exception {
        setIdCliente(idCliente);
    }

    public ClCliente(String nombre, String celular, String correo) throws Exception {
        setNombre(nombre);
        setCelular(celular);
        setCorreo(correo);
    }

    public ClCliente(int idCliente, String nombre, String celular, String correo) throws Exception {
        setIdCliente(idCliente);
        setNombre(nombre);
        setCelular(celular);
        setCorreo(correo);
    }
    
    
    
    
}
