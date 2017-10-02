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
    static String ubicacionW = "c:/";
    static String ubicacionL = "/media/devtroce/java/";
    
    static String carpeta = "Trabajo";
    static String SO = System.getProperty("os.name");
    
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
        if(SO.startsWith("Windows")){
            File directorio = new File(ubicacionW+ carpeta ); 
            directorio.mkdir(); 
        }else{
            File directorio = new File(ubicacionL+ carpeta ); 
            directorio.mkdir();
        }
        
    }
    public void abrirDirecPre() throws IOException{
        if(SO.startsWith("Windows")){
            File directorio = new File(ubicacionW+ carpeta );
            Desktop.getDesktop().open(directorio);
        }else{
            File directorio = new File(ubicacionL+ carpeta );
            Desktop.getDesktop().open(directorio);
        }
        
    }
    public static File selectDirecPre(){
        File f = null;
        if(SO.startsWith("Windows")){
            String url = ubicacionW+carpeta;
            f = new File(url);
        }else{
            String url = ubicacionL+carpeta;
            f = new File(url);
        }
        return f;
    }
}
