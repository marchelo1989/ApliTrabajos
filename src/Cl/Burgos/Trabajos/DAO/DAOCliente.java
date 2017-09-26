/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.DAO;

import Cl.Burgos.Trabajos.BD.BD;
import Cl.Burgos.Trabajos.BD.Log;
import Cl.Burgos.Trabajos.ENT.ClCliente;
import Cl.Burgos.Trabajos.Inter.ClienteInter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author march
 */
public class DAOCliente implements ClienteInter{
/**  Insertar Clientes */
    @Override
    public boolean sqlInsert(ClCliente cliente) {
        try {
            String stSql  = "CALL ProClienteInsertar(";
            stSql += "'" + cliente.getNombre()+ "'";
            stSql += ",'" + cliente.getCelular()+ "'";
            stSql += ",'" + cliente.getCorreo()+ "'";
            stSql += " )";
            return BD.getInstance().sqlEjecutar(stSql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
/**  Actualizar Clientes */
    @Override
    public boolean sqlUpdate(ClCliente cliente) {
        try {
            String stSql =  "Call ProClienteModificar (";
            stSql += "'"+cliente.getNombre()+"',";
            stSql += "'"+cliente.getCelular()+"',";
            stSql += "'"+cliente.getCorreo()+"',";
            stSql += "'"+cliente.getIdCliente()+"'";
            stSql += ")";
            return BD.getInstance().sqlEjecutar(stSql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
/**  Eliminar Clientes */
    @Override
    public boolean sqlDelete(ClCliente cliente) {
        try {
            String stSql =  "call ProClienteEliminar(";
            stSql += " '" + cliente.getIdCliente()+ "')";
            return BD.getInstance().sqlEjecutar(stSql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
/** Lista Cliente */
    @Override
    public void listarClientes(long intDesde, long intCuantos, DefaultTableModel tablaClientes, String strBusqueda) {
        String strConsulta;
        String datos[]=new String [4];
      
        strConsulta="call ProClienteListarAll("+intDesde+","+intCuantos+",'"+strBusqueda+"');";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         
         while(rs.next()){
              datos[0]=rs.getString(1);
              datos[1]=rs.getString(2);
              datos[2]=rs.getString(3);
              datos[3]=rs.getString(4);
              
              tablaClientes.addRow(datos);
         }
         rs.close();
          }catch(SQLException e){
         System.out.println(e);
          }
    }
/** Cuenta cuantos Clientes hay */
    @Override
    public long cuantosClientes(String strBusqueda) {
        String strConsulta;
        long cuantos = 0;
        strConsulta="call ProClienteCuantos('" +strBusqueda +"');";
      
        try{
            ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
            while(rs.next()){
                cuantos=Long.valueOf(rs.getString("cuantos"));

                return cuantos;
             }
             rs.close();
        }catch(SQLException e){
              System.out.println(e);
         return cuantos;
        }
        return cuantos;
    }
/** Busca Cliente por id */
    @Override
    public String[] buscarIdCliente(ClCliente cliente) {
        String strConsulta;
        String datos[]=new String [4];
        
        strConsulta="call ProClienteBuscarID("+cliente.getIdCliente()+");";
        
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         
         
         while(rs.next()){
              datos[0]=rs.getString("idCliente");
              datos[1]=rs.getString("nombre");
              datos[2]=rs.getString("celular");
              datos[3]=rs.getString("correo");
                      
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
/** Cargar el ComboBox */
    @Override
    public void llenarCombo(JComboBox Combo) {
        String strConsulta;
        String datos[]=new String [2];
        DecimalFormat formato = new DecimalFormat("0000");
        String result;
        int intDesde=0;
        int intCuantos=1000;
        String strBusqueda="";
        strConsulta="call ProClienteListarAll("+intDesde+","+intCuantos+",'"+strBusqueda+"');";
      
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         
         while(rs.next()){
             
//              datos[0]=String.format("%1$04d",rs.getInt("idCliente"),rs.getInt("idCliente")).toString();
              result=formato.format( rs.getInt("idCliente")).toString();
              datos[0]=result.replace("0","#");
              datos[1]=rs.getString("nombre");
              
              Combo.addItem(datos[0]+" "+datos[1]);
            }
            rs.close();
            }catch(SQLException e){
                System.out.println(e);
                }
    }
    
}
