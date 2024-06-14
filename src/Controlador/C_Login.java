package Controlador;

import Modelo.Cia;
import Modelo.DAO.CiaDAO;
import Modelo.DAO.EmpleadoDAO;
import Modelo.Message.Mensaje1;
import Modelo.Message.Mensaje2;
import Vistas.V_Login;
import Vistas.V_Principal;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class C_Login implements ActionListener, MouseListener, MouseMotionListener {

    V_Login vLog;
    CiaDAO ciaDAO = new CiaDAO();
    EmpleadoDAO emplDAO = new EmpleadoDAO();
    int xMouse, yMouse;

    // LoginDAO loginDAO=new LoginDAO();

    public C_Login(V_Login vl) {
        this.vLog = vl;
        this.vLog.loginF.login.addEventLogin(this);
        this.vLog.addMouseListener(this);
        this.vLog.addMouseMotionListener(this);
        this.vLog.init();
        initListarNombresCias();
    }

    public void initListarNombresCias() {
        this.vLog.loginF.login.comboboxCia.removeAllItems();
        List<Cia> lista = new CiaDAO().listar();
        for (int i = 0; i < lista.size(); i++) {
            this.vLog.loginF.login.comboboxCia.addItem(lista.get(i).getDesCia());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vLog.loginF.login.btIngresar) {
            checkUser();
            initListarNombresCias();
        }
    }

    public void checkUser() {
        if (this.vLog.loginF.login.checkUser()) { // check si relleno el usr y el pass
            login(this.vLog.loginF.login.getUserName(), this.vLog.loginF.login.getPassword());
        }
    }

    public void debugLogin() {
        int codCia = 1;
        V_Login.varCodCiaGlobalDeLogin = codCia;
        V_Login.varNombreCiaGlobalDeLogin = "Devenco";
        V_Principal vPrincipal = new V_Principal();

        vPrincipal.setVisible(true);
        this.vLog.dispose();
    }

    public void login(String username, String password) {
        if (this.vLog.loginF.login.comboboxCia.getSelectedItem() != null) { // Probar cuando no hay CIAS en la BDD.
            int codCia = ciaDAO.getCodCiaWithDesCia(this.vLog.loginF.login.comboboxCia.getSelectedItem().toString());
            if (username.equals(password)) {
                // Verificar que el empleado seleccionado sea de la empresa
                if (emplDAO.getPerteneceACia(codCia, Integer.parseInt(username))) {
                    V_Login.varCodCiaGlobalDeLogin = codCia;
                    V_Login.varNombreCiaGlobalDeLogin = ciaDAO.getCiaNombre(codCia);
                    showMessage2("¡USUARIO IDENTIFICADO!");
                    V_Principal vPrincipal = new V_Principal();
                    vPrincipal.setVisible(true);
                    this.vLog.dispose();
                } else {
                    if (username.equals("0") && password.equals("0")) {
                        V_Login.varCodCiaGlobalDeLogin = codCia;
                        V_Login.varNombreCiaGlobalDeLogin = ciaDAO.getCiaNombre(codCia);
                        showMessage2("¡MODO ADMINISTRADOR!");
                        V_Principal vPrincipal = new V_Principal();

                        vPrincipal.setVisible(true);
                        this.vLog.dispose();
                    } else {
                        showMessage1("EL EMPLEADO NO PERTENECE A LA CIA SELECCIONADA");
                        this.vLog.loginF.login.checkUserReset();
                    }
                }
            } else {
                showMessage1("¡USUARIO O PASSWORD INCORRECTA!");
                this.vLog.loginF.login.checkUserReset();
            }
        } else {
            showMessage1("¡NECESITA SELECCIONAR UNA CIA!");
            this.vLog.loginF.login.checkUserReset();
        }
    }

    private boolean showMessage1(String message) {
        Mensaje1 obj = new Mensaje1(Frame.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isAceptar();
    }

    private boolean showMessage2(String message) {
        Mensaje2 obj = new Mensaje2(Frame.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isAceptar();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == this.vLog) {
            xMouse = e.getX();
            yMouse = e.getY();
        }
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

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource() == this.vLog) {
            int x, y;
            x = e.getXOnScreen();
            y = e.getYOnScreen();
            this.vLog.setLocation(x - xMouse, y - yMouse);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        return;
    }

}
