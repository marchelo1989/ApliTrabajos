/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.FUN;;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author march
 */
public class CrearWorld {
    private XWPFDocument document=new XWPFDocument();
    static String SO = System.getProperty("os.name");
    public void crear(String[] t, String[] c){
        javax.swing.filechooser.FileNameExtensionFilter filtroWord=new FileNameExtensionFilter("Microsoft Word 2016", "docx");
        final JFileChooser miArchivo=new JFileChooser();
        miArchivo.setFileFilter(filtroWord);
        String userDir = System.getProperty("user.home");
        if(SO.startsWith("Windows")){
            miArchivo.setCurrentDirectory(new File(userDir +"/Desktop"));
        }else{
            miArchivo.setCurrentDirectory(new File(userDir +"/Escritorio"));
        }
        miArchivo.setSelectedFile(new File("Documento"));
        int aceptar=miArchivo.showSaveDialog(null);
        miArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(aceptar==JFileChooser.APPROVE_OPTION){
            File fileWord=miArchivo.getSelectedFile();
            String nombre=fileWord.getName();
            if(nombre.indexOf('.')==-1){
                nombre+=".docx";
                fileWord=new File(fileWord.getParentFile(), nombre);
            }
            try {
                FileOutputStream output=new FileOutputStream(fileWord);
                //FileOutputStream output=new FileOutputStream("documento de prueba.docx");

                XWPFParagraph paragraphTitulo=document.createParagraph();
                XWPFRun runTitulo=paragraphTitulo.createRun();

                paragraphTitulo.setAlignment(ParagraphAlignment.CENTER);
                runTitulo.setBold(true);
                runTitulo.setFontSize(28);
                runTitulo.setUnderline(UnderlinePatterns.NONE);
                runTitulo.setText("Este es el titulo");
                runTitulo.setColor("000000");
                runTitulo.addBreak();

                XWPFParagraph paragraph=document.createParagraph();
                XWPFRun run=paragraph.createRun();
                run.setFontSize(12);
                run.setText("Datos del Cliente");
                run.addBreak();
                run.setText("Nombre: "+c[1]);
                run.setText(" Celular: "+c[2]);
                run.setText(" Correo: "+c[3]);
                run.addBreak();
                run.setText("Datos Del Trabajo");
                run.addBreak();
                run.setText("Nombre: "+t[1]);
                run.addBreak();
                run.setText(" Descripcion: "+t[2]);
                run.addBreak();
                run.setText(" Abono: "+t[3]);
                run.setText(" Pago: "+t[4]);
                run.setText(" Total: "+t[5]);
                run.addBreak();
                run.setText(" Fecha de Inicio: "+FormatoFecha.fDateS(FormatoFecha.mostrarFechaD(t[6])));
                run.setText(" Fecha de Termino: "+t[7]);
                
                document.write(output);
                output.close();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage().toString());
            }
            
            finally{
                try {
                    if(System.getProperty("os.name").equals("Linux")){
//                        Runtime.getRuntime().exec("libreoffice"+fileWord.getAbsolutePath());
                        File objetofile = new File (fileWord.getAbsolutePath());
                        Desktop.getDesktop().open(objetofile);
                    }
                    else{
//                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+fileWord.getAbsolutePath());
                        File objetofile = new File (fileWord.getAbsolutePath());
                        Desktop.getDesktop().open(objetofile);
                    }
                }
                catch (IOException ex) {
                    Logger.getLogger(CrearWorld.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
