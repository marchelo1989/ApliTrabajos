/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.EXP;

/**
 *
 * @author march
 */
public class ExpTrabajo extends Exception {
//    int idTrabajo, String nombre, String descripcion, int abono, int pago, int total, Date fechaIngreso, Date fechaTermino, int idCliente
    public static final int ERR_idTrabajo=1;
    public static final int ERR_nombre=2;
    public static final int ERR_descripcion=3;
    public static final int ERR_abono=4;
    public static final int ERR_pago=5;
    public static final int ERR_total=6;
    public static final int ERR_fechaIngreso=7;
    public static final int ERR_fechaTermino=8;
    public static final int ERR_idCliente=9;
    
    public ExpTrabajo (int error) throws Exception{
        switch(error){
            case ERR_idTrabajo:
                throw new Exception("El ID Trabajo deve ser mayor que 0");
            case ERR_nombre:
                throw new Exception("El Nombre debe estar entre 4...45 caracteres");
            case ERR_descripcion:
                throw new Exception("La Descripcion debe estar entre 4...200 caracteres");
            case ERR_abono:
                throw new Exception("El Abono debe estar entre 4...7 digitos");
            case ERR_pago:
                throw new Exception("El Pago debe estar entre 4...7 digitos");
            case ERR_total:
                throw new Exception("El Total debe estar entre 4...7 digitos");
            case ERR_fechaIngreso:
                throw new Exception("La Fecha de Inicio no puede estar vacia \ny debe ser menor o igual a la fecha actual");
            case ERR_fechaTermino:
                throw new Exception("La Fecha de Termino debe ser menor a la fecha actual");
            case ERR_idCliente:
                throw new Exception("El ID Cliente deve ser mayor que 0");
                default:
                    throw new Exception("Error desconocido "+ error);
        }
    }
}
