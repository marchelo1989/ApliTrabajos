/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.FUN;

import javax.swing.JTextField;

/**
 *
 * @author march
 */
public class Diseño {
    String plomo = "#818181";
    String negro = "#000000";

    public void Mensaje(JTextField letra, String Mensaje, int tamaño) {
        if (tamaño == 0) {
            letra.setText(Mensaje);
            letra.setForeground(java.awt.Color.decode(plomo));
        }
    }

    public void Clic(JTextField letra, String Mensaje) {
        if (letra.getText().equals(Mensaje)) {
            letra.setText("");
            letra.setForeground(java.awt.Color.decode(negro));
        }

    }
}
