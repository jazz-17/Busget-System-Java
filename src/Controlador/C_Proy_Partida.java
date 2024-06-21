package Controlador;

import Modelo.DAO.PartidaDAO;
import Modelo.DAO.Proy_PartidaDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Partida;
import Modelo.Proy_Partida;
import Modelo.Proyecto;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import Vistas.V_Proy_Partida;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Custom.SelectOption;

@SuppressWarnings("unchecked")
public class C_Proy_Partida implements ActionListener, KeyListener, MouseListener {

    Proy_PartidaDAO ppDAO = new Proy_PartidaDAO();
    PartidaDAO pDAO = new PartidaDAO();
    V_Proy_Partida vpp = new V_Proy_Partida();
    DefaultTableModel modelProy_PartidaI = new DefaultTableModel();
    DefaultTableModel modelProy_PartidaE = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorterI;
    TableRowSorter<DefaultTableModel> sorterE;

    List<Partida> partidas_E;
    List<Partida> partidas_I;

    List<Proyecto> proyectos;

    Boolean firstLoad = true;

    public C_Proy_Partida(V_Proy_Partida vpp) {
        this.vpp = vpp;
        this.vpp.btt_Actualizar_I.addActionListener(this);
        this.vpp.btt_Registrar_I.addActionListener(this);
        this.vpp.btt_Eliminar_I.addActionListener(this);
        this.vpp.tablaProy_Partida_I.addMouseListener(this);
        this.vpp.btt_Actualizar_E.addActionListener(this);
        this.vpp.btt_Registrar_E.addActionListener(this);
        this.vpp.btt_Eliminar_E.addActionListener(this);
        this.vpp.tablaProy_Partida_E.addMouseListener(this);
        this.vpp.actualizaTabla.addActionListener(this);
        this.vpp.nuevo.addActionListener(this);
        this.vpp.proyectoIng.addActionListener(this);
        this.vpp.proyectoEg.addActionListener(this);

        this.partidas_E = pDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "E");
        this.partidas_I = pDAO.listarPorCodCia(varCodCiaGlobalDeLogin, "I");
        this.proyectos = new ProyectoDAO().listarPorCodCia(varCodCiaGlobalDeLogin);
        init();
    }

    public void init() {
        initListarProyectos();
        initListarPartidas();
        if (proyectos.size() == 0) {
            JOptionPane.showMessageDialog(null, "No hay proyectos registrados", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            int pyto = proyectos.get(0).getCodPyto();
            initTabla("I", pyto, vpp.tablaProy_Partida_I, modelProy_PartidaI, sorterI);
            initTabla("E", pyto, vpp.tablaProy_Partida_E, modelProy_PartidaE, sorterE);
            this.firstLoad = false;
        }
        vpp.init();
    }

    public void initListarProyectos() {
        vpp.proyectoIng.removeAllItems();
        vpp.proyectoEg.removeAllItems();
        for (int i = 0; i < proyectos.size(); i++) {
            String nombrePyto = proyectos.get(i).getNomPyto();
            String codPyto = String.valueOf(proyectos.get(i).getCodPyto());
            vpp.proyectoIng.addItem(new SelectOption(nombrePyto, codPyto));
            vpp.proyectoEg.addItem(new SelectOption(nombrePyto, codPyto));
        }
    }

    public void initListarPartidas() {
        vpp.codPartida_I.removeAllItems();
        vpp.codPartida_E.removeAllItems();
        for (int i = 0; i < partidas_I.size(); i++) {
            String des = partidas_I.get(i).getDesPartida();
            String cod = partidas_I.get(i).getCodPartida() + "";
            vpp.codPartida_I.addItem(new SelectOption(des, cod));

        }
        for (int i = 0; i < partidas_E.size(); i++) {
            String des = partidas_E.get(i).getDesPartida();
            String cod = partidas_E.get(i).getCodPartida() + "";
            vpp.codPartida_E.addItem(new SelectOption(des, cod));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("DENTRO DE ACTION PARTIDA");
        if (e.getSource() == vpp.btt_Registrar_I) {
            registrarDatos("I");
            actualizarTabla("I");
        }
        if (e.getSource() == vpp.btt_Actualizar_I) {
            actualizarDatos("I");
            actualizarTabla("I");
        }
        if (e.getSource() == vpp.btt_Eliminar_I) {
            eliminarDatos("I");
            actualizarTabla("I");
        }
        if (e.getSource() == vpp.btt_Registrar_E) {
            registrarDatos("E");
            actualizarTabla("E");
        }
        if (e.getSource() == vpp.btt_Actualizar_E) {
            actualizarDatos("E");
            actualizarTabla("E");
        }
        if (e.getSource() == vpp.btt_Eliminar_E) {
            eliminarDatos("E");
            actualizarTabla("E");
        }
        if (e.getSource() == vpp.actualizaTabla) {
            // actualizarTabla();
        }
        if (e.getSource() == vpp.nuevo) {
            vaciarCampos();
            // actualizarTabla();
        }
        if (e.getSource() == vpp.proyectoIng) {
            if (!firstLoad) {
                actualizarTabla("I");
            }
        }
        if (e.getSource() == vpp.proyectoEg) {
            if (!firstLoad) {
                actualizarTabla("E");
            }
        }
    }

    public void actualizarTabla(String tip) {
        if (tip.equals("I")) {
            limpiarTabla(modelProy_PartidaI);
            int pyto = Integer.parseInt(((SelectOption) vpp.proyectoIng.getSelectedItem()).getValue());
            initTabla("I", pyto, vpp.tablaProy_Partida_I, modelProy_PartidaI, sorterI);
        } else {
            limpiarTabla(modelProy_PartidaE);
            int pyto = Integer.parseInt(((SelectOption) vpp.proyectoEg.getSelectedItem()).getValue());
            initTabla("E", pyto, vpp.tablaProy_Partida_E, modelProy_PartidaE, sorterE);
        }
        System.out.println("Refrescando tabla automaticamente.");
    }

    public void initTabla(String tip, int pyto, Modelo.DesignTable.Tabla tabla, DefaultTableModel tableModel,
            TableRowSorter<DefaultTableModel> sorter) {
        new SwingWorker<List<Proy_Partida>, Void>() {
            @Override
            public List<Proy_Partida> doInBackground() {
                return ppDAO.listarPorCodCiaYProyecto(varCodCiaGlobalDeLogin, pyto, tip);
            }

            @Override
            public void done() {
                try {
                    DefaultTableModel localModel = tableModel;
                    TableRowSorter<DefaultTableModel> localSorter = sorter;

                    localModel = (DefaultTableModel) tabla.getModel();
                    localSorter = new TableRowSorter<>(localModel);

                    tabla.setRowSorter(localSorter);
                    limpiarTabla(localModel);

                    List<Proy_Partida> lista = get();
                    Object[] o = new Object[5];
                    for (Proy_Partida proyPartida : lista) {
                        o[0] = proyPartida.getCodPartida();
                        o[1] = proyPartida.getDescripcion();
                        o[2] = proyPartida.getNroVersion();

                        if (proyPartida.getCodEstado().equals("1")) {
                            o[3] = "Disponible";
                        } else {
                            o[3] = (proyPartida.getCodEstado().equals("2")) ? "No Disp." : "Reservado";
                        }
                        o[4] = (proyPartida.getVigente()) == '1' ? "Si" : "No";
                        localModel.addRow(o);
                    }
                    tabla.setModel(localModel);
                } catch (Exception e) {
                    System.out.println(e.toString());
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
        int fila, ver, cod;
        String descripcion;
        if (e.getSource() == vpp.tablaProy_Partida_I) {
            fila = vpp.tablaProy_Partida_I.getSelectedRow();

            cod = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila, 0).toString());
            descripcion = vpp.tablaProy_Partida_I.getValueAt(fila, 1).toString();
            ver = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila, 2).toString());
            vpp.codPartida_I.setSelectedItem(new SelectOption(descripcion, cod + ""));
            vpp.nroVersion_I.setValue(ver);
        }

        if (e.getSource() == vpp.tablaProy_Partida_E) {
            fila = vpp.tablaProy_Partida_E.getSelectedRow();

            cod = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila, 0).toString());
            descripcion = vpp.tablaProy_Partida_E.getValueAt(fila, 1).toString();
            ver = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila, 2).toString());
            vpp.codPartida_E.setSelectedItem(new SelectOption(descripcion, cod + ""));
            vpp.nroVersion_E.setValue(ver);
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
        Proy_Partida pm = new Proy_Partida();
        if (tip == "I") {
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);

            Object item1 = vpp.proyectoIng.getSelectedItem();
            int codPyto = Integer.parseInt(((SelectOption) item1).getValue());
            pm.setCodPyto(codPyto);

            Object item = vpp.codPartida_I.getSelectedItem();
            int codPartida = Integer.parseInt(((SelectOption) item).getValue());
            pm.setCodPartida(codPartida);
            // pm.setCodPartida(Integer.parseInt(vpp.codPartida_I.getSelectedItem().toString()));

            pm.setNroVersion(Integer.parseInt(vpp.nroVersion_I.getValue().toString()));
            pm.setTabEstado("-1");
            pm.setCodEstado("1");
            Partida p = pDAO.listarId(varCodCiaGlobalDeLogin, pm.getCodPartida(), "I");
            pm.setCodPartidas(p.getCodPartidas());
            pm.setVigente(p.getVigente());
        } else {
            pm.setCodCia(varCodCiaGlobalDeLogin);
            pm.setIngEgr(tip);
            Object item1 = vpp.proyectoEg.getSelectedItem();
            int codPyto = Integer.parseInt(((SelectOption) item1).getValue());
            pm.setCodPyto(codPyto);

            Object item = vpp.codPartida_E.getSelectedItem();
            int codPartida = Integer.parseInt(((SelectOption) item).getValue());
            pm.setCodPartida(codPartida);
            // pm.setCodPartida(Integer.parseInt(vpp.codPartida_E.getSelectedItem().toString()));

            pm.setNroVersion(Integer.parseInt(vpp.nroVersion_E.getValue().toString()));
            pm.setTabEstado("-1");
            pm.setCodEstado("1");
            Partida p = pDAO.listarId(varCodCiaGlobalDeLogin, pm.getCodPartida(), "E");
            pm.setCodPartidas(p.getCodPartidas());
            pm.setVigente(p.getVigente());
        }
        if (ppDAO.add(pm) == 1) {
            showMessage2("Proy_Partida registrado correctamente");
            vaciarCampos();
        }
    }

    private boolean showMessage1(String message) {
        JOptionPane.showMessageDialog(null, message, "Mensaje", JOptionPane.ERROR_MESSAGE);
        return true;
    }

    private boolean showMessage2(String message) {
        JOptionPane.showMessageDialog(null, message, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public void actualizarDatos(String tip) {
        int fila, pyto, cod;
        Proy_Partida pm = new Proy_Partida();
        if (tip == "I") {
            fila = vpp.tablaProy_Partida_I.getSelectedRow();
            if (fila != -1) {

                Object proyecto = vpp.proyectoIng.getSelectedItem();
                pyto = Integer.parseInt(((SelectOption) proyecto).getValue());

                cod = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila, 0).toString());
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setCodPyto(pyto);
                pm.setNroVersion(Integer.parseInt(vpp.nroVersion_I.getValue().toString()));
                if (ppDAO.actualizar(pm) == 1) {
                    showMessage2("Proy_Partida registrado correctamente");
                    vaciarCampos();
                }
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        } else {
            fila = vpp.tablaProy_Partida_E.getSelectedRow();
            if (fila != -1) {

                Object proyecto = vpp.proyectoEg.getSelectedItem();
                pyto = Integer.parseInt(((SelectOption) proyecto).getValue());
                cod = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila, 0).toString());
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setCodPyto(pyto);
                pm.setNroVersion(Integer.parseInt(vpp.nroVersion_E.getValue().toString()));
                if (ppDAO.actualizar(pm) == 1) {
                    vaciarCampos();
                } else {
                    showMessage1("Error al registrar Proy_Partida");
                }
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        }
    }

    public void eliminarDatos(String tip) {
        int fila, cod, pyto, ver;
        if (tip == "I") {
            fila = vpp.tablaProy_Partida_I.getSelectedRow();
            if (fila != -1) {
                Object proyecto = vpp.proyectoIng.getSelectedItem();
                pyto = Integer.parseInt(((SelectOption) proyecto).getValue());
                cod = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila, 0).toString());
                ver = Integer.parseInt(vpp.tablaProy_Partida_I.getValueAt(fila, 2).toString());
                ppDAO.eliminarDatos(varCodCiaGlobalDeLogin, cod, tip, pyto, ver);
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        } else {
            fila = vpp.tablaProy_Partida_E.getSelectedRow();
            if (fila != -1) {
                Object proyecto = vpp.proyectoEg.getSelectedItem();
                pyto = Integer.parseInt(((SelectOption) proyecto).getValue());
                cod = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila, 0).toString());
                ver = Integer.parseInt(vpp.tablaProy_Partida_E.getValueAt(fila, 2).toString());
                ppDAO.eliminarDatos(varCodCiaGlobalDeLogin, cod, tip, pyto, ver);
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        }
    }

    public void vaciarCampos() {
        vpp.nroVersion_E.setValue(1);
        vpp.nroVersion_I.setValue(1);
        vpp.nroVersion_I.setModel(new SpinnerNumberModel(1, 1, 9, 1));

        vpp.nroVersion_E.setModel(new SpinnerNumberModel(1, 1, 9, 1));
    }
}