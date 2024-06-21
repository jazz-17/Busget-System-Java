
package Vistas;
import Custom.SelectTabs;

public class V_Partida extends javax.swing.JPanel {
    
    public V_Partida() {
        initComponents();
        setOpaque(false);
        init();
    }
    
    public void init(){
        tablaPartida_I.fixTable(jScrollPane2);
        tablaPartida_I.getColumnModel().getColumn(0).setPreferredWidth(50);  // codPartida
        tablaPartida_I.getColumnModel().getColumn(1).setPreferredWidth(150); // Descripción
        tablaPartida_I.getColumnModel().getColumn(2).setPreferredWidth(100); // codigoDummy
        tablaPartida_I.getColumnModel().getColumn(3).setPreferredWidth(120); // Tab
        tablaPartida_I.getColumnModel().getColumn(4).setPreferredWidth(120);  // Elemento
        tablaPartida_I.getColumnModel().getColumn(5).setPreferredWidth(30);  // Vigente
        tablaPartida_E.fixTable(jScrollPane3);
        tablaPartida_E.getColumnModel().getColumn(0).setPreferredWidth(50);  // codPartida
        tablaPartida_E.getColumnModel().getColumn(1).setPreferredWidth(150); // Descripción
        tablaPartida_E.getColumnModel().getColumn(2).setPreferredWidth(100); // codigoDummy
        tablaPartida_E.getColumnModel().getColumn(3).setPreferredWidth(120); // Tab
        tablaPartida_E.getColumnModel().getColumn(4).setPreferredWidth(120);  // Elemento
        tablaPartida_E.getColumnModel().getColumn(5).setPreferredWidth(30);  // Vigente
    }   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        actualizaTabla = new javax.swing.JMenuItem();
        nuevo = new javax.swing.JMenuItem();
        materialTabbed1 = new Modelo.Design.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        panelShadow7 = new Modelo.Design.PanelShadow();
        vigente_I = new Modelo.Design.Combobox();
        tUniMed_I = new Modelo.Design.Combobox();
        eUniMed_I = new Modelo.Design.Combobox();
        textAreaScroll2 = new Modelo.Design.TextAreaScroll();
        desPartida_I = new Modelo.Design.TextArea();
        panelBackground3 = new Modelo.Design.PanelBackground();
        jLabel2 = new javax.swing.JLabel();
        panelShadow3 = new Modelo.Design.PanelShadow();
        btt_Registrar_I = new Modelo.Design.Button();
        btt_Eliminar_I = new Modelo.Design.Button();
        btt_Actualizar_I = new Modelo.Design.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPartida_I = new Modelo.DesignTable.Tabla();
        jPanel2 = new javax.swing.JPanel();
        panelShadow8 = new Modelo.Design.PanelShadow();
        vigente_E = new Modelo.Design.Combobox();
        tUniMed_E = new Modelo.Design.Combobox();
        eUniMed_E = new Modelo.Design.Combobox();
        textAreaScroll3 = new Modelo.Design.TextAreaScroll();
        desPartida_E = new Modelo.Design.TextArea();
        panelBackground4 = new Modelo.Design.PanelBackground();
        jLabel3 = new javax.swing.JLabel();
        panelShadow4 = new Modelo.Design.PanelShadow();
        btt_Registrar_E = new Modelo.Design.Button();
        btt_Eliminar_E = new Modelo.Design.Button();
        btt_Actualizar_E = new Modelo.Design.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPartida_E = new Modelo.DesignTable.Tabla();

        popupMenu.setLabel("Acciones");

        actualizaTabla.setText("Actualizar");
        popupMenu.add(actualizaTabla);

        nuevo.setText("Nuevo");
        popupMenu.add(nuevo);

        materialTabbed1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setOpaque(false);

        panelShadow7.setComponentPopupMenu(popupMenu);

        vigente_I.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Vigente", "No Vigente" }));
        vigente_I.setSelectedIndex(0);
        vigente_I.setLabeText("Estado:");

        tUniMed_I.setLabeText("Tabs Unidad Medida: ");

        eUniMed_I.setLabeText("Elementos Unidad Medida: ");

        textAreaScroll2.setLabelText("Descripción Partida:");

        desPartida_I.setBorder(null);
        desPartida_I.setColumns(20);
        desPartida_I.setForeground(new java.awt.Color(40, 40, 40));
        desPartida_I.setRows(5);
        textAreaScroll2.setViewportView(desPartida_I);

        javax.swing.GroupLayout panelShadow7Layout = new javax.swing.GroupLayout(panelShadow7);
        panelShadow7.setLayout(panelShadow7Layout);
        panelShadow7Layout.setHorizontalGroup(
            panelShadow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(vigente_I, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tUniMed_I, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(eUniMed_I, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        panelShadow7Layout.setVerticalGroup(
            panelShadow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelShadow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vigente_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tUniMed_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(eUniMed_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panelBackground3.setBackground(new java.awt.Color(54, 87, 185));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DATOS DE SOBRE LAS PARTIDAS INGRESO");

        javax.swing.GroupLayout panelBackground3Layout = new javax.swing.GroupLayout(panelBackground3);
        panelBackground3.setLayout(panelBackground3Layout);
        panelBackground3Layout.setHorizontalGroup(
            panelBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBackground3Layout.setVerticalGroup(
            panelBackground3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelShadow3.setComponentPopupMenu(popupMenu);

        btt_Registrar_I.setBackground(new java.awt.Color(240, 240, 240));
        btt_Registrar_I.setText("Registrar");

        btt_Eliminar_I.setBackground(new java.awt.Color(240, 240, 240));
        btt_Eliminar_I.setText("Eliminar");

        btt_Actualizar_I.setBackground(new java.awt.Color(240, 240, 240));
        btt_Actualizar_I.setText("Actualizar");

        tablaPartida_I.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodPartida", "Descripción","Codigo",  "TUniMed", "EUniMed", "Vigente"
            }
        ));
        tablaPartida_I.setComponentPopupMenu(popupMenu);
        jScrollPane2.setViewportView(tablaPartida_I);

        javax.swing.GroupLayout panelShadow3Layout = new javax.swing.GroupLayout(panelShadow3);
        panelShadow3.setLayout(panelShadow3Layout);
        panelShadow3Layout.setHorizontalGroup(
            panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
                    .addGroup(panelShadow3Layout.createSequentialGroup()
                        .addComponent(btt_Actualizar_I, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btt_Registrar_I, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btt_Eliminar_I, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        panelShadow3Layout.setVerticalGroup(
            panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btt_Actualizar_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_Registrar_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_Eliminar_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelShadow3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBackground3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBackground3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShadow7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("INGRESO", jPanel1);

        jPanel2.setOpaque(false);

        panelShadow8.setComponentPopupMenu(popupMenu);

        vigente_E.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Vigente", "No Vigente" }));
        vigente_E.setSelectedIndex(0);
        vigente_E.setLabeText("Estado:");

        tUniMed_E.setLabeText("Tabs Unidad Medida: ");

        eUniMed_E.setLabeText("Elementos Unidad Medida: ");

        textAreaScroll3.setLabelText("Descripción Partida:");

        desPartida_E.setBorder(null);
        desPartida_E.setColumns(20);
        desPartida_E.setForeground(new java.awt.Color(40, 40, 40));
        desPartida_E.setRows(5);
        textAreaScroll3.setViewportView(desPartida_E);

        javax.swing.GroupLayout panelShadow8Layout = new javax.swing.GroupLayout(panelShadow8);
        panelShadow8.setLayout(panelShadow8Layout);
        panelShadow8Layout.setHorizontalGroup(
            panelShadow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(vigente_E, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tUniMed_E, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(eUniMed_E, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        panelShadow8Layout.setVerticalGroup(
            panelShadow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelShadow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vigente_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tUniMed_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(eUniMed_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panelBackground4.setBackground(new java.awt.Color(54, 87, 185));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DATOS DE SOBRE LAS PARTIDAS EGRESO");

        javax.swing.GroupLayout panelBackground4Layout = new javax.swing.GroupLayout(panelBackground4);
        panelBackground4.setLayout(panelBackground4Layout);
        panelBackground4Layout.setHorizontalGroup(
            panelBackground4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBackground4Layout.setVerticalGroup(
            panelBackground4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelShadow4.setComponentPopupMenu(popupMenu);

        btt_Registrar_E.setBackground(new java.awt.Color(240, 240, 240));
        btt_Registrar_E.setText("Registrar");

        btt_Eliminar_E.setBackground(new java.awt.Color(240, 240, 240));
        btt_Eliminar_E.setText("Eliminar");

        btt_Actualizar_E.setBackground(new java.awt.Color(240, 240, 240));
        btt_Actualizar_E.setText("Actualizar");

        tablaPartida_E.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodPartida", "Descripción", "Codigo",  "TUniMed", "EUniMed", "Vigente"
            }
        ));
        tablaPartida_E.setComponentPopupMenu(popupMenu);
        jScrollPane3.setViewportView(tablaPartida_E);

        javax.swing.GroupLayout panelShadow4Layout = new javax.swing.GroupLayout(panelShadow4);
        panelShadow4.setLayout(panelShadow4Layout);
        panelShadow4Layout.setHorizontalGroup(
            panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
                    .addGroup(panelShadow4Layout.createSequentialGroup()
                        .addComponent(btt_Actualizar_E, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btt_Registrar_E, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btt_Eliminar_E, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        panelShadow4Layout.setVerticalGroup(
            panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btt_Actualizar_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_Registrar_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_Eliminar_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelShadow4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBackground4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBackground4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShadow8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("EGRESO", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    public javax.swing.JMenuItem actualizaTabla;
    public Modelo.Design.Button btt_Actualizar_E;
    public Modelo.Design.Button btt_Actualizar_I;
    public Modelo.Design.Button btt_Eliminar_E;
    public Modelo.Design.Button btt_Eliminar_I;
    public Modelo.Design.Button btt_Registrar_E;
    public Modelo.Design.Button btt_Registrar_I;
    public Modelo.Design.TextArea desPartida_E;
    public Modelo.Design.TextArea desPartida_I;
    public Modelo.Design.Combobox eUniMed_E;
    public Modelo.Design.Combobox eUniMed_I;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private Modelo.Design.MaterialTabbed materialTabbed1;
    public javax.swing.JMenuItem nuevo;
    private Modelo.Design.PanelBackground panelBackground3;
    private Modelo.Design.PanelBackground panelBackground4;
    private Modelo.Design.PanelShadow panelShadow3;
    private Modelo.Design.PanelShadow panelShadow4;
    private Modelo.Design.PanelShadow panelShadow7;
    private Modelo.Design.PanelShadow panelShadow8;
    public javax.swing.JPopupMenu popupMenu;
    public Modelo.Design.Combobox tUniMed_E;
    public Modelo.Design.Combobox tUniMed_I;
    public Modelo.DesignTable.Tabla tablaPartida_E;
    public Modelo.DesignTable.Tabla tablaPartida_I;
    private Modelo.Design.TextAreaScroll textAreaScroll2;
    private Modelo.Design.TextAreaScroll textAreaScroll3;
    public Modelo.Design.Combobox<String> vigente_E;
    public Modelo.Design.Combobox<String> vigente_I;
    // End of variables declaration                   
}
