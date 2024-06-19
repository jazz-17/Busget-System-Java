package Controlador;

import Modelo.DAO.Partida_MezclaDAO;
import Modelo.DAO.Proy_Partida_MezclaDAO;
import Modelo.DAO.ProyectoDAO;
import Modelo.DAO.TabsDAO;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Modelo.Partida_Mezcla;
import Modelo.Proy_Partida_Mezcla;
import Modelo.Proyecto;
import Vistas.V_GeneratorTree;
import static Vistas.V_Login.varCodCiaGlobalDeLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Vistas.V_Proy_Partida_Mezcla;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.html.parser.Element;

import Custom_by_me.SelectOption;
import Custom_by_me.SelectTabs;
import Modelo.Elementos;
import Modelo.Partida;
import Modelo.Tabs;
import Modelo.DAO.ElementosDAO;
import Modelo.DAO.PartidaDAO;

@SuppressWarnings("unchecked")
public class C_Proy_Partida_Mezcla implements ItemListener, ActionListener, KeyListener, MouseListener {

    Proy_Partida_MezclaDAO ppmDAO = new Proy_Partida_MezclaDAO();
    Partida_MezclaDAO pmDAO = new Partida_MezclaDAO();
    ProyectoDAO pytoDAO = new ProyectoDAO();
    V_Proy_Partida_Mezcla vppm = new V_Proy_Partida_Mezcla();
    DefaultTableModel modelProyPartidaMezclaI = new DefaultTableModel();
    DefaultTableModel modelProyPartidaMezclaE = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> sorterI;
    TableRowSorter<DefaultTableModel> sorterE;
    List<Proyecto> proyectos;
    Boolean firstLoad;

    List<Tabs> tabs;

    public C_Proy_Partida_Mezcla(V_Proy_Partida_Mezcla vppm) {
        this.vppm = vppm;
        this.vppm.btt_Actualizar_I.addActionListener(this);
        this.vppm.btt_Registrar_I.addActionListener(this);
        this.vppm.btt_Eliminar_I.addActionListener(this);
        this.vppm.tablaProy_Partida_Mezcla_I.addMouseListener(this);
        this.vppm.padCodPartida_I.addItemListener(this);

        this.vppm.btt_Actualizar_E.addActionListener(this);
        this.vppm.btt_Registrar_E.addActionListener(this);
        this.vppm.btt_Eliminar_E.addActionListener(this);
        this.vppm.tablaProy_Partida_Mezcla_E.addMouseListener(this);
        this.vppm.padCodPartida_E.addItemListener(this);

        this.vppm.actualizaTablaIng.addActionListener(this);
        this.vppm.generaArbolIng.addActionListener(this);
        this.vppm.actualizaTablaEgr.addActionListener(this);
        this.vppm.generaArbolEgr.addActionListener(this);
        this.vppm.nuevoIng.addActionListener(this);
        this.vppm.nuevoEgr.addActionListener(this);

        this.vppm.proyectoIngreso.addActionListener(this);
        this.vppm.proyectoEgreso.addActionListener(this);

        this.proyectos = pytoDAO.listarPorCodCia(varCodCiaGlobalDeLogin);
        this.firstLoad = true;
        init();
    }

    public void init() {
        initListarProyectos();
        initListarPartidas();
        getInitialMezcla();
        vppm.init();
    }

    public void getInitialMezcla() {
        if (proyectos.size() == 0) {
            JOptionPane.showMessageDialog(null, "No hay proyectos registrados", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            Proyecto proyecto = proyectos.get(0);
            int pyto = proyecto.getCodPyto();
            generateIngresoTableByProject(pyto);
            generateEgresoTableByProject(pyto);
            this.firstLoad = false;
        }
    }

    public void initListarProyectos() {
        vppm.proyectoIngreso.removeAllItems();
        vppm.proyectoEgreso.removeAllItems();
        for (int i = 0; i < proyectos.size(); i++) {
            String nombre = proyectos.get(i).getNomPyto();
            String codPyto = proyectos.get(i).getCodPyto() + "";
            vppm.proyectoIngreso.addItem(new SelectOption(nombre, codPyto));
            vppm.proyectoEgreso.addItem(new SelectOption(nombre, codPyto));
        }
    }

    public void initListarPartidas() {
        vppm.padCodPartida_I.removeAllItems();
        vppm.padCodPartida_E.removeAllItems();
        List<Partida_Mezcla> partidasMezclaI = pmDAO.listarMezclas(varCodCiaGlobalDeLogin, "I");
        List<Partida_Mezcla> partidasMezclaE = pmDAO.listarMezclas(varCodCiaGlobalDeLogin, "E");
        List<Partida_Mezcla> partidasMezclaRaizI = pmDAO.listarMezclasPadre(varCodCiaGlobalDeLogin, "I");
        List<Partida_Mezcla> partidasMezclaRaizE = pmDAO.listarMezclasPadre(varCodCiaGlobalDeLogin, "E");
        if (partidasMezclaI.size() == 0) {
            for (int i = 0; i < partidasMezclaRaizI.size(); i++) {
                Partida_Mezcla partida = partidasMezclaRaizI.get(i);
                String descripcion = partida.getDescripcion();
                String cod = partida.getCodPartida() + "";

                SelectOption option = new SelectOption(descripcion, cod);
                vppm.padCodPartida_I.addItem(option);
            }
        } else {
            for (int i = 0; i < partidasMezclaI.size(); i++) {
                Partida_Mezcla partida = partidasMezclaI.get(i);
                String descripcion = partida.getDescripcion();
                String cod = partida.getPadCodPartida() + "";

                SelectOption option = new SelectOption(descripcion, cod);
                System.out.println("option: " + option.toString());
                vppm.padCodPartida_I.addItem(option);
            }
        }

        if (partidasMezclaE.size() == 0) {
            for (int i = 0; i < partidasMezclaRaizE.size(); i++) {
                String descripcion = partidasMezclaRaizE.get(i).getDescripcion();
                String cod = partidasMezclaRaizE.get(i).getCodPartida() + "";
                vppm.padCodPartida_E.addItem(new SelectOption(descripcion, cod));
            }
        } else {
            for (int i = 0; i < partidasMezclaE.size(); i++) {
                String descripcion = partidasMezclaE.get(i).getDescripcion();
                String cod = partidasMezclaE.get(i).getPadCodPartida() + "";
                vppm.padCodPartida_E.addItem(new SelectOption(descripcion, cod));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vppm.btt_Registrar_I) {
            registrarDatos("I");
            actualizarTabla("I");
        }
        if (e.getSource() == vppm.btt_Actualizar_I) {
            actualizarDatos("I");
            actualizarTabla("I");
        }
        if (e.getSource() == vppm.btt_Eliminar_I) {
            eliminarDatos("I");
            actualizarTabla("I");
        }
        if (e.getSource() == vppm.btt_Registrar_E) {
            registrarDatos("E");
            actualizarTabla("E");
        }
        if (e.getSource() == vppm.btt_Actualizar_E) {
            actualizarDatos("E");
            actualizarTabla("E");
        }
        if (e.getSource() == vppm.btt_Eliminar_E) {
            eliminarDatos("E");
            actualizarTabla("E");
        }
        if (e.getSource() == vppm.actualizaTablaIng) {
            actualizarTabla("I");
        }
        if (e.getSource() == vppm.generaArbolIng) {
            generaArbolIngreso();
        }
        if (e.getSource() == vppm.actualizaTablaEgr) {
            actualizarTabla("E");
        }
        if (e.getSource() == vppm.generaArbolEgr) {
            generaArbolEgreso();
        }
        if (e.getSource() == vppm.nuevoEgr) {
            vaciarCampos();
            actualizarTabla("E");
        }
        if (e.getSource() == vppm.nuevoIng) {
            vaciarCampos();
            actualizarTabla("I");
        }
        if (e.getSource() == vppm.proyectoIngreso) {
            Object item = vppm.proyectoIngreso.getSelectedItem(); // type SelectOption
            int pyto = getValueFromCombobox(item);
            generateIngresoTableByProject(pyto);

        }
        if (e.getSource() == vppm.proyectoEgreso) {
            Object item = vppm.proyectoEgreso.getSelectedItem(); // type SelectOption
            int pyto = getValueFromCombobox(item);
            generateEgresoTableByProject(pyto);
        }
    }

    public void actualizarTabla(String tip) {
        if(tip.equals("I")){
            Object item = vppm.proyectoIngreso.getSelectedItem(); // type SelectOption
            String value = ((SelectOption) item).getValue();
            int pyto1 = Integer.parseInt(value);
            generateIngresoTableByProject(pyto1);
        }else{
            Object item2 = vppm.proyectoEgreso.getSelectedItem(); // type SelectOption
            String value2 = ((SelectOption) item2).getValue();
            int pyto2 = Integer.parseInt(value2);
            generateEgresoTableByProject(pyto2);
        }
        System.out.println("Refrescando tabla automaticamente.");
    }

    public void generateIngresoTableByProject(int proyecto) {
        List<Proy_Partida_Mezcla> listaI = ppmDAO.listarPorCodCiaYProyecto(varCodCiaGlobalDeLogin, proyecto, "I");
        modelProyPartidaMezclaI = (DefaultTableModel) vppm.tablaProy_Partida_Mezcla_I.getModel();
        Object[] o = new Object[11];
        sorterI = new TableRowSorter<>(modelProyPartidaMezclaI);
        vppm.tablaProy_Partida_Mezcla_I.setRowSorter(sorterI);
        limpiarTabla(modelProyPartidaMezclaI);
        for (int i = 0; i < listaI.size(); i++) {
            o[0] = listaI.get(i).getCodPartida();
            o[1] = listaI.get(i).getDescription();
            o[2] = listaI.get(i).getNroVersion();
            int padCodPartida = listaI.get(i).getPadCodPartida();
            if (padCodPartida == 0) {
                o[3] = "NULL";
            } else {
                o[3] = listaI.get(i).getPadDescription();
            }
            o[4] = listaI.get(i).getCorr();
            o[5] = listaI.get(i).getNivel();
            o[6] = listaI.get(i).getOrden();
            o[7] = listaI.get(i).getUnidadMedida();
            o[8] = listaI.get(i).getCostoUnit();
            o[9] = listaI.get(i).getCant();
            o[10] = listaI.get(i).getCostoTot();
            modelProyPartidaMezclaI.addRow(o);
        }
        vppm.tablaProy_Partida_Mezcla_I.setModel(modelProyPartidaMezclaI);
    }

    public void generateEgresoTableByProject(int proyecto) {
        List<Proy_Partida_Mezcla> listaE = ppmDAO.listarPorCodCiaYProyecto(varCodCiaGlobalDeLogin, proyecto, "E");
        modelProyPartidaMezclaE = (DefaultTableModel) vppm.tablaProy_Partida_Mezcla_E.getModel();
        Object[] o = new Object[11];
        sorterE = new TableRowSorter<>(modelProyPartidaMezclaE);
        vppm.tablaProy_Partida_Mezcla_E.setRowSorter(sorterE);
        limpiarTabla(modelProyPartidaMezclaE);
        for (int i = 0; i < listaE.size(); i++) {
            o[0] = listaE.get(i).getCodPartida();
            o[1] = listaE.get(i).getDescription();
            o[2] = listaE.get(i).getNroVersion();
            int padCodPartida = listaE.get(i).getPadCodPartida();
            if (padCodPartida == 0) {
                o[3] = "NULL";
            } else {
                o[3] = listaE.get(i).getPadDescription();
            }
            o[4] = listaE.get(i).getCorr();
            o[5] = listaE.get(i).getNivel();
            o[6] = listaE.get(i).getOrden();
            o[7] = listaE.get(i).getUnidadMedida();
            o[8] = listaE.get(i).getCostoUnit();
            o[9] = listaE.get(i).getCant();
            o[10] = listaE.get(i).getCostoTot();
            modelProyPartidaMezclaE.addRow(o);
        }
        vppm.tablaProy_Partida_Mezcla_E.setModel(modelProyPartidaMezclaE);
    }

    public void generaArbolIngreso() {
        int filaSelecIng, pyto;
        filaSelecIng = vppm.tablaProy_Partida_Mezcla_I.getSelectedRow();
        // pyto =
        // Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(filaSelecIng,
        // 0).toString()); // codPyto

        pyto = getValueFromCombobox(vppm.proyectoIngreso.getSelectedItem());
        // codPartida =
        // Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(filaSelecIng,3).toString());//codPartida
        V_GeneratorTree tg1 = new V_GeneratorTree(varCodCiaGlobalDeLogin, pyto, "I");
        tg1.setVisible(true);
        System.out.println("LA FILA DE INGRESO ES: " + filaSelecIng);
    }

    public void generaArbolEgreso() {
        int filaSelecEgr, pyto;
        filaSelecEgr = vppm.tablaProy_Partida_Mezcla_E.getSelectedRow();
        pyto = getValueFromCombobox(vppm.proyectoEgreso.getSelectedItem());
        // Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(filaSelecEgr,3).toString());//codPartida
        V_GeneratorTree tg2 = new V_GeneratorTree(varCodCiaGlobalDeLogin, pyto, "E");
        tg2.setVisible(true);
        System.out.println("LA FILA DE Egreso ES: " + filaSelecEgr);
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
        int fila, cor, cod, pyto, ver;
        if (e.getSource() == vppm.tablaProy_Partida_Mezcla_I) {
            fila = vppm.tablaProy_Partida_Mezcla_I.getSelectedRow();

            pyto = getValueFromCombobox(vppm.proyectoIngreso.getSelectedItem());
            cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila, 0).toString());
            ver = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila, 2).toString());
            cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila, 4).toString());
            System.out.println("PartidaMezcla = " + cod);
            Proy_Partida_Mezcla pmI = ppmDAO.listarId(varCodCiaGlobalDeLogin, "I", cod, cor, ver, pyto);

            String pad = vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila, 4).toString();
            vppm.proyectoIngreso.setSelectedItem(String.valueOf(pmI.getCodPyto()));
            if (pad == "NULL") {
                vppm.padCodPartida_I.setSelectedItem(pmI.getCodPartida());
            } else {
                vppm.padCodPartida_I.setSelectedItem(pmI.getPadCodPartida());
            }
            vppm.cantidad_I.setValue(pmI.getCant());
            vppm.nroVersion_I.setValue(pmI.getNroVersion());
        }

        if (e.getSource() == vppm.tablaProy_Partida_Mezcla_E) {
            fila = vppm.tablaProy_Partida_Mezcla_E.getSelectedRow();
            Object item = vppm.proyectoEgreso.getSelectedItem();
            pyto = getValueFromCombobox(item);
            ver = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila, 1).toString());
            cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila, 2).toString());
            cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila, 3).toString());

            String pad = vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila, 4).toString();
            System.out.println("PartidaMezcla = " + cor);
            Proy_Partida_Mezcla pmE = new Proy_Partida_MezclaDAO().listarId(varCodCiaGlobalDeLogin, "E", cod, cor, ver,
                    pyto);
            vppm.proyectoEgreso.setSelectedItem(String.valueOf(pmE.getCodPyto()));
            if (pad == "NULL") {
                vppm.padCodPartida_E.setSelectedItem(pmE.getCodPartida());
            } else {
                vppm.padCodPartida_E.setSelectedItem(pmE.getPadCodPartida());
            }
            vppm.cantidad_E.setValue(pmE.getCant());
            vppm.nroVersion_E.setValue(pmE.getNroVersion());
        }
    }

    public int getValueFromCombobox(Object item) {
        String value = ((SelectOption) item).getValue();
        return Integer.parseInt(value);
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
        Proy_Partida_Mezcla pm = new Proy_Partida_Mezcla();

        if (tip.equals("I")) {
            populateProyPartidaMezcla(pm, tip, vppm.proyectoIngreso, vppm.padCodPartida_I, vppm.nroVersion_I,
                    vppm.cantidad_I);
        } else {
            populateProyPartidaMezcla(pm, tip, vppm.proyectoEgreso, vppm.padCodPartida_E, vppm.nroVersion_E,
                    vppm.cantidad_E);
        }

        if (ppmDAO.add(pm) == 1) {
            showMessage2("Proy_Partida_Mezcla registrado correctamente");
            vaciarCampos();
        }
    }

    private void populateProyPartidaMezcla(Proy_Partida_Mezcla pm, String tip, JComboBox<?> proyectoComboBox,
            JComboBox<?> padCodPartidaComboBox, JSpinner nroVersionSpinner, JSpinner cantidadSpinner) {
        pm.setCodCia(varCodCiaGlobalDeLogin);
        pm.setIngEgr(tip);

        Object projectItem = proyectoComboBox.getSelectedItem();
        int pyto = getValueFromCombobox(projectItem);
        pm.setCodPyto(pyto);

        Object padCodPartidaItem = padCodPartidaComboBox.getSelectedItem();
        int padCodPartida = getValueFromCombobox(padCodPartidaItem);
        pm.setPadCodPartida(padCodPartida);

        pm.setNroVersion(Integer.parseInt(nroVersionSpinner.getValue().toString()));
        pm.setCant(Integer.parseInt(cantidadSpinner.getValue().toString()));
    }

    private boolean showMessage1(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        return true;
    }

    private boolean showMessage2(String message) {
        JOptionPane.showMessageDialog(null, message, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    public void printProyPartidaMezcla(Proy_Partida_Mezcla ppm){
        System.out.println("cia:  " +ppm.getCodCia());
        System.out.println("pyto:  " +ppm.getCodPyto());
        System.out.println("ing:  " +ppm.getIngEgr());
        System.out.println("version:  " +ppm.getNroVersion());
        System.out.println("codpartida:  " +ppm.getCodPartida());
        System.out.println("corr: "+ ppm.getCorr());
        System.out.println("padcod: "+ ppm.getPadCodPartida());
        System.out.println("nivel: "+ ppm.getNivel());
        System.out.println("orden: "+ ppm.getOrden());
        

    }
    public void actualizarDatos(String tip) {
        int fila, cod, cor, pyto;
        float costoUnitario;
        Proy_Partida_Mezcla pm = new Proy_Partida_Mezcla();
        if (tip == "I") {
            fila = vppm.tablaProy_Partida_Mezcla_I.getSelectedRow();
            if (fila != -1) {
                Object item = vppm.proyectoIngreso.getSelectedItem();
                if (item != null) {
                    pyto = getValueFromCombobox(item);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay proyecto seleccionado", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila, 0).toString());
                cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila, 4).toString());
                costoUnitario = Float.parseFloat(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila, 8).toString());
                pm.setCostoUnit(costoUnitario);
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setCorr(cor);
                pm.setCodPyto(pyto);
                pm.setCant(Integer.parseInt(vppm.cantidad_I.getValue().toString()));
                pm.setNroVersion(Integer.parseInt(vppm.nroVersion_I.getValue().toString()));
                pm.setCostoTot(pm.getCant() * pm.getCostoUnit());

                if (ppmDAO.actualizar(pm) == 1) {
                    showMessage2("Registro actualizado correctamente");
                    vaciarCampos();
                    generateIngresoTableByProject(pyto);
                }
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        } else {
            fila = vppm.tablaProy_Partida_Mezcla_E.getSelectedRow();
            if (fila != -1) {
                Object item = vppm.proyectoEgreso.getSelectedItem();
                if (item != null) {
                    pyto = getValueFromCombobox(item);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay proyecto seleccionado", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila, 0).toString());
                cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila, 4).toString());
                costoUnitario = Float.parseFloat(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila, 8).toString());
                pm.setCostoUnit(costoUnitario);
                pm.setCodCia(varCodCiaGlobalDeLogin);
                pm.setIngEgr(tip);
                pm.setCodPartida(cod);
                pm.setCorr(cor);
                pm.setCodPyto(pyto);

                pm.setCant(Integer.parseInt(vppm.cantidad_E.getValue().toString()));
                pm.setNroVersion(Integer.parseInt(vppm.nroVersion_E.getValue().toString()));
                pm.setCostoTot(pm.getCant() * pm.getCostoUnit());

                if (ppmDAO.actualizar(pm) == 1) {
                    showMessage2("Registro actualizado correctamente");
                    vaciarCampos();
                    generateEgresoTableByProject(pyto);
                }
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        }
    }

    public void eliminarDatos(String tip) {
        int fila, cod, cor, pyto, ver;
        if (tip == "I") {
            fila = vppm.tablaProy_Partida_Mezcla_I.getSelectedRow();
            if (fila != -1) {
                Object item = vppm.proyectoIngreso.getSelectedItem();
                pyto = getValueFromCombobox(item);
                cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila, 0).toString());
                ver = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila, 2).toString());
                cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_I.getValueAt(fila, 4).toString());
                ppmDAO.eliminarDatos(varCodCiaGlobalDeLogin, cod, tip, cor, pyto, ver);
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        } else {
            fila = vppm.tablaProy_Partida_Mezcla_E.getSelectedRow();
            if (fila != -1) {
                Object item = vppm.proyectoEgreso.getSelectedItem();
                pyto = getValueFromCombobox(item);
                cod = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila, 0).toString());
                ver = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila, 2).toString());
                cor = Integer.parseInt(vppm.tablaProy_Partida_Mezcla_E.getValueAt(fila, 4).toString());
                ppmDAO.eliminarDatos(varCodCiaGlobalDeLogin, cod, tip, cor, pyto, ver);
            } else {
                showMessage1("Debe seleccionar una fila");
            }
        }
    }

    public void vaciarCampos() {
        vppm.nroVersion_E.setValue(1);
        vppm.nroVersion_I.setValue(1);
        vppm.cantidad_E.setValue(1);
        vppm.cantidad_I.setValue(1);

        vppm.nroVersion_I.setModel(new SpinnerNumberModel(1, 1, 9, 1));
        vppm.cantidad_I.setModel(new SpinnerNumberModel(1, 1, 999, 1));

        vppm.nroVersion_E.setModel(new SpinnerNumberModel(1, 1, 9, 1));
        vppm.cantidad_E.setModel(new SpinnerNumberModel(1, 1, 999, 1));
    }

    int pyto;

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == vppm.proyectoIngreso) {
            if (vppm.proyectoIngreso.getSelectedIndex() != -1) {
                Object item = vppm.proyectoIngreso.getSelectedItem();
                int pyto = getValueFromCombobox(item);
                generateIngresoTableByProject(pyto);
            } else {
                vppm.proyectoIngreso.setSelectedItem(-1);
            }
        }

        if (e.getSource() == vppm.proyectoEgreso) {
            if (vppm.proyectoEgreso.getSelectedIndex() != -1) {
                Object item = vppm.proyectoEgreso.getSelectedItem();
                int pyto = getValueFromCombobox(item);
                generateEgresoTableByProject(pyto);
            } else {
                vppm.proyectoEgreso.setSelectedItem(-1);
            }
        }
    }

}
