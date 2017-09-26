/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.FUN;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author march
 */
public class Directorio {
    public String selecDirectrorio(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(null);
//        System.out.println(fileChooser.getSelectedFile());
        //String url = "c:/Users/march/Desktop/";
        String url = fileChooser.getSelectedFile().toString();
        //url=url.replace('\\', '/');
        return url;
    }
    public static void abrirArchivo(String url) throws IOException{
        File objetofile = new File (url);
        Desktop.getDesktop().open(objetofile);
    }
    
    public static void crearDirecPre(){
        String OrigenCarpeta = "Reparaciones"; 
        File directorio = new File("c:/"+ OrigenCarpeta ); 
        directorio.mkdir(); 
    }
    public void abrirDirecPre() throws IOException{
        String OrigenCarpeta = "Reparaciones"; 
        File directorio = new File("c:/"+ OrigenCarpeta );
        Desktop.getDesktop().open(directorio);
    }
    public static File selectDirecPre(){
        String url = "c:/Reparaciones";
        File f = new File(url);
        return f;
    }
}
