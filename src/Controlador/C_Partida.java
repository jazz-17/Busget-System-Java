package Controlador;

import Modelo.DAO.ElementosDAO;
import Modelo.DAO.PartidaDAO;
import Modelo.DAO.TabsDAO;
import Modelo.Elementos;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Partida;
import Modelo.Tabs;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Vistas.V_Partida;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;

import Custom.SelectOption;

@SuppressWarnings("unchecked")
public class C_Partida implements ItemListener, ActionListener, KeyListener, MouseListener {

    PartidaDAO pDAO = new PartidaDAO();
    ElementosDAO eDAO = new ElementosDAO();
    TabsDAO tDAO = new TabsDAO();
    V_Partida vp = new V_Partida();
    DefaultTableModel modelPartidaI = new DefaultTableModel();
    DefaultTableModel modelPartidaE = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorterI;
    TableRowSorter<DefaultTableModel> sorterE;
    int id = -1;

    public C_Partida(V_Partida vp) {
        this.vp = vp;
        this.vp.btt_Actualizar_I.addActionListener(this);
        this.vp.btt_Registrar_I.addActionListener(this);
        this.vp.btt_Eliminar_I.addActionListener(this);
        this.vp.tablaPartida_I.addMouseListener(this);
        this.vp.tUniMed_I.addItemListener(this);
        this.vp.btt_Actualizar_E.addActionListener(this);
        this.vp.btt_Registrar_E.addActionListener(this);
        this.vp.btt_Eliminar_E.addActionListener(this);
        this.vp.tablaPartida_E.addMouseListener(this);
        this.vp.tUniMed_E.addItemListener(this);
        this.vp.actualizaTabla.addActionListener(this);
        this.vp.nuevo.addActionListener(this);
        init();

    }

    public void init() {
        initTablaPartida("I", vp.tablaPartida_I, modelPartidaI, sorterI);
        initTablaPartida("E", vp.tablaPartida_E, modelPartidaE, sorterE);
        vp.init();

        initListarTabs();
        ((AbstractDocument) vp.desPartida_I.getDocument()).setDocumentFilter(new LimitDocumentFilter(30, 0));
        ((AbstractDocument) vp.desPartida_E.getDocument()).setDocumentFilter(new LimitDocumentFilter(30, 0));
        vp.vigente_E.setSelectedIndex(0);
        vp.vigente_I.setSelectedIndex(0);
    }

    public void initListarTabs() {
        vp.tUniMed_I.removeAllItems();
        vp.tUniMed_E.removeAllItems();
        List<Tabs> listaTab = tDAO.listar();
        for (int i = 0; i < listaTab.size(); i++) {
            String descripcion = listaTab.get(i).getDenTab();
            String codigo = listaTab.get(i).getCodTab();
            vp.tUniMed_I.addItem(new SelectOption(descripcion, codigo));
            vp.tUniMed_E.addItem(new SelectOption(descripcion, codigo));
        }
    }

    public void initListarElementos_I(int cod) {
        vp.eUniMed_I.removeAllItems();
        List<Elementos> listaEle = eDAO.listarTabs(cod);
        for (int i = 0; i < listaEle.size(); i++) {
            String descripcion = listaEle.get(i).getDenElemento();
            String codigo = listaEle.get(i).getCodElemento();
            vp.eUniMed_I.addItem(new SelectOption(descripcion, codigo));
        }
    }

    public void initListarElementos_E(int cod) {
        vp.eUniMed_E.removeAllItems();
        List<Elementos> listaEle = eDAO.listarTabs(cod);
        for (int i = 0; i < listaEle.size(); i++) {
            String descripcion = listaEle.get(i).getDenElemento();
            String codigo = listaEle.get(i).getCodElemento();
            vp.eUniMed_E.addItem(new SelectOption(descripcion, codigo));
            // vp.eUniMed_E.addItem(listaEle.get(i).getCodElemento());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("DENTRO DE ACTION PARTIDA");
        if (e.getSource() == vp.btt_Registrar_I) {
            registrarDatos("I");
            actualizarTabla();
        }
        if (e.getSource() == vp.btt_Actualizar_I) {
            actualizarDatos("I");
            actualizarTabla();
        }
        if (e.getSource() == vp.btt_Eliminar_I) {
            eliminarDatos("I");
            actualizarTabla();
        }
        if (e.getSource() == vp.btt_Registrar_E) {
            registrarDatos("E");
            actualizarTabla();
        }
        if (e.getSource() == vp.btt_Actualizar_E) {
            actualizarDatos("E");
            actualizarTabla();
        }
        if (e.getSource() == vp.btt_Eliminar_E) {
            eliminarDatos("E");
            actualizarTabla();
        }
        if (e.getSource() == vp.actualizaTabla) {
            actualizarTabla();
        }
        if (e.getSource() == vp.nuevo) {
            vaciarCampos();
            actualizarTabla();
        }
    }

    public void actualizarTabla() {
        limpiarTabla(modelPartidaI);
        limpiarTabla(modelPartidaE);
        initTablaPartida("I", vp.tablaPartida_I, modelPartidaI, sorterI);
        initTablaPartida("E", vp.tablaPartida_E, modelPartidaE, sorterE);
        System.out.println("Refrescando tabla automaticamente.");
    }

    public void initTablaPartida(String tip, Modelo.DesignTable.Tabla tabla, DefaultTableModel model,
            TableRowSorter<DefaultTableModel> sorter) {

        //
        new SwingWorker<List<Partida>, Void>() {
            @Override
            protected List<Partida> doInBackground() {
                return pDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "I");
            }

            @Override
            protected void done() {
                try {
                    DefaultTableModel localModel = model;
                    TableRowSorter<DefaultTableModel> localSorter = sorter;

                    localModel = (DefaultTableModel) tabla.getModel();
                    localSorter = new TableRowSorter<>(localModel);

                    tabla.setRowSorter(localSorter);
                    limpiarTabla(localModel);

                    List<Partida> lista = get();
                    Object[] o = new Object[6];
                    for (Partida partida : lista) {
                        o[0] = partida.getCodPartida();
                        o[1] = partida.getDesPartida();
                        o[2] = partida.getCodPartidas();

                        String descTab = (partida.getDescTab());
                        String codTab = (partida.gettUniMed());
                        o[3] = new SelectOption(descTab, codTab);

                        String descElemento = (partida.getDescElemento());
                        String codElemento = (partida.geteUniMed());
                        o[4] = new SelectOption(descElemento, codElemento);

                        o[5] = (partida.getVigente()) == '1' ? "Si" : "No";
                        localModel.addRow(o);
                    }
                    tabla.setModel(localModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    
    }

    public void limpiarTabla(DefaultTableModel model) {
        model.setRowCount(0);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila, cod;
        if (e.getSource() == vp.tablaPartida_I) {
            fila = vp.tablaPartida_I.getSelectedRow();
            cod = Integer.parseInt(vp.tablaPartida_I.getValueAt(fila, 0).toString());
            Partida pI = pDAO.listarId(varCodCiaGlobalDeLogin, cod, "I");
            vp.desPartida_I.setText(pI.getDesPartida());
            vp.eUniMed_I.setSelectedItem(new SelectOption(pI.getDescElemento(), pI.geteUniMed()));
            vp.tUniMed_I.setSelectedItem(new SelectOption(pI.getDescTab(), pI.gettUniMed()));
            vp.vigente_I.setSelectedItem((pI.getVigente() == '1' ? "Vigente" : "No Vigente"));
        }

        if (e.getSource() == vp.tablaPartida_E) {
            fila = vp.tablaPartida_E.getSelectedRow();
            cod = Integer.parseInt(vp.tablaPartida_E.getValueAt(fila, 0).toString());
            Partida pE = new PartidaDAO().listarId(varCodCiaGlobalDeLogin, cod, "E");
            vp.desPartida_E.setText(pE.getDesPartida());
            vp.eUniMed_E.setSelectedItem(new SelectOption(pE.getDescElemento(), pE.geteUniMed()));
            vp.tUniMed_E.setSelectedItem(new SelectOption(pE.getDescTab(), pE.gettUniMed()));
            vp.vigente_E.setSelectedItem((pE.getVigente() == '1' ? "Vigente" : "No Vigente"));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }

    public void registrarDatos(String tip) {
        Partida p = new Partida();
        char vig;
        if (tip == "I") {
            p.setCodCia(varCodCiaGlobalDeLogin);
            p.setIngEgr(tip);

            Object item = vp.tUniMed_I.getSelectedItem();
            String value = ((SelectOption) item).getValue();
            p.settUniMed(value);

            Object item2 = vp.eUniMed_I.getSelectedItem();
            String value2 = ((SelectOption) item2).getValue();
            p.seteUniMed(value2);

            p.setDesPartida(vp.desPartida_I.getText());
            vig = "Vigente".equals(vp.vigente_I.getSelectedItem().toString()) ? '1' : '0';
            p.setVigente(vig);
        } else {
            p.setCodCia(varCodCiaGlobalDeLogin);
            p.setIngEgr(tip);

            Object item = vp.tUniMed_E.getSelectedItem();
            String value = ((SelectOption) item).getValue();
            p.settUniMed(value);

            Object item2 = vp.eUniMed_E.getSelectedItem();
            String value2 = ((SelectOption) item2).getValue();
            p.seteUniMed(value2);
            p.setDesPartida(vp.desPartida_E.getText());
            vig = "Vigente".equals(vp.vigente_E.getSelectedItem().toString()) ? '1' : '0';
            p.setVigente(vig);
        }
        if (pDAO.add(p) == 1) { // Corregir
            showMessage2("Partida registrada correctamente");
            vaciarCampos();
        } else {
            showMessage1("Error al registrar Partida");
        }
    }

    private boolean showMessage1(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        return true;
    }

    private boolean showMessage2(String message) {
        JOptionPane.showMessageDialog(null, message, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public void actualizarDatos(String tip) {
        int fila, cod;
        char vig;
        Partida pm = new Partida();
        if (tip == "I") {
            fila = vp.tablaPartida_I.getSelectedRow();
            if (fila != -1) {
                cod = Integer.parseInt(vp.tablaPartida_I.getValueAt(fila, 0).toString());
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setDesPartida(vp.desPartida_I.getText());

                Object item = vp.tUniMed_I.getSelectedItem();
                String value = ((SelectOption) item).getValue();
                pm.settUniMed(value);

                Object item2 = vp.eUniMed_I.getSelectedItem();
                String value2 = ((SelectOption) item2).getValue();
                pm.seteUniMed(value2);
                // pm.seteUniMed(vp.eUniMed_I.getSelectedItem().toString());

                vig = "Vigente".equals(vp.vigente_I.getSelectedItem().toString()) ? '1' : '0';
                pm.setVigente(vig);
                if (pDAO.actualizar(pm) == 1) {
                    showMessage2("Partida registrado correctamente");
                    vaciarCampos();
                } else {
                    showMessage1("Error al registrar Partida");
                }
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        } else {
            fila = vp.tablaPartida_E.getSelectedRow();
            if (fila != -1) {
                cod = Integer.parseInt(vp.tablaPartida_E.getValueAt(fila, 0).toString());
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setDesPartida(vp.desPartida_E.getText());

                Object item = vp.tUniMed_E.getSelectedItem();
                String value = ((SelectOption) item).getValue();
                pm.settUniMed(value);

                Object item2 = vp.eUniMed_E.getSelectedItem();
                String value2 = ((SelectOption) item2).getValue();
                pm.seteUniMed(value2);

                vig = "Vigente".equals(vp.vigente_E.getSelectedItem().toString()) ? '1' : '0';
                pm.setVigente(vig);
                if (pDAO.actualizar(pm) == 1) {
                    showMessage2("Partida registrado correctamente");
                    vaciarCampos();
                } else {
                    showMessage1("Error al registrar Partida");
                }
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        }
    }

    public void eliminarDatos(String tip) {
        int fila, cod;
        if (tip == "I") {
            fila = vp.tablaPartida_I.getSelectedRow();
            // System.out.println("La fila es" + fila);
            if (fila != -1) {
                // System.out.println("Hay filas seleccionadas.");
                cod = Integer.parseInt(vp.tablaPartida_I.getValueAt(fila, 0).toString());
                new PartidaDAO().eliminarDatos(varCodCiaGlobalDeLogin, cod, tip);
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        } else {
            fila = vp.tablaPartida_E.getSelectedRow();
            // System.out.println("La fila es" + fila);
            if (fila != -1) {
                // System.out.println("Hay filas seleccionadas.");
                cod = Integer.parseInt(vp.tablaPartida_E.getValueAt(fila, 0).toString());
                new PartidaDAO().eliminarDatos(varCodCiaGlobalDeLogin, cod, tip);
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        }
    }

    public void vaciarCampos() {
        vp.desPartida_E.setText("");
        vp.desPartida_I.setText("");
    }

    int codT;

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == vp.tUniMed_I) {
            Object item = vp.tUniMed_I.getSelectedItem();
            String value = ((SelectOption) item).getValue();
            codT = Integer.parseInt(value);
            initListarElementos_I(codT);
        }

        if (e.getSource() == vp.tUniMed_E) {
            Object item = vp.tUniMed_E.getSelectedItem();
            String value = ((SelectOption) item).getValue();
            codT = Integer.parseInt(value);
            initListarElementos_E(codT);
        }
    }
}