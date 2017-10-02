/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.DAO;

import Cl.Burgos.Trabajos.BD.BD;
import Cl.Burgos.Trabajos.BD.Log;
import Cl.Burgos.Trabajos.ENT.ClTrabajo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author march
 */
public class DAOTrabajos {
    /** Insertar datos*/
    public boolean sqlInsert(ClTrabajo ct)throws Exception, SQLException{
            String stSql="";
            if(ct.getFechaTermino()==null){
                stSql  = "CALL ProTrabajoInsertar(";
            stSql += "'" + ct.getNombre()+ "'";
            stSql += ",'" + ct.getDescripcion()+ "'";
            stSql += ",'" + ct.getAbono()+ "'";
            stSql += ",'" + ct.getPago()+ "'";
            stSql += ",'" + ct.getTotal()+ "'";
            stSql += ",'" + ct.getFechaIngreso()+ "'";
            stSql += "," + ct.getFechaTermino()+ "";
            stSql += ",'" + ct.getIdCliente()+ "'";
            stSql += " )";
            }else{
                stSql  = "CALL ProTrabajoInsertar(";
            stSql += "'" + ct.getNombre()+ "'";
            stSql += ",'" + ct.getDescripcion()+ "'";
            stSql += ",'" + ct.getAbono()+ "'";
            stSql += ",'" + ct.getPago()+ "'";
            stSql += ",'" + ct.getTotal()+ "'";
            stSql += ",'" + ct.getFechaIngreso()+ "'";
            stSql += ",'" + ct.getFechaTermino()+ "'";
            stSql += ",'" + ct.getIdCliente()+ "'";
            stSql += " )";
            }
            return BD.getInstance().sqlEjecutar(stSql);
		}
    /** Modificar datos de trabajo*/
    public boolean sqlUpdate(ClTrabajo ct){		
        try {
            String stSql="";
            if(ct.getFechaTermino()==null){
                stSql =  "Call ProTrabajosModificar (";
                stSql += "'" + ct.getNombre()+ "'";
                stSql += ",'" + ct.getDescripcion()+ "'";
                stSql += ",'" + ct.getAbono()+ "'";
                stSql += ",'" + ct.getPago()+ "'";
                stSql += ",'" + ct.getTotal()+ "'";
                stSql += ",'" + ct.getFechaIngreso()+ "'";
                stSql += "," + ct.getFechaTermino()+ "";
                stSql += ",'" + ct.getIdCliente()+ "'";
                stSql += ",'" + ct.getIdTrabajo()+"'";
                stSql += ")";
            }else{
                stSql =  "Call ProTrabajosModificar (";
                stSql += "'" + ct.getNombre()+ "'";
                stSql += ",'" + ct.getDescripcion()+ "'";
                stSql += ",'" + ct.getAbono()+ "'";
                stSql += ",'" + ct.getPago()+ "'";
                stSql += ",'" + ct.getTotal()+ "'";
                stSql += ",'" + ct.getFechaIngreso()+ "'";
                stSql += ",'" + ct.getFechaTermino()+ "'";
                stSql += ",'" + ct.getIdCliente()+ "'";
                stSql += ",'" + ct.getIdTrabajo()+"'";
                stSql += ")";
            }
            return BD.getInstance().sqlEjecutar(stSql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTrabajos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /** Elimina un trabajo por idTrabajo*/
    public boolean sqlDelete(ClTrabajo ct){		
        try {
            String stSql =  "call ProTrabajoEliminar(";
            stSql += " '" + ct.getIdTrabajo()+ "')";
            return BD.getInstance().sqlEjecutar(stSql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTrabajos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /** Lista Los trabajos por IdCliente */
    public void listarTrabjosIdC(long intDesde ,long intCuantos,DefaultTableModel tablaClientes,String strBusqueda,int id ){
        String strConsulta;
        String datos[]=new String [9];
      
//        strConsulta="call ProTrabajosListarIdC("+intDesde+","+intCuantos+",'"+strBusqueda+"',"+id+");";
        strConsulta="SELECT idTrabajo, nombre, descripcion, abono, pago, total, fechaInicio, fechaTermino, Cliente_idCliente"
                + " FROM `Trabajo` WHERE Cliente_idCliente="+id+";";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         
         while(rs.next()){
              //System.out.println(res.getString("Nombres"));
              datos[0]=rs.getString(1);
              datos[1]=rs.getString(2);
              datos[2]=rs.getString(3);
              datos[3]=rs.getString(4);
              datos[4]=rs.getString(5);
              datos[5]=rs.getString(6);
              datos[6]=rs.getString(7);
              datos[7]=rs.getString(8);
              datos[8]=rs.getString(9);
              //datos[8]=rs.getString("idCliente");
              
              tablaClientes.addRow(datos);
         }
         rs.close();
          }catch(SQLException e){
         System.out.println(e);
         Log.log(e.getMessage());
          }
    }
    
    public void listarTrabjosP(long intDesde ,long intCuantos,DefaultTableModel tablaClientes,String strBusqueda ){
        String strConsulta;
        String datos[]=new String [6];
      
//        strConsulta="call ProTrabajosListarIdC("+intDesde+","+intCuantos+",'"+strBusqueda+"',"+id+");";
        strConsulta="SELECT c.nombre,t.idTrabajo,t.nombre,t.abono,t.pago,t.total FROM Trabajo as t JOIN Cliente as c on c.idCliente = t.Cliente_idCliente where abono+pago!=total or total=0";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         
         while(rs.next()){
              //System.out.println(res.getString("Nombres"));
              datos[0]=rs.getString(1);
              datos[1]=rs.getString(2);
              datos[2]=rs.getString(3);
              datos[3]=rs.getString(4);
              datos[4]=rs.getString(5);
              datos[5]=rs.getString(6);
              
              tablaClientes.addRow(datos);
         }
         rs.close();
          }catch(SQLException e){
         System.out.println(e);
         Log.log(e.getMessage());
          }
    }
    /** Cuenta los registros de tabla trabajo*/
    public long cuantosTrabajos(int id){
        String strConsulta;
        long cuantos = 0;
        strConsulta="call ProTrabajosCuantos('" +id+"');";
      
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         
         while(rs.next()){
              //System.out.println(res.getString("Nombres"));
              cuantos=Long.valueOf(rs.getString("cuantos"));
       
              return cuantos;
              
         }
         rs.close();
          }catch(SQLException e){
              Log.log(e.getMessage());
         System.out.println(e);
         System.out.println(strConsulta);
         return cuantos;
          }
       System.out.println(strConsulta);
        return cuantos;
       
        }
    /** busca y lista todos los trabajos por el id de trabajo*/
    public String[] listartrabajosIdT(ClTrabajo ct){
        String strConsulta;
        String datos[]=new String [9];
        
        strConsulta="call ProTrabajoListarIdT("+ct.getIdTrabajo()+");";
     
      
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         
         
         while(rs.next()){
              //System.out.println(res.getString("Nombres"));
              datos[0]=rs.getString("idTrabajo");
              datos[1]=rs.getString("nombre");
              datos[2]=rs.getString("descripcion");
              datos[3]=rs.getString("abono");
              datos[4]=rs.getString("pago");
              datos[5]=rs.getString("total");
              datos[6]=rs.getString("fechaInicio");
              datos[7]=rs.getString("fechaTermino");
              datos[8]=rs.getString("Cliente_idCliente");
              
              rs.close();
              return datos;
              
         }
         rs.close();
          }catch(SQLException e){
         System.out.println(e);
         Log.log(e.getMessage());
 
         return datos;
          }
      
        return datos;
    }
}
