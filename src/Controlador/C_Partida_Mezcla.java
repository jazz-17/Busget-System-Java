package Controlador;

import Modelo.DAO.PartidaDAO;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.DAO.Partida_MezclaDAO;
import Modelo.Partida;
import Modelo.Partida_Mezcla;
import Vistas.V_Partida_Mezcla;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Custom_by_me.SelectPartidas;

@SuppressWarnings("unchecked")
public class C_Partida_Mezcla implements ItemListener, ActionListener, KeyListener, MouseListener {
    V_Partida_Mezcla vpm = new V_Partida_Mezcla();
    DefaultTableModel modelPartidaMezclaI = new DefaultTableModel();
    DefaultTableModel modelPartidaMezclaE = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorterI;
    TableRowSorter<DefaultTableModel> sorterE;
    int ordenI, ordenE;
    List<Partida> partidas_E;
    List<Partida> partidas_I;
    List<Partida_Mezcla> partidasMezcla_E;
    List<Partida_Mezcla> partidasMezcla_I;

    Partida_MezclaDAO partidaMezclaDAO = new Partida_MezclaDAO();
    PartidaDAO partidaDAO = new PartidaDAO();

    public C_Partida_Mezcla(V_Partida_Mezcla vpm) {
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

        this.partidas_E = partidaDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "E");
        this.partidas_I = partidaDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "I");
        init();
    }

    public void init() {
        initTablaPartidaMezcla_I();
        initTablaPartidaMezcla_E();
        vpm.init();
        vpm.vigente_E.setSelectedIndex(0);
        vpm.vigente_I.setSelectedIndex(0);
        initListarPartidas();
    }

    public void initListarPartidas() {
        vpm.codPartida_I.removeAllItems();
        vpm.codPartida_E.removeAllItems();
        vpm.padCodPartida_I.removeAllItems();
        vpm.padCodPartida_E.removeAllItems();

        List<Partida> listaParI = partidaDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "I");
        List<Partida> listaParE = partidaDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "E");
        List<Partida_Mezcla> listaParIP = partidaMezclaDAO.listarCodPartida_Mezcla(varCodCiaGlobalDeLogin, "I");
        List<Partida_Mezcla> listaParEP = partidaMezclaDAO.listarCodPartida_Mezcla(varCodCiaGlobalDeLogin, "E");
        vpm.padCodPartida_I.addItem(new SelectPartidas("NULL", "0"));
        vpm.padCodPartida_E.addItem(new SelectPartidas("NULL", "0"));

        for (int i = 0; i < listaParIP.size(); i++) {
            int codPartidaPadre = listaParIP.get(i).getCodPartida();
            this.partidas_I.forEach(partida -> {
                if (partida.getCodPartida() == codPartidaPadre) {
                    vpm.padCodPartida_I.addItem(
                            new SelectPartidas(partida.getDesPartida(), String.valueOf(codPartidaPadre)));
                }
            });
        }

        for (int i = 0; i < listaParI.size(); i++) {
            String descripcion = listaParI.get(i).getDesPartida();
            int codPartida = listaParI.get(i).getCodPartida();
            Object item = new SelectPartidas(descripcion, String.valueOf(codPartida));
            vpm.codPartida_I.addItem(item);
        }

        for (int i = 0; i < listaParEP.size(); i++) {
            int codPartidaPadre = listaParEP.get(i).getCodPartida();
            this.partidas_E.forEach(partida -> {
                if (partida.getCodPartida() == codPartidaPadre) {
                    vpm.padCodPartida_E.addItem(
                            new SelectPartidas(partida.getDesPartida(), String.valueOf(codPartidaPadre)));
                }
            });
        }

        for (int i = 0; i < listaParE.size(); i++) {
            String descripcion = listaParE.get(i).getDesPartida();
            int codPartida = listaParE.get(i).getCodPartida();
            Object item = new SelectPartidas(descripcion, String.valueOf(codPartida));
            vpm.codPartida_E.addItem(item);
            // vpm.codPartida_E.addItem(listaParE.get(i).getCodPartida());
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
        initTablaPartidaMezcla_I();
        initTablaPartidaMezcla_E();
        // initListarPartidas(); //Para una mejor frescura visual.
        // Me da un error
        System.out.println("Refrescando tabla automaticamente.");
    }

    public void initTablaPartidaMezcla_I() {
        List<Partida_Mezcla> listaI = partidaMezclaDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "I");
        modelPartidaMezclaI = (DefaultTableModel) vpm.tablaPartida_Mezcla_I.getModel();
        Object[] o = new Object[10];
        sorterI = new TableRowSorter<>(modelPartidaMezclaI);
        vpm.tablaPartida_Mezcla_I.setRowSorter(sorterI);
        limpiarTabla(modelPartidaMezclaI);
        for (int i = 0; i < listaI.size(); i++) {
            o[0] = listaI.get(i).getCorr();
            int codPartida = listaI.get(i).getCodPartida();
            this.partidas_I.forEach(partida -> {
                if (partida.getCodPartida() == codPartida) {
                    o[1] = partida.getDesPartida();
                }
            });
            o[2] = codPartida;
            o[3] = (listaI.get(i).getPadCodPartida()) == 0 ? "NULL" : listaI.get(i).getPadCodPartida();
            o[4] = listaI.get(i).getNivel();
            o[5] = listaI.get(i).getOrden();
            o[6] = listaI.get(i).gettUnitMed();
            o[7] = listaI.get(i).geteUnitMed();
            o[8] = listaI.get(i).getCostoUnit();
            o[9] = (listaI.get(i).getVigente()) == '1' ? "Si" : "No";
            modelPartidaMezclaI.addRow(o);
        }
        vpm.tablaPartida_Mezcla_I.setModel(modelPartidaMezclaI);
    }

    public void initTablaPartidaMezcla_E() {
        List<Partida_Mezcla> listaE = partidaMezclaDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "E");
        modelPartidaMezclaE = (DefaultTableModel) vpm.tablaPartida_Mezcla_E.getModel();
        Object[] o = new Object[10];
        sorterE = new TableRowSorter<>(modelPartidaMezclaE);
        vpm.tablaPartida_Mezcla_E.setRowSorter(sorterE);
        limpiarTabla(modelPartidaMezclaE);
        for (int i = 0; i < listaE.size(); i++) {
            o[0] = listaE.get(i).getCorr();
            int codPartida = listaE.get(i).getCodPartida();
            this.partidas_E.forEach(partida -> {
                if (partida.getCodPartida() == codPartida) {
                    o[1] = partida.getDesPartida();
                }
            });
            o[2] = listaE.get(i).getCodPartida();
            o[3] = (listaE.get(i).getPadCodPartida()) == 0 ? "NULL" : listaE.get(i).getPadCodPartida();
            o[4] = listaE.get(i).getNivel();
            o[5] = listaE.get(i).getOrden();
            o[6] = listaE.get(i).gettUnitMed();
            o[7] = listaE.get(i).geteUnitMed();
            o[8] = listaE.get(i).getCostoUnit();
            o[9] = (listaE.get(i).getVigente()) == '1' ? "Si" : "No";
            modelPartidaMezclaE.addRow(o);
        }
        vpm.tablaPartida_Mezcla_E.setModel(modelPartidaMezclaE);
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
        int fila, cor, cod;
        if (e.getSource() == vpm.tablaPartida_Mezcla_I) {
            fila = vpm.tablaPartida_Mezcla_I.getSelectedRow();
            cor = Integer.parseInt(vpm.tablaPartida_Mezcla_I.getValueAt(fila, 0).toString());
            cod = Integer.parseInt(vpm.tablaPartida_Mezcla_I.getValueAt(fila, 2).toString());

            Partida_Mezcla pmI = partidaMezclaDAO.listarId(varCodCiaGlobalDeLogin, "I", cod, cor);

            int partidaID = pmI.getCodPartida();
            this.partidas_I.forEach(partida -> {
                if (partida.getCodPartida() == partidaID) {
                    vpm.codPartida_I.setSelectedItem(
                            new SelectPartidas(partida.getDesPartida(), String.valueOf(partida.getCodPartida())));
                }
            });
            // vpm.codPartida_I.setSelectedItem(pmI.getCodPartida());

            vpm.costoUnit_I.setValue(pmI.getCostoUnit());

            int padCodPartida = pmI.getPadCodPartida();
            if (padCodPartida == 0)
                vpm.padCodPartida_I.setSelectedItem(new SelectPartidas("NULL", "0"));
            else {
                this.partidas_I.forEach(partida -> {
                    if (partida.getCodPartida() == padCodPartida) {
                        vpm.padCodPartida_I.setSelectedItem(
                                new SelectPartidas(partida.getDesPartida(), String.valueOf(padCodPartida)));
                    }
                });
            }

            vpm.nivel_I.setValue(pmI.getNivel());
            vpm.nivel_I.setEnabled(true);
            vpm.orden_I.setValue(pmI.getOrden());

            ordenI = pmI.getOrden();

            vpm.orden_I.setEnabled(true);
            vpm.vigente_I.setSelectedItem((pmI.getVigente() == '1' ? "Vigente" : "No Vigente"));
        }

        if (e.getSource() == vpm.tablaPartida_Mezcla_E) {
            fila = vpm.tablaPartida_Mezcla_E.getSelectedRow();
            cor = Integer.parseInt(vpm.tablaPartida_Mezcla_E.getValueAt(fila, 0).toString());
            cod = Integer.parseInt(vpm.tablaPartida_Mezcla_E.getValueAt(fila, 2).toString());
            System.out.println("PartidaMezcla = " + cor);
            Partida_Mezcla pmE = partidaMezclaDAO.listarId(varCodCiaGlobalDeLogin, "E", cod, cor);

            int partidaID = pmE.getCodPartida();
            this.partidas_E.forEach(partida -> {
                if (partida.getCodPartida() == partidaID) {
                    vpm.codPartida_E.setSelectedItem(
                            new SelectPartidas(partida.getDesPartida(), String.valueOf(partida.getCodPartida())));
                }
            });
            // vpm.codPartida_E.setSelectedItem(pmE.getCodPartida());

            vpm.costoUnit_E.setValue(pmE.getCostoUnit());

            int padCodPartida = pmE.getPadCodPartida();
            if (padCodPartida == 0)
                vpm.padCodPartida_E.setSelectedItem(new SelectPartidas("NULL", "0"));
            else {
                this.partidas_E.forEach(partida -> {
                    if (partida.getCodPartida() == padCodPartida) {
                        vpm.padCodPartida_E.setSelectedItem(
                                new SelectPartidas(partida.getDesPartida(), String.valueOf(padCodPartida)));
                    }
                });
            }

            vpm.nivel_E.setValue(pmE.getNivel());
            vpm.nivel_E.setEnabled(true);
            vpm.orden_E.setValue(pmE.getOrden());

            ordenE = pmE.getOrden();

            vpm.orden_E.setEnabled(true);
            vpm.vigente_E.setSelectedItem((pmE.getVigente() == '1' ? "Vigente" : "No Vigente"));
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
        Partida_Mezcla pm = new Partida_Mezcla();
        char vig;
        if (tip == "I") {
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);

            Object item = vpm.codPartida_I.getSelectedItem();
            int codPartida = Integer.parseInt(((SelectPartidas) item).getValue());
            pm.setCodPartida(codPartida);
            // pm.setCodPartida(Integer.parseInt(vpm.codPartida_I.getSelectedItem().toString()));

            Object item2 = vpm.padCodPartida_I.getSelectedItem();
            int padCodPartida = Integer.parseInt(((SelectPartidas) item2).getValue());
            pm.setPadCodPartida(padCodPartida);

            pm.setNivel(Integer.parseInt(vpm.nivel_I.getValue().toString()));
            pm.setOrden(Integer.parseInt(vpm.orden_I.getValue().toString()));
            pm.setCostoUnit(Float.parseFloat(vpm.costoUnit_I.getValue().toString()));

            Partida p = partidaDAO.listarId(varCodCiaGlobalDeLogin, pm.getCodPartida(), "I");
            pm.settUnitMed(p.gettUniMed());
            pm.seteUnitMed(p.geteUniMed());

            Object selectedItem2 = vpm.vigente_I.getSelectedItem();
            if (selectedItem2 == null) {
                vig = '0'; // Assuming '0' as default for non-vigente
            } else {
                String selectedString = selectedItem2.toString();
                vig = "Vigente".equals(selectedString) ? '1' : '0';
            }
            pm.setVigente(vig);
        } else {
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);

            Object item = vpm.codPartida_E.getSelectedItem();
            int codPartida = Integer.parseInt(((SelectPartidas) item).getValue());
            pm.setCodPartida(codPartida);
            // pm.setCodPartida(Integer.parseInt(vpm.codPartida_E.getSelectedItem().toString()));

            Object item2 = vpm.padCodPartida_E.getSelectedItem();
            int padCodPartida = Integer.parseInt(((SelectPartidas) item2).getValue());
            pm.setPadCodPartida(padCodPartida);

            pm.setNivel(Integer.parseInt(vpm.nivel_E.getValue().toString()));
            pm.setOrden(Integer.parseInt(vpm.orden_E.getValue().toString()));
            pm.setCostoUnit(Float.parseFloat(vpm.costoUnit_E.getValue().toString()));

            Partida p = partidaDAO.listarId(varCodCiaGlobalDeLogin, pm.getCodPartida(), "E");
            pm.settUnitMed(p.gettUniMed());
            pm.seteUnitMed(p.geteUniMed());

            // Check if selected item is null or empty before using it
            Object selectedItem = vpm.vigente_E.getSelectedItem();
            if (selectedItem == null) {
                // Handle the null case, e.g., set a default value or show an error message
                vig = '0'; // Assuming '0' as default for non-vigente
            } else {
                // Safely convert to string and perform the comparison
                String selectedString = selectedItem.toString();
                vig = "Vigente".equals(selectedString) ? '1' : '0';
            }
            pm.setVigente(vig);
        }
        if (pm.getCodPartida() == pm.getPadCodPartida()) {
            showMessage1("La partida no puede ser padre de si misma.");
            return;
        }
        if (partidaMezclaDAO.add(pm) == 1) {
            showMessage2("Partida_Mezcla registrada correctamente");
            vaciarCampos();
        } else {
            showMessage1("Error al registrar el Partida_Mezcla ");
        }
    }

    private boolean showMessage1(String message) {
        if(Frame.getFrames().length > 1) {
            Mensaje1 obj = new Mensaje1(Frame.getFrames()[1], true);
            obj.showMessage(message);
            return obj.isAceptar();
        } else {
            Mensaje1 obj = new Mensaje1(Frame.getFrames()[0], true);
            obj.showMessage(message);
            return obj.isAceptar();
        }
    }

    private boolean showMessage2(String message) {
        if (Frame.getFrames().length > 1) {
            Mensaje2 obj = new Mensaje2(Frame.getFrames()[1], true);
            obj.showMessage(message);
            return obj.isAceptar();
        } else {
            Mensaje2 obj = new Mensaje2(Frame.getFrames()[0], true);
            obj.showMessage(message);
            return obj.isAceptar();
        }
    }

    public void actualizarDatos(String tip) {
        int fila, codPartida, corr, pad;
        char vig;
        Partida_Mezcla pm = new Partida_Mezcla();
        if (tip == "I") {
            String padCodPartida_I;
            fila = vpm.tablaPartida_Mezcla_I.getSelectedRow();
            if (fila != -1) {
                corr = Integer.parseInt(vpm.tablaPartida_Mezcla_I.getValueAt(fila, 0).toString());
                codPartida = Integer.parseInt(vpm.tablaPartida_Mezcla_I.getValueAt(fila, 2).toString());
                padCodPartida_I = vpm.tablaPartida_Mezcla_I.getValueAt(fila, 3).toString();
                if (padCodPartida_I.equals("NULL")) {
                    pad = 0;
                } else {
                    pad = Integer.parseInt(vpm.tablaPartida_Mezcla_I.getValueAt(fila, 3).toString());
                }
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(codPartida);
                pm.setCorr(corr);
                pm.setNivel(Integer.parseInt(vpm.nivel_I.getValue().toString()));
                pm.setOrden(Integer.parseInt(vpm.orden_I.getValue().toString()));
                pm.setCostoUnit(Float.parseFloat(vpm.costoUnit_I.getValue().toString()));

                Object selectedItem2 = vpm.vigente_I.getSelectedItem();
                if (selectedItem2 == null) {
                    vig = '1'; // Assuming '0' as default for non-vigente
                } else {
                    String selectedString = selectedItem2.toString();
                    vig = "Vigente".equals(selectedString) ? '1' : '0';
                }
                pm.setVigente(vig);

                if (partidaMezclaDAO.busOrden(varCodCiaGlobalDeLogin, tip, pad, pm.getNivel(), pm.getOrden()) == true) {
                    Partida_Mezcla pmI = partidaMezclaDAO.listarOrden(varCodCiaGlobalDeLogin, "I", pad,
                            pm.getNivel(), pm.getOrden());
                    pmI.setOrden(ordenI);
                    partidaMezclaDAO.actualizar(pmI);
                }
                if (partidaMezclaDAO.actualizar(pm) == 1) {
                    showMessage2("Partida_Mezcla actualizada correctamente");
                    vaciarCampos();
                } else {
                    showMessage1("Error al actualizar Partida_Mezcla");
                }
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        } else {
            fila = vpm.tablaPartida_Mezcla_E.getSelectedRow();
            if (fila != -1) {
                System.out.println("Hay filas seleccionadas.");
                corr = Integer.parseInt(vpm.tablaPartida_Mezcla_E.getValueAt(fila, 0).toString());
                codPartida = Integer.parseInt(vpm.tablaPartida_Mezcla_E.getValueAt(fila, 2).toString());
                if ((vpm.tablaPartida_Mezcla_E.getValueAt(fila, 3).toString()) == "NULL") {
                    pad = 0;
                } else {
                    pad = Integer.parseInt(vpm.tablaPartida_Mezcla_E.getValueAt(fila, 3).toString());
                }
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(codPartida);
                pm.setCorr(corr);
                pm.setNivel(Integer.parseInt(vpm.nivel_E.getValue().toString()));
                pm.setOrden(Integer.parseInt(vpm.orden_E.getValue().toString()));
                pm.setCostoUnit(Float.parseFloat(vpm.costoUnit_E.getValue().toString()));
                System.out.println(pm.getCostoUnit());

                Object selectedItem2 = vpm.vigente_E.getSelectedItem();
                if (selectedItem2 == null) {
                    vig = '0'; // Assuming '0' as default for non-vigente
                } else {
                    String selectedString = selectedItem2.toString();
                    vig = "Vigente".equals(selectedString) ? '1' : '0';
                }

                pm.setVigente(vig);
                if (partidaMezclaDAO.busOrden(varCodCiaGlobalDeLogin, tip, pad, pm.getNivel(), pm.getOrden()) == true) {
                    Partida_Mezcla pmE = partidaMezclaDAO.listarOrden(varCodCiaGlobalDeLogin, "E", pad,
                            pm.getNivel(), pm.getOrden());
                    pmE.setOrden(ordenE);
                    partidaMezclaDAO.actualizar(pmE);
                }
                if (partidaMezclaDAO.actualizar(pm) == 1) {
                    showMessage2("Partida_Mezcla actualizada correctamente");
                    vaciarCampos();
                } else {
                    showMessage1("Error al actualizar Partida_Mezcla");
                }
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        }
    }

    public void eliminarDatos(String tip) {
        int fila, cod, corr;
        if (tip == "I") {
            fila = vpm.tablaPartida_Mezcla_I.getSelectedRow();
            if (fila != -1) {
                corr = Integer.parseInt(vpm.tablaPartida_Mezcla_I.getValueAt(fila, 0).toString());
                cod = Integer.parseInt(vpm.tablaPartida_Mezcla_I.getValueAt(fila, 2).toString());
                partidaMezclaDAO.eliminarDatos(varCodCiaGlobalDeLogin, cod, tip, corr);
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        } else {
            fila = vpm.tablaPartida_Mezcla_E.getSelectedRow();
            System.out.println("La fila es" + fila);
            if (fila != -1) {
                System.out.println("Hay filas seleccionadas.");
                corr = Integer.parseInt(vpm.tablaPartida_Mezcla_E.getValueAt(fila, 0).toString());
                cod = Integer.parseInt(vpm.tablaPartida_Mezcla_E.getValueAt(fila, 2).toString());
                partidaMezclaDAO.eliminarDatos(varCodCiaGlobalDeLogin, cod, tip, corr);
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        }
    }

    public void vaciarCampos() {
        initListarPartidas();
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
        codPAD = Integer.parseInt(((SelectPartidas) item).getValue());
        if (codPAD == 0) {
            vpm.nivel_I.setValue(1);
            vpm.orden_I.setValue(partidaMezclaDAO.asignarOrden("I", 1, varCodCiaGlobalDeLogin));
            vpm.nivel_I.setEnabled(false);
            vpm.orden_I.setEnabled(false);
        }

        Object item2 = vpm.padCodPartida_E.getSelectedItem();
        codPAD = Integer.parseInt(((SelectPartidas) item2).getValue());
        if (codPAD == 0) {
            vpm.nivel_E.setValue(1);
            vpm.orden_E.setValue(partidaMezclaDAO.asignarOrden("E", 1, varCodCiaGlobalDeLogin));
            vpm.nivel_E.setEnabled(false);
            vpm.orden_E.setEnabled(false);
        }
    }

    int codPAD;

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == vpm.padCodPartida_I && vpm.padCodPartida_I.getSelectedItem() != null) {
            Object item2 = vpm.padCodPartida_I.getSelectedItem();
            codPAD = Integer.parseInt(((SelectPartidas) item2).getValue());
            if (codPAD == 0) {
                vpm.nivel_I.setValue(1);
                vpm.orden_I.setValue(partidaMezclaDAO.asignarOrden("I", 1, varCodCiaGlobalDeLogin));
                vpm.nivel_I.setEnabled(false);
                vpm.orden_I.setEnabled(false);
            } else {
                vpm.nivel_I.setValue(partidaMezclaDAO.asignarNivelNoNULL("I", codPAD, varCodCiaGlobalDeLogin));
                vpm.orden_I.setValue(partidaMezclaDAO.asignarOrdenNoNULL("I", codPAD, varCodCiaGlobalDeLogin));
                vpm.nivel_I.setEnabled(false);
                vpm.orden_I.setEnabled(false);
            }
        }

        if (e.getSource() == vpm.padCodPartida_E && vpm.padCodPartida_E.getSelectedItem() != null) {
            Object item2 = vpm.padCodPartida_E.getSelectedItem();
            codPAD = Integer.parseInt(((SelectPartidas) item2).getValue());
            if (codPAD == 0) {
                vpm.nivel_E.setValue(1);
                vpm.orden_E.setValue(partidaMezclaDAO.asignarOrden("E", 1, varCodCiaGlobalDeLogin));
                vpm.nivel_E.setEnabled(false);
                vpm.orden_E.setEnabled(false);
            } else {
                vpm.nivel_E.setValue(partidaMezclaDAO.asignarNivelNoNULL("E", codPAD, varCodCiaGlobalDeLogin));
                vpm.orden_E.setValue(partidaMezclaDAO.asignarOrdenNoNULL("E", codPAD, varCodCiaGlobalDeLogin));
                vpm.nivel_E.setEnabled(false);
                vpm.orden_E.setEnabled(false);
            }
        }
    }
}