/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Modelo.Conexion.PbConexion;
import Modelo.Message.Mensaje1;
import java.awt.Color;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class V_Exportar extends javax.swing.JPanel {
    
    PbConexion conexion = new PbConexion();
    Connection con;
    Statement ps;
    ResultSet rs;
    String sql;
    int fila;
    Object[] o;
 
    public V_Exportar(String sql) {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        init(sql);
    }

    public void init(String sql){
        this.sql = sql;
    }
    
    public void CrearExcel(String nombre){
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Export");
        
        try {
            FileOutputStream fileout = new FileOutputStream(nombre + ".xls");
            book.write(fileout);
            fileout.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(V_Exportar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(V_Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ResultSet aux; 
            con = conexion.conectar();
            ps=con.createStatement();
            rs = ps.executeQuery(sql);
            aux=rs;
            fila=aux.getMetaData().getColumnCount();
            o=new Object[fila];
            
            Row NombreDatos = sheet.createRow(0);
            for(int i = 0; i < o.length; i++){
                Cell Datos;
                Datos = NombreDatos.createCell(i);
                
                o[i] = aux.getMetaData().getColumnName(i+1);
                Datos.setCellValue((String) o[i]);
            }
            
            
        } catch (SQLException e) {
            showMessage1("<html>ERROR "+e+" <html>");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        header = new Modelo.Design.Headboard();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ArchivoExport = new javax.swing.JTextArea();
        buttonCircle1 = new Modelo.Design.ButtonCircle();

        setOpaque(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Exportar");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        jLabel2.setText("Nombre del Archivo");

        ArchivoExport.setColumns(20);
        ArchivoExport.setRows(5);
        ArchivoExport.setMaximumSize(new java.awt.Dimension(13, 20));
        jScrollPane1.setViewportView(ArchivoExport);

        buttonCircle1.setBackground(new java.awt.Color(102, 204, 0));
        buttonCircle1.setForeground(new java.awt.Color(255, 255, 255));
        buttonCircle1.setText("EXPORTAR");
        buttonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCircle1ActionPerformed1(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(buttonCircle1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(buttonCircle1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCircle1ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCircle1ActionPerformed1
     String Export = ArchivoExport.getText().trim();
     
     if(!Export.isEmpty()){
         CrearExcel(Export);
     } else JOptionPane.showMessageDialog(null, "No hay nombre de archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_buttonCircle1ActionPerformed1


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ArchivoExport;
    private Modelo.Design.ButtonCircle buttonCircle1;
    private Modelo.Design.Headboard header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private boolean showMessage1(String message) {
        Mensaje1 obj = new Mensaje1(Frame.getFrames()[1],true);
        obj.showMessage(message);
        return obj.isAceptar();
    }
}
