package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import Modelo.PPartida;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PPartidaDAO implements CRUD<PPartida> {

    // ConectarOracle conexion=new ConectarOracle();
    ConectarOracle conexion = ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;

    @Override
    public List<PPartida> listar() {
        List<PPartida> lista = new ArrayList<>();
        String sql = "SELECT * FROM PPARTIDA order by CODCIA";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                PPartida pm = new PPartida();
                pm.setCodCia(rs.getInt(1));
                pm.setIngEgr(rs.getString(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodPartidas(rs.getString(4));
                pm.setDesPartida(rs.getString(5));
                pm.setFlgCC(rs.getString(6));
                pm.setNivel(rs.getInt(7));
                pm.settUniMed(rs.getString(8));
                pm.seteUniMed(rs.getString(9));
                pm.setSemilla(rs.getInt(10));
                pm.setVigente(rs.getString(11).charAt(0));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println("terminando la lista");
        System.out.println(conexion.getValue());
        return lista;
    }

    @Override
    public int add(PPartida p) {
        System.out.println("{call INSERTAR_PPARTIDA(?,?,?,?,?,?)}");
        try {
            con = conexion.conectar();
            myCall = con.prepareCall("{call INSERTAR_PPARTIDA(?,?,?,?,?,?)}");
            myCall.setInt(1, p.getCodCia());
            myCall.setString(2, p.getIngEgr());
            myCall.setString(3, p.getDesPartida());
            myCall.setString(4, p.gettUniMed());
            myCall.setString(5, p.geteUniMed());
            myCall.setString(6, String.valueOf(p.getVigente()));
            myCall.execute();
            myCall.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

            return 0;
        }
        return 1;
    }

    @Override
    public int actualizar(PPartida p) {
        String sql1 = "update PPARTIDA set DesPartida=?, TUniMed=?, EUniMed=?, Vigente=? where CodCIA=? AND CodPartida=? AND IngEgr=?";
        System.out.println(sql1);
        try {
            con = conexion.conectar();
            pst = con.prepareStatement(sql1);

            // Set parameters using pst, not myCall
            pst.setString(1, p.getDesPartida());
            pst.setString(2, p.gettUniMed());
            pst.setString(3, p.geteUniMed());
            pst.setString(4, String.valueOf(p.getVigente()));
            pst.setInt(5, p.getCodCia());
            pst.setInt(6, p.getCodPartida());
            pst.setString(7, p.getIngEgr());

            pst.executeUpdate(); // Use executeUpdate for update statements

            // Close resources
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

            return 0;
        }
        return 1;
    }

    @Override
    public void eliminar(int id) {

    }

    public void eliminarDatos(int cia, int cod, String tip) {
        String sql1 = "DELETE from PPARTIDA where CodCIA=" + cia + " AND CodPartida=" + cod + " AND IngEgr='" + tip
                + "'";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql1);
            rs = ps.executeQuery(sql1);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "PPartida eliminado con exito.");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public PPartida listarId(int cia, int id, String tip) {
        PPartida pm = new PPartida();
        String sql = "SELECT p.DesPartida,p.tUniMed,p.eUniMed,p.vigente,p.CODPARTIDAS, el.denele, t.dentab FROM PPARTIDA p "
                + "LEFT JOIN tabs t on t.codtab=p.tunimed "
                + "LEFT JOIN elementos el on el.codelem = p.eunimed AND el.codtab = p.tunimed "
                + "WHERE p.CODCIA=" + cia
                + " AND p.CODPARTIDA=" + id + " AND p.INGEGR='" + tip + "'";
        try {
            con = conexion.conectar();
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                pm.setDesPartida(rs.getString(1));
                pm.settUniMed(rs.getString(2));
                pm.seteUniMed(rs.getString(3));
                pm.setVigente(rs.getString(4).charAt(0));
                pm.setCodPartidas(rs.getString(5));
                pm.setDescElemento(rs.getString(6));
                pm.setDescTab(rs.getString(7));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return pm;
    }

    public PPartida listarIdv2(int cia, int id) {
        PPartida pm = new PPartida();
        String sql = "SELECT DesPartida,tUniMed,eUniMed,vigente,CODPARTIDAS FROM PPARTIDA WHERE CODCIA=" + cia
                + " AND CODPARTIDA=" + id;
        try {
            con = conexion.conectar();
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                pm.setDesPartida(rs.getString(1));
                pm.settUniMed(rs.getString(2));
                pm.seteUniMed(rs.getString(3));
                pm.setVigente(rs.getString(4).charAt(0));
                pm.setCodPartidas(rs.getString(5));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return pm;
    }

    public String listarDescPartida(int cia, int id, String tip) {
        // List<String> lista=new ArrayList<>();
        String desc = "";
        String sql = "SELECT DesPartida FROM PPARTIDA WHERE CODCIA=" + cia + " AND CODPARTIDA=" + id + " AND INGEGR='"
                + tip + "'";
        try {
            con = conexion.conectar();
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                // lista.add(rs.getString(1));
                desc = rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return desc;
    }

    public List<PPartida> listarPorCodCia(int id, String tip) {
        List<PPartida> lista = new ArrayList<>();
        System.out.println("-------Fetching ppartidas by CIA------");
        String sql = "SELECT "
                + "p.CodPartida,p.CodPartidas,p.DesPartida,p.tUniMed,p.eUniMed,p.Vigente, el.denele, t.dentab "
                + "FROM PPARTIDA p "
                + "LEFT JOIN tabs t on t.codtab=p.tunimed "
                + "LEFT JOIN elementos el on el.codelem = p.eunimed AND el.codtab = p.tunimed "
                + "WHERE p.CodCIA=" + id + " AND p.IngEgr='" + tip + "' order by p.codPartida";
        System.out.println(sql);
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                PPartida p = new PPartida();
                p.setCodPartida(rs.getInt(1));
                p.setCodPartidas(rs.getString(2));
                p.setDesPartida(rs.getString(3));
                p.settUniMed(rs.getString(4));
                p.seteUniMed(rs.getString(5));
                p.setVigente(rs.getString(6).charAt(0));
                p.setDescElemento(rs.getString(7));
                p.setDescTab(rs.getString(8));
                lista.add(p);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return lista;
    }

    public List<PPartida> listarPartidas(String id) {
        List<PPartida> lista = new ArrayList<>();
        String sql = "SELECT * FROM PPARTIDA WHERE INGEGR=" + id;
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                PPartida pm = new PPartida();
                pm.setCodCia(rs.getInt(1));
                pm.setIngEgr(rs.getString(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodPartidas(rs.getString(4));
                pm.setDesPartida(rs.getString(5));
                pm.setFlgCC(rs.getString(6));
                pm.setNivel(rs.getInt(7));
                pm.settUniMed(rs.getString(8));
                pm.seteUniMed(rs.getString(9));
                pm.setSemilla(rs.getInt(10));
                pm.setVigente(rs.getString(11).charAt(0));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println("terminando la lista");
        return lista;
    }

}