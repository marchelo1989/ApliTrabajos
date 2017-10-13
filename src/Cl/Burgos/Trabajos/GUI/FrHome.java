/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Trabajos.GUI;

import Cl.Burgos.Trabajos.BD.Log;
import Cl.Burgos.Trabajos.DAO.DAOCliente;
import Cl.Burgos.Trabajos.DAO.DAOTrabajos;
import Cl.Burgos.Trabajos.ENT.ClCliente;
import Cl.Burgos.Trabajos.ENT.ClTrabajo;
import Cl.Burgos.Trabajos.FUN.CrearWorld;
import Cl.Burgos.Trabajos.FUN.FormatoFecha;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author march
 */
public class FrHome extends javax.swing.JFrame {

    DAOCliente dAOCliente = new DAOCliente();
    DAOTrabajos dAOTrabajos = new DAOTrabajos();
    int idCliente;
    long lngNumPaginas;
    
    /**
     * Creates new form FrHome
     */
    public FrHome() {
        initComponents();
        txtNumReg.setText("9");
        txtPagina.setText("1");
        
        this.setLocationRelativeTo(null);
        this.setResizable(false); 
        this.setTitle("Trabajos");
        //Bloquear casillas
        txtIdT.setEnabled(false);
        btnAgregarT.setEnabled(false);
        btnModificarT.setEnabled(false);
        btnEliminarT.setEnabled(false);
        jTabTrabajos.setVisible(false);
        btnImprimirReporte.setEnabled(false);
        //Bloqueo de Jtable
        this.jTabTrabajos.disable();
        
        //para Cargar el ComboBox
        this.jcbClientes.setSelectedItem(null);
        this.jcbClientes.removeAllItems();
        dAOCliente.llenarCombo(this.jcbClientes);
        
        jPanel1.setOpaque(false);
        jPanel2.setOpaque(false);
        jPanel3.setOpaque(false);
        jPanel4.setOpaque(false);
        jPanel5.setOpaque(false);
        jPanel6.setOpaque(false);
        
        String url="/Cl/Burgos/Trabajos/IMG/";
        setIconImage(new ImageIcon(getClass().getResource(url+"Trabajo_icono.png")).getImage());
        ((JPanel)getContentPane()).setOpaque(false);
        ImageIcon MyImgCustom =new ImageIcon(this.getClass().getResource(url+"fondo2.jpg"));
        JLabel fondo= new JLabel();
        
        fondo.setIcon(MyImgCustom);
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0,0,MyImgCustom.getIconWidth(),MyImgCustom.getIconHeight());
    }
    
    public void Limpiar(){
        txtIdT.setText("");
        txtPagina.setText("1");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtAbono.setText("");
        txtPago.setText("");
        txtTotal.setText("");
        txtFechaI.setDate(null);
        txtFechaT.setDate(null);
        btnAgregarT.setEnabled(false);
        btnModificarT.setEnabled(false);
        btnEliminarT.setEnabled(false);
        btnImprimirReporte.setEnabled(false);
    }
    public ClTrabajo idTrabajo() throws Exception{
        ClTrabajo clTrabajo = new ClTrabajo(Integer.parseInt(campoNume(txtIdT.getText())));
        return clTrabajo;
    }
    public ClCliente idCliente() throws Exception{
        ClCliente cliente = new ClCliente(idCliente);
        return cliente;
    }
    public ClTrabajo IntTrabajo() throws Exception{
        ClTrabajo clTrabajo = new ClTrabajo(txtNombre.getText(), txtDescripcion.getText(), 
                Integer.parseInt(campoNume(txtAbono.getText())), Integer.parseInt(campoNume(txtPago.getText())), 
                Integer.parseInt(campoNume(txtTotal.getText())),campoFecha(txtFechaI.getDate()) , 
                campoFecha(txtFechaT.getDate()), idCliente);
        return clTrabajo;
    }
    public ClTrabajo allTrabajo() throws Exception{
        ClTrabajo clTrabajo = new ClTrabajo(Integer.parseInt(txtIdT.getText()), txtNombre.getText(), txtDescripcion.getText(), 
                Integer.parseInt(campoNume(txtAbono.getText())), Integer.parseInt(campoNume(txtPago.getText())), 
                Integer.parseInt(campoNume(txtTotal.getText())),campoFecha(txtFechaI.getDate()) , 
                campoFecha(txtFechaT.getDate()), idCliente);
        return clTrabajo;
    }
    
    public String campoNume(String nu){
        String numero;
        if(nu.length()==0){
            numero="0";
        }else{
            numero=nu;
        }
        return numero;
    }
    public java.sql.Date campoFecha(java.util.Date fc){
        java.sql.Date fecha;
        if(fc==null){
            fecha = null;
        }else{
            fecha = new java.sql.Date(fc.getTime());
        }
        return fecha;
    }
    
    public void defineTablaTrabajos(String strBusqueda,long DesdeHoja){
        
        
        long lngRegistros=1;
        long lngDesdeRegistro;
        
        //DEFINIMOS LA TABLA MODELO
        DefaultTableModel tablaClientes = new DefaultTableModel();
        
        //LE AGREGAMOS EL TITULO DE LAS COLUMNAS DE LA TABLA EN UN ARREGLO
        String strTitulos[]={"ID","NOMBRE","DESCRIPCION","ABONO","PAGO","TOTAL","FECHA INICIO","FECHA TERMINO"};
        
        //LE ASIGNAMOS LAS COLUMNAS AL MODELO CON LA CADENA DE ARRIBA
        tablaClientes.setColumnIdentifiers(strTitulos);
        
        //LE ASIGNAMOS EL MODELO DE ARRIBA AL JTABLE 
        this.jTabTrabajos.setModel(tablaClientes);
        
                    //AHORA A LEER LOS DATOS
        
        //ASIGNAMOS CUANTOS REGISTROS POR HOJA TRAEREMOS
        lngRegistros=(Long.valueOf(this.txtNumReg.getText()));
        
        //ASIGNAMOS DESDE QUE REGISTRO TRAERA LA CONSULTA SQL
        lngDesdeRegistro=(DesdeHoja*lngRegistros)-lngRegistros;
        
        //INSTANCEAMOS LA CLASE CLIENTE
//        DAOPc classCliente= new DAOPc();
        
        //LEEMOS LA CLASE CLIENTE MANDANDOLE LOS PARAMETROS
        dAOTrabajos.listarTrabjosIdC(lngDesdeRegistro, (Long.valueOf(this.txtNumReg.getText())),tablaClientes,strBusqueda,idCliente);
        
        //LE PONEMOS EL RESULTADO DE LA CONSULA AL JTABLE
        this.jTabTrabajos.setModel(tablaClientes);
        
        //ASIGNAMOS LOS VALORES A LA PAGINACION
        lngRegistros = dAOTrabajos.cuantosTrabajos(idCliente);
        lngNumPaginas= (lngRegistros/ (Long.valueOf( this.txtNumReg.getText())))+1;
        this.jlblTotalPaginas.setText(" De " + ( lngNumPaginas));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarC = new javax.swing.JButton();
        btnBuscarC = new javax.swing.JButton();
        jcbClientes = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabTrabajos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIdT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtAbono = new javax.swing.JTextField();
        txtPago = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        txtFechaI = new com.toedter.calendar.JDateChooser();
        txtFechaT = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jlblNumReg = new javax.swing.JLabel();
        txtNumReg = new javax.swing.JTextField();
        jlblNumReg2 = new javax.swing.JLabel();
        txtPagina = new javax.swing.JTextField();
        jlblTotalPaginas = new javax.swing.JLabel();
        cmdAtras = new javax.swing.JButton();
        cmdSiguiente1 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        lblBuscar = new javax.swing.JLabel();
        cmdBuscar = new javax.swing.JButton();
        btnImprimirReporte = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnAgregarT = new javax.swing.JButton();
        btnModificarT = new javax.swing.JButton();
        btnEliminarT = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Trabajos"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes"));

        jLabel1.setText("Nombre:");

        btnAgregarC.setText("Agregar Cliente");
        btnAgregarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCActionPerformed(evt);
            }
        });

        btnBuscarC.setText("Buscar");
        btnBuscarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCActionPerformed(evt);
            }
        });

        jcbClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jcbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarC)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarC)
                    .addComponent(btnAgregarC))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Listar de Trabajos"));

        jTabTrabajos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTabTrabajos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabTrabajosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabTrabajos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Trabajos"));

        jLabel2.setText("ID:");

        jLabel3.setText("Nombre:*");

        jLabel4.setText("Descripcion:");

        jLabel5.setText("Abono:");

        jLabel6.setText("Pago:");

        jLabel7.setText("Total:");

        jLabel8.setText("Fecha Inicio:*");

        jLabel9.setText("Fecha Termino:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        txtAbono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbonoKeyTyped(evt);
            }
        });

        txtPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPagoKeyTyped(evt);
            }
        });

        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6))
                                    .addComponent(txtFechaI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel9)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(txtFechaT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 35, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8))
                    .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jlblNumReg.setText("Registros");

        jlblNumReg2.setText("Pagina ");

        jlblTotalPaginas.setText("Pagina ");

        cmdAtras.setText("<<");
        cmdAtras.setActionCommand("");
        cmdAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAtrasActionPerformed(evt);
            }
        });

        cmdSiguiente1.setText(">>");
        cmdSiguiente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSiguiente1ActionPerformed(evt);
            }
        });

        lblBuscar.setText("Buscar");

        cmdBuscar.setText("Buscar All");
        cmdBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBuscarActionPerformed(evt);
            }
        });

        btnImprimirReporte.setText("Imprimir ");
        btnImprimirReporte.setActionCommand("");
        btnImprimirReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblNumReg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumReg, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdAtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblNumReg2)
                .addGap(2, 2, 2)
                .addComponent(txtPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jlblTotalPaginas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSiguiente1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(lblBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdBuscar)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(btnImprimirReporte)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNumReg)
                    .addComponent(txtNumReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblNumReg2)
                    .addComponent(txtPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblTotalPaginas)
                    .addComponent(cmdAtras)
                    .addComponent(cmdSiguiente1)
                    .addComponent(lblBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImprimirReporte)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Aciones de Trabajo"));

        btnAgregarT.setText("Agregar");
        btnAgregarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTActionPerformed(evt);
            }
        });

        btnModificarT.setText("Modificar");
        btnModificarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTActionPerformed(evt);
            }
        });

        btnEliminarT.setText("Eliminar");
        btnEliminarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTActionPerformed(evt);
            }
        });

        jButton1.setText("Pagos Pendientes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnAgregarT)
                    .addComponent(btnModificarT)
                    .addComponent(jButton1)
                    .addComponent(btnEliminarT))
                .addGap(29, 29, 29))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCActionPerformed
        // TODO add your handling code here:
        FrClientes clientes = new FrClientes();
        clientes.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAgregarCActionPerformed

    private void jTabTrabajosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabTrabajosMouseClicked
        // TODO add your handling code here:
        int fila;
        this.txtIdT.getText();
        String[] datosCliente =new String[9];
        fila = this.jTabTrabajos.rowAtPoint(evt.getPoint());
        
        if (fila > -1){
            try {
                this.txtIdT.setText(String.valueOf(jTabTrabajos.getValueAt(fila, 0)));
                
                datosCliente=dAOTrabajos.listartrabajosIdT(idTrabajo());
                
                this.txtIdT.setText(datosCliente[0]);
                this.txtNombre.setText(datosCliente[1]);
                this.txtDescripcion.setText(datosCliente[2]);
                this.txtAbono.setText(datosCliente[3]);
                this.txtPago.setText(datosCliente[4]);
                this.txtTotal.setText(datosCliente[5]);
                Date di = FormatoFecha.mostrarFechaYMD(datosCliente[6]);
                this.txtFechaI.setDate(di);
                if(datosCliente[7].equals("0000-00-00")){
                    this.txtFechaT.setDate(null);
                }else{
                    Date dt = FormatoFecha.mostrarFechaYMD(datosCliente[7]);
                    this.txtFechaT.setDate(dt);
                }
                
                this.btnEliminarT.setEnabled(true);
                this.btnModificarT.setEnabled(true);
                this.btnImprimirReporte.setEnabled(true);
                
//                if(Long.valueOf( datosCliente[0])>0){
//                    this.btnActualizarPC.setLabel("Actualizar");
//                }
            } catch (Exception ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
                Log.log(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jTabTrabajosMouseClicked

    private void cmdAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAtrasActionPerformed
        long lngValor=0;
        if(1<Long.valueOf( this.txtPagina.getText())){
            lngValor=Long.valueOf( this.txtPagina.getText())-1;
            this.txtPagina.setText(String.valueOf(lngValor));
            defineTablaTrabajos(Integer.toString(idCliente),lngValor);
        }
    }//GEN-LAST:event_cmdAtrasActionPerformed

    private void cmdSiguiente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSiguiente1ActionPerformed
        long lngValor=0;
        if(lngNumPaginas>Long.valueOf( this.txtPagina.getText())){
            lngValor=Long.valueOf( this.txtPagina.getText())+1;
            this.txtPagina.setText(String.valueOf(lngValor));
            defineTablaTrabajos(Integer.toString(idCliente),lngValor);
        }
    }//GEN-LAST:event_cmdSiguiente1ActionPerformed

    private void cmdBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarActionPerformed
        //        defineTablaClientesAll(this.txtBuscar.getText(),Long.valueOf(this.txtPagina.getText()),idCliente);
    }//GEN-LAST:event_cmdBuscarActionPerformed

    private void btnBuscarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCActionPerformed
        // TODO add your handling code here:
        Limpiar();
        String r = this.jcbClientes.getSelectedItem().toString().substring(0, 4).toString();
        idCliente = Integer.parseInt(r.replace("#", ""));
        jTabTrabajos.setVisible(true);
        this.btnImprimirReporte.setEnabled(false);
        defineTablaTrabajos("", idCliente);
    }//GEN-LAST:event_btnBuscarCActionPerformed

    private void btnAgregarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTActionPerformed
        try {
            // TODO add your handling code here:
            if(!dAOTrabajos.sqlInsert(IntTrabajo())){
                JOptionPane.showMessageDialog(null, "Trabajo No Agregado");
            }else{
                JOptionPane.showMessageDialog(null, "Trabajo Agregado");
                String r = this.jcbClientes.getSelectedItem().toString().substring(0, 4).toString();
                idCliente = Integer.parseInt(r.replace("#", ""));
                defineTablaTrabajos(Integer.toString(idCliente),1);
                Limpiar();
            }
        } catch (Exception ex) {
            Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Log.log(ex.getMessage());
        }
    }//GEN-LAST:event_btnAgregarTActionPerformed

    private void btnModificarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTActionPerformed
        try {
            // TODO add your handling code here:
            if(!dAOTrabajos.sqlUpdate(allTrabajo())){
                JOptionPane.showMessageDialog(null, "Trabajo No Modificado");
            }else{
                JOptionPane.showMessageDialog(null, "Trabajo Modificado");
                String r = this.jcbClientes.getSelectedItem().toString().substring(0, 4).toString();
                idCliente = Integer.parseInt(r.replace("#", ""));
                defineTablaTrabajos(Integer.toString(idCliente),1);
                Limpiar();
            }
        } catch (Exception ex) {
            Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Log.log(ex.getMessage());
        }
    }//GEN-LAST:event_btnModificarTActionPerformed

    private void btnEliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTActionPerformed
        try {
            // TODO add your handling code here:
            if(!dAOTrabajos.sqlDelete(idTrabajo())){
                JOptionPane.showMessageDialog(null, "Trabajo No Eliminado");
            }else{
                JOptionPane.showMessageDialog(null, "Trabajo Eliminado");
                String r = this.jcbClientes.getSelectedItem().toString().substring(0, 4).toString();
                idCliente = Integer.parseInt(r.replace("#", ""));
                defineTablaTrabajos(Integer.toString(idCliente),1);
                Limpiar();
            }
        } catch (Exception ex) {
            Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Log.log(ex.getMessage());
        }
    }//GEN-LAST:event_btnEliminarTActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        Character c = evt.getKeyChar();
        if(!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_BACK_SPACE){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo Letras y Espacios");
            txtNombre.setCursor(null);
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        btnAgregarT.setEnabled(txtNombre.getText().length() != 0);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtAbonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoKeyTyped
        // TODO add your handling code here:
        Character c = evt.getKeyChar();
        if(!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo Numeros");
            txtAbono.setCursor(null);
        }
    }//GEN-LAST:event_txtAbonoKeyTyped

    private void txtPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoKeyTyped
        // TODO add your handling code here:
        Character c = evt.getKeyChar();
        if(!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo Numeros");
            txtPago.setCursor(null);
        }
    }//GEN-LAST:event_txtPagoKeyTyped

    private void txtTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyTyped
        // TODO add your handling code here:
        Character c = evt.getKeyChar();
        if(!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo Numeros");
            txtTotal.setCursor(null);
        }
    }//GEN-LAST:event_txtTotalKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FrPendientes fp=new FrPendientes();
        fp.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnImprimirReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirReporteActionPerformed
        // TODO add your handling code here:
        String[] c = null;
        String[] t = null;
        try {
           c= dAOCliente.buscarIdCliente(idCliente());
           t =  dAOTrabajos.listartrabajosIdT(idTrabajo());
           CrearWorld world = new CrearWorld();
           world.crear(t, c);
        } catch (Exception ex) {
            Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimirReporteActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarC;
    private javax.swing.JButton btnAgregarT;
    private javax.swing.JButton btnBuscarC;
    private javax.swing.JButton btnEliminarT;
    private javax.swing.JButton btnImprimirReporte;
    private javax.swing.JButton btnModificarT;
    private javax.swing.JButton cmdAtras;
    private javax.swing.JButton cmdBuscar;
    private javax.swing.JButton cmdSiguiente1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabTrabajos;
    private javax.swing.JComboBox<String> jcbClientes;
    private javax.swing.JLabel jlblNumReg;
    private javax.swing.JLabel jlblNumReg2;
    private javax.swing.JLabel jlblTotalPaginas;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JTextField txtAbono;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtDescripcion;
    private com.toedter.calendar.JDateChooser txtFechaI;
    private com.toedter.calendar.JDateChooser txtFechaT;
    private javax.swing.JTextField txtIdT;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumReg;
    private javax.swing.JTextField txtPagina;
    private javax.swing.JTextField txtPago;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
