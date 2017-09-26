/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.Main;

import Cl.Burgos.Trabajos.ENT.ClCliente;
import Cl.Burgos.Trabajos.ENT.ClTrabajo;
import Cl.Burgos.Trabajos.FUN.Directorio;
import Cl.Burgos.Trabajos.FUN.FormatoFecha;
import Cl.Burgos.Trabajos.GUI.FrHome;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author march
 */
public class ApliTrabajos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        Directorio.crearDirecPre();
        FrHome home = new FrHome();
        home.setVisible(true);
    }
    
}
