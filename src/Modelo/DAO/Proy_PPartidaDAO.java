package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.Interface.CRUD;
import Modelo.Proy_PPartida;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

public class Proy_PPartidaDAO implements CRUD<Proy_PPartida> {

    // ConectarOracle conexion=new ConectarOracle();
    ConectarOracle conexion = ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;

    @Override
    public List<Proy_PPartida> listar() {
        List<Proy_PPartida> lista = new ArrayList<>();
        String sql = "SELECT * FROM PROY_PPARTIDA order by CODCIA";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Proy_PPartida pm = new Proy_PPartida();
                pm.setCodCia(rs.getInt(1));
                pm.setCodPyto(rs.getInt(2));
                pm.setNroVersion(rs.getInt(3));
                pm.setIngEgr(rs.getString(4));
                pm.setCodPartida(rs.getInt(5));
                pm.setCodPartidas(rs.getString(6));
                pm.setFlgCC(rs.getString(7));
                pm.setNivel(rs.getInt(8));
                pm.setuUniMed(rs.getString(9));
                pm.setTabEstado(rs.getString(10));
                pm.setCodEstado(rs.getString(11));
                pm.setVigente(rs.getString(12).charAt(0));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        // System.out.println("terminando la lista");
        return lista;
    }

    @Override
    public int add(Proy_PPartida p) {
        System.out.println("{call INSERTAR_PROY_PPARTIDA(?,?,?,?,?,?,?,?,?)}");
        try {
            con = conexion.conectar();
            myCall = con.prepareCall("{call INSERTAR_PROY_PPARTIDA(?,?,?,?,?,?,?,?,?)}");
            myCall.setInt(1, p.getCodPyto());
            myCall.setInt(2, p.getNroVersion());
            myCall.setInt(3, p.getCodCia());
            myCall.setString(4, p.getIngEgr());
            myCall.setInt(5, p.getCodPartida());
            myCall.setString(6, p.getCodPartidas());
            myCall.setString(7, p.getTabEstado());
            myCall.setString(8, p.getCodEstado());
            myCall.setString(9, String.valueOf(p.getVigente()));
            myCall.execute();
            myCall.close();
            con.close();
        } catch (SQLException ex) {
            handleException(ex);
            return 0;
        }
        return 1;
    }

    @Override
    public int actualizar(Proy_PPartida p) {
        String sql1 = "UPDATE PROY_PPARTIDA SET NROVERSION = ? WHERE CODCIA = ? AND CODPYTO = ? AND INGEGR = ? AND CODPARTIDA = ?";
        System.out.println(sql1);
        try (Connection con = conexion.conectar();
                PreparedStatement pst = con.prepareStatement(sql1)) {

            pst.setInt(1, p.getNroVersion());
            pst.setInt(2, p.getCodCia());
            pst.setInt(3, p.getCodPyto());
            pst.setString(4, p.getIngEgr());
            pst.setInt(5, p.getCodPartida());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Proy_PPartida actualizado con éxito.", "Aviso",
                        JOptionPane.INFORMATION_MESSAGE);
                return 1;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún registro para actualizar.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getSQLState().equals("23000") && e.getErrorCode() == 2292) {
                JOptionPane.showMessageDialog(null,
                        "No se puede actualizar el registro porque existen registros relacionados", "Error",
                        JOptionPane.ERROR_MESSAGE);
                System.out.println(e.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Restricción de integridad.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                System.out.println(e.toString());
            }
        } catch (SQLException e) {
            // Handle other SQLExceptions
            JOptionPane.showMessageDialog(null, "A database error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        } catch (Exception e) {
            // Handle other exceptions
            JOptionPane.showMessageDialog(null, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
        return 0;
    }

    @Override
    public void eliminar(int id) {
        return;
    }

    public void eliminarDatos(int cia, int cod, String tip, int pyto, int ver) {
        String sql1 = "DELETE from PROY_PPARTIDA where CODCIA=" + cia + " AND CODPARTIDA=" + cod + " AND INGEGR='" + tip
                + "' AND CODPYTO=" + pyto + " AND NROVERSION=" + ver;
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql1);
            rs = ps.executeQuery(sql1);
            rs.close();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Proy_PPartida eliminado con exito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + e.toString());
            System.out.println(e.toString());
        }
    }

    public Proy_PPartida listarId(int cia, int pyto, int ver, int cod, String tip) {
        Proy_PPartida pm = new Proy_PPartida();
        String sql = "SELECT CODPYTO,NROVERSION,CODPARTIDA,CODESTADO,VIGENTE FROM PROY_PPARTIDA WHERE CODCIA=" + cia
                + " AND"
                + " CODPYTO=" + pyto + " AND NROVERSION=" + ver + " AND CODPARTIDA=" + cod + " AND INGEGR='" + tip
                + "'";
        try {
            con = conexion.conectar();
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                pm.setCodPyto(rs.getInt(1));
                pm.setNroVersion(rs.getInt(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodEstado(rs.getString(4));
                pm.setVigente(rs.getString(5).charAt(0));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + e.toString());
            System.out.println(e.toString());
        }
        return pm;
    }

    public List<Proy_PPartida> listarPorCodCiaYProyecto(int id, int pyto, String tip) {
        List<Proy_PPartida> lista = new ArrayList<>();
        String sql = "SELECT "
                + "pp.CodPyto,pp.NroVersion,pp.CodPartida,pp.CodEstado,pp.Vigente, p.despartida "
                + "FROM PROY_PPARTIDA pp "
                + "LEFT JOIN ppartida p ON pp.codpartida=p.codpartida AND pp.CODCIA=p.CODCIA AND pp.ingegr=p.ingegr "
                + "WHERE pp.CODCIA=" + id + "AND pp.codpyto=" + pyto + " AND pp.ingegr='" + tip
                + "' order by pp.codpartida";
        System.out.println(sql);
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Proy_PPartida pm = new Proy_PPartida();
                pm.setCodPyto(rs.getInt(1));
                pm.setNroVersion(rs.getInt(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodEstado(rs.getString(4));
                pm.setVigente(rs.getString(5).charAt(0));
                pm.setDescripcion(rs.getString(6));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + e.toString());
            System.out.println(e.toString());
        }
        return lista;
    }

    public List<Proy_PPartida> listarPorCodCia(int id, String tip) {
        List<Proy_PPartida> lista = new ArrayList<>();
        String sql = "SELECT "
                + "pp.CodPyto,pp.NroVersion,pp.CodPartida,pp.CodEstado,pp.Vigente, p.despartida "
                + "FROM PROY_PPARTIDA pp "
                + "INNER JOIN ppartida p ON pp.codpartida=p.codpartida AND pp.CODCIA=p.CODCIA AND pp.ingegr=p.ingegr "
                + "WHERE pp.CODCIA=" + id + " AND pp.ingegr='" + tip + "' order by pp.codpartida";
        System.out.println(sql);
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Proy_PPartida pm = new Proy_PPartida();
                pm.setCodPyto(rs.getInt(1));
                pm.setNroVersion(rs.getInt(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodEstado(rs.getString(4));
                pm.setVigente(rs.getString(5).charAt(0));
                pm.setDescripcion(rs.getString(6));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + e.toString());
            System.out.println(e.toString());
        }
        return lista;
    }

    public List<Proy_PPartida> listarPorCodCiav2(int id, String tip, int pyto) {
        List<Proy_PPartida> lista = new ArrayList<>();
        String sql = "SELECT "
                + "CodPyto,NroVersion,CodPartida,CodEstado,Vigente "
                + "FROM PROY_PPARTIDA WHERE CODCIA=" + id + " AND INGEGR='" + tip + "' AND CODPYTO=" + pyto
                + " order by CODPYTO";
        System.out.println(sql);
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Proy_PPartida pm = new Proy_PPartida();
                pm.setCodPyto(rs.getInt(1));
                pm.setNroVersion(rs.getInt(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodEstado(rs.getString(4));
                pm.setVigente(rs.getString(5).charAt(0));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + e.toString());
            System.out.println(e.toString());
        }
        return lista;
    }

    public List<Proy_PPartida> listarPorCodCia(int id, String tip, int pyto) {
        List<Proy_PPartida> lista = new ArrayList<>();
        String sql = "SELECT "
                + "CodPyto,NroVersion,CodPartida,CodEstado,Vigente "
                + "FROM PROY_PPARTIDA WHERE CODCIA=" + id + " AND INGEGR='" + tip + "' AND CODPYTO=" + pyto
                + " order by CODPYTO";
        System.out.println(sql);
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Proy_PPartida pm = new Proy_PPartida();
                pm.setCodPyto(rs.getInt(1));
                pm.setNroVersion(rs.getInt(2));
                pm.setCodPartida(rs.getInt(3));
                pm.setCodEstado(rs.getString(4));
                pm.setVigente(rs.getString(5).charAt(0));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + e.toString());
            System.out.println(e.toString());
        }
        return lista;
    }

    public List<Proy_PPartida> listarPorCodPyto(int id) {
        List<Proy_PPartida> lista = new ArrayList<>();
        String sql = "SELECT "
                + "CodPartida "
                + "FROM PROY_PPARTIDA WHERE CODPYTO=" + id + " order by CODPARTIDA";
        System.out.println(sql);
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Proy_PPartida pm = new Proy_PPartida();
                pm.setCodPartida(rs.getInt(1));
                lista.add(pm);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + e.toString());
            System.out.println(e.toString());
        }
        return lista;
    }

    public Proy_PPartida listarId(String id) {
        Proy_PPartida pyPart = new Proy_PPartida();
        String sql = "SELECT DESPARTIDA,CODPARTIDA FROM PROY_PPARTIDA WHERE CODPARTIDA=" + id + " order by CODPARTIDA";
        try {
            con = conexion.conectar();
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                // pyPart.setDesPartida(rs.getString(1));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + e.toString());
            System.out.println(e.toString());
        }
        return pyPart;
    }

    public void handleException(SQLException ex) {
        if (ex.getSQLState().equals("23000") && ex.getErrorCode() == 1)
            JOptionPane.showMessageDialog(null, "No se puede agregar el registro porque ya existe.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        else if (ex.getSQLState().equals("23000") && ex.getErrorCode() == 2291)
            JOptionPane.showMessageDialog(null, "No se puede agregar el registro porque no existe la llave foránea.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        else if (ex.getSQLState().equals("23000") && ex.getErrorCode() == 2292)
            JOptionPane.showMessageDialog(null,
                    "No se puede agregar el registro porque existe un registro relacionado.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + ex.toString());
        System.out.println(ex.toString());
    }
}