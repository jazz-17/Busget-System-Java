package Controlador;

import Modelo.DAO.PPartidaDAO;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.DAO.PPartida_MezclaDAO;
import Modelo.PPartida;
import Modelo.PPartida_Mezcla;
import Vistas.V_PPartida_Mezcla;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Custom.SelectOption;

@SuppressWarnings("unchecked")
public class C_PPartida_Mezcla implements ItemListener, ActionListener, KeyListener, MouseListener {
    V_PPartida_Mezcla vpm = new V_PPartida_Mezcla();
    DefaultTableModel modelPartidaMezclaI = new DefaultTableModel();
    DefaultTableModel modelPartidaMezclaE = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorterI;
    TableRowSorter<DefaultTableModel> sorterE;
    int ordenI, ordenE;
    List<PPartida> partidas_E;
    List<PPartida> partidas_I;
    List<PPartida_Mezcla> partidasMezcla_E;
    List<PPartida_Mezcla> partidasMezcla_I;

    PPartida_MezclaDAO ppmDAO = new PPartida_MezclaDAO();
    PPartidaDAO pDAO = new PPartidaDAO();

    public C_PPartida_Mezcla(V_PPartida_Mezcla vpm) {
        this.vpm = vpm;
        this.vpm.btt_Actualizar_I.addActionListener(this);
        this.vpm.btt_Registrar_I.addActionListener(this);
        this.vpm.btt_Eliminar_I.addActionListener(this);
        this.vpm.bttn_confI.addActionListener(this);
        this.vpm.tablaPartida_Mezcla_I.addMouseListener(this);
        this.vpm.padCodPartida_I.addItemListener(this);

        this.vpm.btt_Actualizar_E.addActionListener(this);
        this.vpm.btt_Registrar_E.addActionListener(this);
        this.vpm.btt_Eliminar_E.addActionListener(this);
        this.vpm.tablaPartida_Mezcla_E.addMouseListener(this);
        this.vpm.padCodPartida_E.addItemListener(this);
        this.vpm.bttn_confE.addActionListener(this);

        this.vpm.actualizaTabla.addActionListener(this);
        this.vpm.nuevo.addActionListener(this);

        this.partidas_E = pDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "E");
        this.partidas_I = pDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "I");
        init();
    }

    public void init() {
        initTabla("I", vpm.tablaPartida_Mezcla_I, modelPartidaMezclaI, sorterI);
        initTabla("E", vpm.tablaPartida_Mezcla_E, modelPartidaMezclaE, sorterE);
        vpm.init();
        vpm.vigente_E.setSelectedIndex(0);
        vpm.vigente_I.setSelectedIndex(0);
        initPartidas("I", vpm.codPartida_I, vpm.padCodPartida_I);
        initPartidas("E", vpm.codPartida_E, vpm.padCodPartida_E);
    }

    public void initPartidas(String tip, Modelo.Design.Combobox<Object> partidaCombobox,
            Modelo.Design.Combobox<Object> partidaPadreCombobox) {
        partidaCombobox.removeAllItems();
        partidaPadreCombobox.removeAllItems();
        partidaPadreCombobox.addItem(new SelectOption("NULL", "0"));
        List<PPartida_Mezcla> listaPadre = ppmDAO.listarCodPartida_Mezcla(varCodCiaGlobalDeLogin, tip);
        for (int i = 0; i < listaPadre.size(); i++) {
            PPartida_Mezcla pm = listaPadre.get(i);
            SelectOption item = new SelectOption(pm.getDescripcion(), pm.getCodPartida() + "");
            partidaPadreCombobox.addItem(item);
        }

        List<PPartida> lista = pDAO.listarPorCodCia(varCodCiaGlobalDeLogin, tip);
        for (int i = 0; i < lista.size(); i++) {
            PPartida p = lista.get(i);
            SelectOption item = new SelectOption(p.getDesPartida(), p.getCodPartida() + "");
            partidaCombobox.addItem(item);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DENTRO DE ACTION PARTIDA_MEZCLA");
        if (e.getSource() == vpm.btt_Registrar_I) {
            registrarDatos("I");
            actualizarTabla();
        }
        if (e.getSource() == vpm.btt_Actualizar_I) {
            actualizarDatos("I");
            actualizarTabla();
        }
        if (e.getSource() == vpm.btt_Eliminar_I) {
            eliminarDatos("I");
            actualizarTabla();
        }
        if (e.getSource() == vpm.bttn_confI) {
            vpm.orden_I.setEnabled(true);
            vpm.nivel_I.setEnabled(true);
        }
        if (e.getSource() == vpm.btt_Registrar_E) {
            registrarDatos("E");
            actualizarTabla();
        }
        if (e.getSource() == vpm.btt_Actualizar_E) {
            actualizarDatos("E");
            actualizarTabla();
        }
        if (e.getSource() == vpm.btt_Eliminar_E) {
            eliminarDatos("E");
            actualizarTabla();
        }
        if (e.getSource() == vpm.bttn_confE) {
            vpm.orden_E.setEnabled(true);
            vpm.nivel_I.setEnabled(true);
        }
        if (e.getSource() == vpm.actualizaTabla) {
            actualizarTabla();
        }
        if (e.getSource() == vpm.nuevo) {
            vaciarCampos();
            actualizarTabla();
        }
    }

    public void actualizarTabla() {
        limpiarTabla(modelPartidaMezclaI);
        limpiarTabla(modelPartidaMezclaE);
        initTabla("I", vpm.tablaPartida_Mezcla_I, modelPartidaMezclaI, sorterI);
        initTabla("E", vpm.tablaPartida_Mezcla_E, modelPartidaMezclaE, sorterE);
        System.out.println("Refrescando tabla automaticamente.");
    }

    public void initTabla(String tip, Modelo.DesignTable.Tabla tabla, DefaultTableModel model,
            TableRowSorter<DefaultTableModel> sorter) {
        new SwingWorker<List<PPartida_Mezcla>, Void>() {
            @Override
            protected List<PPartida_Mezcla> doInBackground() {
                return ppmDAO.listarPorCodCia(varCodCiaGlobalDeLogin, tip);
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

                    List<PPartida_Mezcla> lista = get();
                    Object[] o = new Object[10];
                    for (PPartida_Mezcla partida : lista) {
                        o[0] = partida.getCodPartida();
                        o[1] = partida.getDescripcion();
                        String codPartida = partida.getPadCodPartida() + "";
                        String padDescripcion = partida.getPadDescripcion();
                        o[2] = new SelectOption(padDescripcion, codPartida);
                        o[3] = partida.getCorr();
                        o[4] = partida.getNivel();
                        o[5] = partida.getOrden();
                        String tabDescripcion = partida.getTabDesc();
                        String codTab = partida.gettUnitMed() + "";
                        o[6] = new SelectOption(tabDescripcion, codTab);

                        String elementoDescripcion = partida.getElementoDesc();
                        String codElemento = partida.geteUnitMed() + "";
                        o[7] = new SelectOption(elementoDescripcion, codElemento);

                        o[8] = partida.getCostoUnit();
                        o[9] = (partida.getVigente()) == '1' ? "Si" : "No";
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
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
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
        if (e.getSource() == vpm.tablaPartida_Mezcla_I) {
            handleTableRowClick("I", vpm.tablaPartida_Mezcla_I, vpm.codPartida_I, vpm.padCodPartida_I, vpm.costoUnit_I,
                    vpm.nivel_I, vpm.orden_I, vpm.vigente_I);
        }

        if (e.getSource() == vpm.tablaPartida_Mezcla_E) {
            handleTableRowClick("E", vpm.tablaPartida_Mezcla_E, vpm.codPartida_E, vpm.padCodPartida_E, vpm.costoUnit_E,
                    vpm.nivel_E, vpm.orden_E, vpm.vigente_E);
        }
    }

    public void handleTableRowClick(String tip, Modelo.DesignTable.Tabla table,
            Modelo.Design.Combobox<Object> partidaCombobox,
            Modelo.Design.Combobox<Object> padresCombobox, javax.swing.JSpinner costoUnit, javax.swing.JSpinner nivel,
            javax.swing.JSpinner orden, Modelo.Design.Combobox<Object> vigente) {

        int fila = table.getSelectedRow();
        int cod = Integer.parseInt(table.getValueAt(fila, 0).toString());
        int corr = Integer.parseInt(table.getValueAt(fila, 3).toString());

        PPartida_Mezcla pm = ppmDAO.listarId(varCodCiaGlobalDeLogin, tip, cod, corr);

        partidaCombobox.setSelectedItem(new SelectOption(pm.getDescripcion(), pm.getCodPartida() + ""));

        int padCodPartida = pm.getPadCodPartida();
        if (padCodPartida == 0)
            padresCombobox.setSelectedItem(new SelectOption("NULL", "0"));
        else {
            padresCombobox.setSelectedItem(new SelectOption(pm.getPadDescripcion(), pm.getPadCodPartida() + ""));
        }

        costoUnit.setValue(pm.getCostoUnit());
        nivel.setValue(pm.getNivel());
        nivel.setEnabled(true);
        orden.setValue(pm.getOrden());
        orden.setEnabled(true);

        if (tip == "I") {
            ordenI = pm.getOrden();
        } else {
            ordenE = pm.getOrden();
        }

        vigente.setSelectedItem((pm.getVigente() == '1' ? "Vigente" : "No Vigente"));

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
        if (tip == "I") {
            handleRegistration(tip, vpm.codPartida_I, vpm.padCodPartida_I, vpm.nivel_I, vpm.orden_I, vpm.costoUnit_I,
                    vpm.vigente_I);
        } else {
            handleRegistration(tip, vpm.codPartida_E, vpm.padCodPartida_E, vpm.nivel_E, vpm.orden_E, vpm.costoUnit_E,
                    vpm.vigente_E);
        }

    }

    public void handleRegistration(String tip, Modelo.Design.Combobox<Object> partidaCombobox,
            Modelo.Design.Combobox<Object> padreCombobox, javax.swing.JSpinner nivel, javax.swing.JSpinner orden,
            javax.swing.JSpinner costoUnit, Modelo.Design.Combobox<Object> vigente) {
        PPartida_Mezcla pm = new PPartida_Mezcla();
        char vig;
        pm.setCodCia(varCodCiaGlobalDeLogin);
        pm.setIngEgr(tip);
        Object item = partidaCombobox.getSelectedItem();
        int codPartida = Integer.parseInt(((SelectOption) item).getValue());
        pm.setCodPartida(codPartida);

        Object item2 = padreCombobox.getSelectedItem();
        int padCodPartida = Integer.parseInt(((SelectOption) item2).getValue());
        pm.setPadCodPartida(padCodPartida);

        pm.setNivel(Integer.parseInt(nivel.getValue().toString()));
        pm.setOrden(Integer.parseInt(orden.getValue().toString()));
        pm.setCostoUnit(Float.parseFloat(costoUnit.getValue().toString()));

        PPartida p = pDAO.listarId(varCodCiaGlobalDeLogin, pm.getCodPartida(), tip);
        pm.settUnitMed(p.gettUniMed());
        pm.seteUnitMed(p.geteUniMed());

        Object item3 = vigente.getSelectedItem();
        if (item3 == null) {
            vig = '0'; // Assuming '0' as default for non-vigente
        } else {
            vig = "Vigente".equals(item3.toString()) ? '1' : '0';
        }
        pm.setVigente(vig);

        if (pm.getCodPartida() == pm.getPadCodPartida()) {
            JOptionPane.showMessageDialog(null, "La partida no puede ser padre de si misma.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (ppmDAO.add(pm) == 1) {
            JOptionPane.showMessageDialog(null, "PPartida mezcla registrada correctamente", "",
                    JOptionPane.INFORMATION_MESSAGE);
            vaciarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean showMessage1(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        return true;
    }

    private boolean showMessage2(String message) {
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public void actualizarDatos(String tip) {
        if (tip == "I") {
            handleActualizarDatos(tip, vpm.tablaPartida_Mezcla_I, vpm.codPartida_I, vpm.padCodPartida_I,
                    vpm.nivel_I, vpm.orden_I, vpm.costoUnit_I, vpm.vigente_I);
        } else {
            handleActualizarDatos(tip, vpm.tablaPartida_Mezcla_E, vpm.codPartida_E, vpm.padCodPartida_E,
                    vpm.nivel_E, vpm.orden_E, vpm.costoUnit_E, vpm.vigente_E);
        }
    }

    public void handleActualizarDatos(String tip, Modelo.DesignTable.Tabla tabla,
            Modelo.Design.Combobox<Object> partidaCombobox,
            Modelo.Design.Combobox<Object> padreCombobox, javax.swing.JSpinner nivel, javax.swing.JSpinner orden,
            javax.swing.JSpinner costoUnit, Modelo.Design.Combobox<Object> vigente) {
        int fila, codPartida, corr, codPartidaPadre;
        char vig;
        PPartida_Mezcla pm = new PPartida_Mezcla();
        Object partidaPadre;
        fila = tabla.getSelectedRow();
        if (fila != -1) {
            codPartida = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
            partidaPadre = tabla.getValueAt(fila, 2);
            if (partidaPadre.toString() == "NULL") {
                codPartidaPadre = 0;
            } else {
                codPartidaPadre = Integer.parseInt(((SelectOption) partidaPadre).getValue());
            }
            corr = Integer.parseInt(tabla.getValueAt(fila, 3).toString());
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);
            pm.setCodPartida(codPartida);
            pm.setCorr(corr);
            pm.setNivel(Integer.parseInt(nivel.getValue().toString()));
            pm.setOrden(Integer.parseInt(orden.getValue().toString()));
            pm.setCostoUnit(Float.parseFloat(costoUnit.getValue().toString()));
            Object selectedItem2 = vigente.getSelectedItem();
            if (selectedItem2 == null) {
                vig = '0'; // Assuming '0' as default for non-vigente
            } else {
                String selectedString = selectedItem2.toString();
                vig = "Vigente".equals(selectedString) ? '1' : '0';
            }
            pm.setVigente(vig);

            // Verificar si la partida mezcla a actualizar tiene el mismo orden que otra
            if (ppmDAO.busOrden(varCodCiaGlobalDeLogin, tip, codPartidaPadre, pm.getNivel(), pm.getOrden()) == true) {
                PPartida_Mezcla pm2 = ppmDAO.listarOrden(varCodCiaGlobalDeLogin, tip, codPartidaPadre,
                        pm.getNivel(), pm.getOrden());
                if (tip == "I") {
                    pm2.setOrden(ordenI);
                } else {
                    pm2.setOrden(ordenE);
                }
                ppmDAO.actualizar(pm2);
            }
            if (ppmDAO.actualizar(pm) == 1) {
                showMessage2("PPartida_Mezcla actualizada correctamente");
                vaciarCampos();
            } else {
                showMessage1("Error al actualizar PPartida_Mezcla");
            }
        } else {
            showMessage1("Debe seleccionar una fila");
        }

    }

    public void eliminarDatos(String tip) {
        int fila, cod, corr;
        if (tip == "I") {
            fila = vpm.tablaPartida_Mezcla_I.getSelectedRow();
            if (fila != -1) {
                corr = Integer.parseInt(vpm.tablaPartida_Mezcla_I.getValueAt(fila, 3).toString());
                cod = Integer.parseInt(vpm.tablaPartida_Mezcla_I.getValueAt(fila, 0).toString());
                ppmDAO.eliminarDatos(varCodCiaGlobalDeLogin, cod, tip, corr);
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        } else {
            fila = vpm.tablaPartida_Mezcla_E.getSelectedRow();
            System.out.println("La fila es" + fila);
            if (fila != -1) {
                System.out.println("Hay filas seleccionadas.");
                cod = Integer.parseInt(vpm.tablaPartida_Mezcla_E.getValueAt(fila, 0).toString());
                corr = Integer.parseInt(vpm.tablaPartida_Mezcla_E.getValueAt(fila, 3).toString());
                ppmDAO.eliminarDatos(varCodCiaGlobalDeLogin, cod, tip, corr);
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        }
    }

    public void vaciarCampos() {
        initPartidas("I", vpm.codPartida_I, vpm.padCodPartida_I);
        initPartidas("E", vpm.codPartida_E, vpm.padCodPartida_E);
        try{
            vpm.nivel_E.setValue(0);
            vpm.nivel_I.setValue(0);
            vpm.orden_E.setValue(0);
            vpm.orden_I.setValue(0);
            vpm.costoUnit_E.setValue(0);
            vpm.costoUnit_I.setValue(0);
            vpm.codPartida_I.setSelectedIndex(0);
            vpm.codPartida_E.setSelectedIndex(0);
            vpm.padCodPartida_I.setSelectedIndex(0);
            vpm.padCodPartida_E.setSelectedIndex(0);
    
            vpm.nivel_I.setModel(new SpinnerNumberModel(1, 1, 99, 1));
            vpm.orden_I.setModel(new SpinnerNumberModel(1, 1, 99, 1));
            vpm.costoUnit_I.setModel(new SpinnerNumberModel(0, 0, 999999999, 1000));
    
            vpm.nivel_E.setModel(new SpinnerNumberModel(0, 0, 99, 1));
            vpm.orden_E.setModel(new SpinnerNumberModel(0, 0, 99, 1));
            vpm.costoUnit_E.setModel(new SpinnerNumberModel(0, 0, 999999999, 1000));
    
            Object item = vpm.padCodPartida_I.getSelectedItem();
            codPAD = Integer.parseInt(((SelectOption) item).getValue());
            if (codPAD == 0) {
                vpm.nivel_I.setValue(1);
                vpm.orden_I.setValue(ppmDAO.asignarOrden("I", 1, varCodCiaGlobalDeLogin));
                vpm.nivel_I.setEnabled(false);
                vpm.orden_I.setEnabled(false);
            }
    
            Object item2 = vpm.padCodPartida_E.getSelectedItem();
            codPAD = Integer.parseInt(((SelectOption) item2).getValue());
            if (codPAD == 0) {
                vpm.nivel_E.setValue(1);
                vpm.orden_E.setValue(ppmDAO.asignarOrden("E", 1, varCodCiaGlobalDeLogin));
                vpm.nivel_E.setEnabled(false);
                vpm.orden_E.setEnabled(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    int codPAD;

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == vpm.padCodPartida_I && vpm.padCodPartida_I.getSelectedItem() != null) {
            Object item2 = vpm.padCodPartida_I.getSelectedItem();
            codPAD = Integer.parseInt(((SelectOption) item2).getValue());
            if (codPAD == 0) {
                vpm.nivel_I.setValue(1);
                vpm.orden_I.setValue(ppmDAO.asignarOrden("I", 1, varCodCiaGlobalDeLogin));
                vpm.nivel_I.setEnabled(false);
                vpm.orden_I.setEnabled(false);
            } else {
                vpm.nivel_I.setValue(ppmDAO.asignarNivelNoNULL("I", codPAD, varCodCiaGlobalDeLogin));
                vpm.orden_I.setValue(ppmDAO.asignarOrdenNoNULL("I", codPAD, varCodCiaGlobalDeLogin));
                vpm.nivel_I.setEnabled(false);
                vpm.orden_I.setEnabled(false);
            }
        }

        if (e.getSource() == vpm.padCodPartida_E && vpm.padCodPartida_E.getSelectedItem() != null) {
            Object item2 = vpm.padCodPartida_E.getSelectedItem();
            codPAD = Integer.parseInt(((SelectOption) item2).getValue());
            if (codPAD == 0) {
                vpm.nivel_E.setValue(1);
                vpm.orden_E.setValue(ppmDAO.asignarOrden("E", 1, varCodCiaGlobalDeLogin));
                vpm.nivel_E.setEnabled(false);
                vpm.orden_E.setEnabled(false);
            } else {
                vpm.nivel_E.setValue(ppmDAO.asignarNivelNoNULL("E", codPAD, varCodCiaGlobalDeLogin));
                vpm.orden_E.setValue(ppmDAO.asignarOrdenNoNULL("E", codPAD, varCodCiaGlobalDeLogin));
                vpm.nivel_E.setEnabled(false);
                vpm.orden_E.setEnabled(false);
            }
        }
    }
}