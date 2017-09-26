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
public class ExpCliente extends Exception {
    public static final int ERR_idCliente=1;
    public static final int ERR_nombre=2;
    public static final int ERR_apellido=3;
    public static final int ERR_celular=4;
    public static final int ERR_correo=5;
    
    
    public ExpCliente (int error) throws Exception{
        switch(error){
            case ERR_idCliente:
                throw new Exception("El ID deve ser mayor que 0");
            case ERR_nombre:
                throw new Exception("El Nombre debe estar entre 4...25 caracteres");
            case ERR_apellido:
                throw new Exception("El Apellido debe estar entre 4...25 caracteres");
            case ERR_celular:
                throw new Exception("El Celular debe ser de 9 digitos");
            case ERR_correo:
                throw new Exception("El Correo es incorrecto");
                default:
                    throw new Exception("Error desconocido "+ error);
        }
    }
}
