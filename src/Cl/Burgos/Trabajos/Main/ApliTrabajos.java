/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.Main;

import Cl.Burgos.Trabajos.FUN.Directorio;
import Cl.Burgos.Trabajos.GUI.FrHome;

/**
 *
 * @author march
 */
public class ApliTrabajos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Directorio.crearDirecPre();
        FrHome home = new FrHome();
        home.setVisible(true);
    }
    
}
