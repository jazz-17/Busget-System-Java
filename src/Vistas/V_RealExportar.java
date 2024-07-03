package Vistas;

import Modelo.Conexion.PbConexion;
import Modelo.Message.Mensaje1;
import java.awt.Color;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class V_RealExportar extends javax.swing.JFrame {

    int xMouse, yMouse,fila = 0;
    boolean Exportar = false;
    PbConexion conexion = new PbConexion();
    Connection con;
    Statement ps;
    ResultSet rs;
    String sql;
    Object[] o;
 
    public V_RealExportar(String sql) {
        initComponents();
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        init(sql);
        iniciarEvento(this);
    }

    public void init(String sql){
        this.sql = sql;
    }
    
    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.valueOf(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
    
    public void MainExcel(String nombre){
        String archivo = "Datos.xls";
        File datos = new File(archivo);
        if(!datos.exists()){
            JOptionPane.showMessageDialog(null, "Archivo base no encontrado, Creando...", "Espere", JOptionPane.INFORMATION_MESSAGE);
            CrearExcel(nombre, datos);
            JOptionPane.showMessageDialog(null, "Creado con Éxito", "OK", JOptionPane.INFORMATION_MESSAGE);
        } else ModificarExcel(nombre, datos);
    }
    
    public void CrearExcel(String nombre, File archivo){
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet(nombre);
        
        try {
            ResultSet aux; 
            con = conexion.conectar();
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            aux = rs;
            fila = aux.getMetaData().getColumnCount();
            o = new Object[fila];
            int contador = 1;
           
           Row NombreDatos = sheet.createRow(0);
           CellStyle Principal = book.createCellStyle();
           CellStyle Numeros = book.createCellStyle();
           Numeros.setDataFormat(book.createDataFormat().getFormat("0"));
           Principal.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
           Principal.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (int i = 0; i < o.length; i++) {
                o[i]=aux.getMetaData().getColumnName(i+1);
            }
      
            for(int i = 0; i < o.length; i++){
                Cell Datos = NombreDatos.createCell(i);      
                Datos.setCellValue((String)o[i]);
                Datos.setCellStyle(Principal);
            }

            while(aux.next()){
                Row EscribirDatos = sheet.createRow(contador);
                for(int i = 0; i < o.length; i++){
                    Cell Escribir = EscribirDatos.createCell(i);
                    Object Dato = rs.getObject(o[i]+"");
                    if(isNumeric(Dato.toString())){ 
                    Escribir.setCellValue(Integer.parseInt(Dato.toString()));
                    Escribir.setCellStyle(Numeros);
                    } else Escribir.setCellValue(rs.getObject(o[i]+"").toString());
                }
                contador++;
            }
            
        } 
        catch (SQLException e) {
            showMessage1("<html>ERROR "+e+" <html>");
        } 
        
        sheet.autoSizeColumn(1);
        
            try {
                FileOutputStream fileout = new FileOutputStream(archivo);
                book.write(fileout);
                fileout.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(V_Exportar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(V_Exportar.class.getName()).log(Level.SEVERE, null, ex);
            }  
        
    }
    
    public void ModificarExcel(String hoja,File archivo){
        try {
            FileInputStream filein = new FileInputStream(archivo);
            
            Workbook book = new HSSFWorkbook(filein);
            int CantidadHoja = book.getNumberOfSheets();
            
            for(int i = 0; i < CantidadHoja; i++){
                String NombreHoja = book.getSheetName(i);
                if(NombreHoja == null ? hoja == null : NombreHoja.equals(hoja)){
                    JOptionPane.showMessageDialog(null, "Hoja ya existente", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            Sheet nuevaHoja = book.createSheet(hoja);
            
            try {
                ResultSet aux; 
                con = conexion.conectar();
                ps = con.createStatement();
                rs = ps.executeQuery(sql);
                aux = rs;
                fila = aux.getMetaData().getColumnCount();
                o = new Object[fila];
                int contador = 1;
           
                Row NombreDatos = nuevaHoja.createRow(0);
                CellStyle Principal = book.createCellStyle();
                CellStyle Numeros = book.createCellStyle();
                Numeros.setDataFormat(book.createDataFormat().getFormat("0"));
                Principal.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
                Principal.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                for (int i = 0; i < o.length; i++) {
                    o[i]=aux.getMetaData().getColumnName(i+1);
                }

                for(int i = 0; i < o.length; i++){
                    Cell Datos = NombreDatos.createCell(i);      
                    Datos.setCellValue((String)o[i]);
                    Datos.setCellStyle(Principal);
                }
                    
                while(aux.next()){
                    Row EscribirDatos = nuevaHoja.createRow(contador);
                    for(int i = 0; i < o.length; i++){
                        Cell Escribir = EscribirDatos.createCell(i);
                        Object Dato = rs.getObject(o[i]+"");
                        if(isNumeric(Dato.toString())){ 
                        Escribir.setCellValue(Integer.parseInt(Dato.toString()));
                        Escribir.setCellStyle(Numeros);
                        } else Escribir.setCellValue(rs.getObject(o[i]+"").toString());
                    }
                    contador++;
                }
            
            } 
            catch (SQLException e) {
                showMessage1("<html>ERROR "+e+" <html>");
            } 
        
        nuevaHoja.autoSizeColumn(1);
        
        try {
                FileOutputStream fileout = new FileOutputStream("test.xls");
                book.write(fileout);
                fileout.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(V_Exportar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(V_Exportar.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(V_RealExportar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(V_Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }  
       
        File archivo2 = new File("test.xls");
        archivo.delete();
        archivo2.renameTo(archivo);   
        
        JOptionPane.showMessageDialog(null, "Hoja Creada con Éxito", "OK", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void iniciarEvento(JFrame frame){
        header.initEvent(this, panelBackground1,1);
    }
   
    private boolean showMessage1(String message){
        Mensaje1 obj = new Mensaje1(Frame.getFrames()[1],true);
        obj.showMessage(message);
        return obj.isAceptar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground1 = new Modelo.Design.PanelBackground();
        header = new Modelo.Design.Headboard();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonCircle1 = new Modelo.Design.ButtonCircle();
        ArchivoExport = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBackground1.setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Exportar");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        jLabel2.setText("Nombre de la Hoja");

        buttonCircle1.setBackground(new java.awt.Color(102, 204, 0));
        buttonCircle1.setForeground(new java.awt.Color(255, 255, 255));
        buttonCircle1.setText("EXPORTAR");
        buttonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCircle1ActionPerformed1(evt);
            }
        });

        javax.swing.GroupLayout panelBackground1Layout = new javax.swing.GroupLayout(panelBackground1);
        panelBackground1.setLayout(panelBackground1Layout);
        panelBackground1Layout.setHorizontalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addGroup(panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBackground1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel1))
                    .addGroup(panelBackground1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBackground1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(ArchivoExport, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBackground1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(buttonCircle1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panelBackground1Layout.setVerticalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(ArchivoExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(buttonCircle1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCircle1ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCircle1ActionPerformed1
        String Export = ArchivoExport.getText().trim();
        System.out.println("a");
        if(!Export.isEmpty()){
            MainExcel(Export);
        } else JOptionPane.showMessageDialog(null, "No hay nombre de archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_buttonCircle1ActionPerformed1

    
    /**
     * @param args the command line arguments
     */
    //public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(V_Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(V_Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(V_Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(V_Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new V_Consultas().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ArchivoExport;
    private Modelo.Design.ButtonCircle buttonCircle1;
    private Modelo.Design.Headboard header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private Modelo.Design.PanelBackground panelBackground1;
    // End of variables declaration//GEN-END:variables
}
