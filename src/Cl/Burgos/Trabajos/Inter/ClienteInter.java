/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.Inter;

import Cl.Burgos.Trabajos.ENT.ClCliente;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author march
 */
public interface ClienteInter {
    public boolean sqlInsert(ClCliente cliente);
    public boolean sqlUpdate(ClCliente cliente);
    public boolean sqlDelete(ClCliente cliente);
    public void listarClientes(long intDesde ,long intCuantos,DefaultTableModel tablaClientes,String strBusqueda );
    public void llenarCombo(JComboBox Combo);
    public long cuantosClientes(String strBusqueda);
    public String[] buscarIdCliente(ClCliente cliente);
}
