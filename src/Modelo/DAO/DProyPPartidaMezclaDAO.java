package Modelo.DAO;

import Modelo.Conexion.ConectarOracle;
import Modelo.DProyPPartidaMezcla;
import Modelo.Proyecto;
import Modelo.Interface.CRUD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DProyPPartidaMezclaDAO implements CRUD<DProyPPartidaMezcla> {

    // ConectarOracle conexion=new ConectarOracle();
    ConectarOracle conexion = ConectarOracle.getInstance();
    Connection con;
    Statement ps;
    PreparedStatement pst;
    ResultSet rs;
    CallableStatement myCall;

    @Override
    public List<DProyPPartidaMezcla> listar() {
        List<DProyPPartidaMezcla> lista = new ArrayList<>();
        String sql = "SELECT * FROM DPROY_PPARTIDA_MEZCLA ORDER BY CODCIA";

        try (Connection con = conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DProyPPartidaMezcla dppm = new DProyPPartidaMezcla();
                dppm.setCodCia(rs.getInt(1));
                dppm.setCodPyto(rs.getInt(2));
                dppm.setIngEgr(rs.getString(3));
                dppm.setNroVersion(rs.getInt(4));
                dppm.setCodPartida(rs.getInt(5));
                dppm.setCorr(rs.getInt(6));
                dppm.setSec(rs.getInt(7));
                dppm.settDesembolso(rs.getString(8));
                dppm.seteDesembolso(rs.getString(9));
                dppm.setNroPago(rs.getInt(10));
                dppm.settCompPago(rs.getString(11));
                dppm.seteCompPago(rs.getString(12));
                dppm.setFecDesembolso(rs.getDate(13));
                dppm.setImpDesemNeto(rs.getFloat(14));
                dppm.setImpDesemIgv(rs.getFloat(15));
                dppm.setImpDesemTotal(rs.getFloat(16));
                dppm.setSemilla(rs.getInt(17));
                lista.add(dppm);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        System.out.println("terminando la lista");
        return lista;
    }

    @Override
    public int add(DProyPPartidaMezcla dppm) {
        Connection con = null;
        CallableStatement myCall = null;

        try {
            con = conexion.conectar();
            String sqlCall = "{call INSERTAR_DPROY_PPARTIDA_MEZCLA(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            myCall = con.prepareCall(sqlCall);

            // Set parameters
            myCall.setInt(1, dppm.getCodCia());
            myCall.setInt(2, dppm.getCodPyto());
            myCall.setString(3, dppm.getIngEgr());
            myCall.setInt(4, dppm.getNroVersion());
            myCall.setInt(5, dppm.getCodPartida());
            myCall.setInt(6, dppm.getCorr());
            myCall.setString(7, dppm.geteDesembolso());
            myCall.setString(8, dppm.geteCompPago());
            myCall.setDate(9, dppm.getFecDesembolso());
            myCall.setFloat(10, dppm.getImpDesemNeto());
            myCall.setFloat(11, dppm.getImpDesemIgv());
            myCall.setFloat(12, dppm.getImpDesemTotal());
            myCall.setInt(13, dppm.getSemilla());
            myCall.setInt(14, dppm.getRep());

            // Print the SQL call with parameters for debugging
            String debugMessage = String.format(
                    "Executing SQL Call: %s with parameters: [%d, %d, '%s', %d, %d, %d, '%s', '%s', %s, %.2f, %.2f, %.2f, %d, %d]",
                    sqlCall,
                    dppm.getCodCia(),
                    dppm.getCodPyto(),
                    dppm.getIngEgr(),
                    dppm.getNroVersion(),
                    dppm.getCodPartida(),
                    dppm.getCorr(),
                    dppm.geteDesembolso(),
                    dppm.geteCompPago(),
                    dppm.getFecDesembolso(),
                    dppm.getImpDesemNeto(),
                    dppm.getImpDesemIgv(),
                    dppm.getImpDesemTotal(),
                    dppm.getSemilla(),
                    dppm.getRep());
            System.out.println(debugMessage);

            // Execute the SQL call
            myCall.execute();
        } catch (SQLException ex) {
            // Check if the error is "no data found"
            if (ex.getErrorCode() == 1403) {
                // The user inserted a year that the project does not have, so we need to show
                // them the available years
                Proyecto proyecto = new ProyectoDAO().listarId(dppm.getCodPyto());
                String inputDate = dppm.getFecDesembolso().toString().substring(0, 4);
                String availableYears = proyecto.getAnnoIni() + " - " + proyecto.getAnnoFin();
                JOptionPane.showMessageDialog(null, "-Verifique el año. Rango disponible: " + availableYears
                        + "\n-Verifique que la partida raíz este incluida en la mezcla del proyecto.");
            } else {
                JOptionPane.showMessageDialog(null, "Exception: " + ex.toString());
            }
            System.out.println(ex.toString());
            return -1;
        } finally {
            try {
                if (myCall != null)
                    myCall.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int actualizar(DProyPPartidaMezcla dppm) {
        String sql1 = "update DPROY_PPARTIDA_MEZCLA set CODCIA=?,CODPYTO=?,INGEGR=?,NROVERSION=?,CODPARTIDA=?,CORR=?,TDESEMBOLSO=?,EDESEMBOLSO=?,NROPAGO=?,TCOMPPAGO=?,ECOMPPAGO=?,FECDESEMBOLSO=?,IMPDESEMBNETO=?,IMPDESEMBIGV=?,IMPDESEMBTOT=?,SEMILLA=? where SEC=?";
        System.out.println(sql1);
        try {
            con = conexion.conectar();
            pst = con.prepareStatement(sql1);
            pst.setInt(1, dppm.getCodCia());
            pst.setInt(2, dppm.getCodPyto());
            pst.setString(3, dppm.getIngEgr());
            pst.setInt(4, dppm.getNroVersion());
            pst.setInt(5, dppm.getCodPartida());
            pst.setInt(6, dppm.getCorr());
            pst.setString(7, dppm.gettDesembolso());
            pst.setString(8, dppm.geteDesembolso());
            pst.setInt(9, dppm.getNroPago());
            pst.setString(10, dppm.gettCompPago());
            pst.setString(11, dppm.geteCompPago());
            pst.setDate(12, dppm.getFecDesembolso());
            pst.setFloat(13, dppm.getImpDesemNeto());
            pst.setFloat(14, dppm.getImpDesemIgv());
            pst.setFloat(15, dppm.getImpDesemTotal());
            pst.setInt(16, dppm.getSemilla());
            pst.setInt(17, dppm.getSec());
            pst.execute();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + ex.toString());
            System.out.println(ex.toString());
            return -1;
        }
        return 0;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM DPROY_PPARTIDA_MEZCLA WHERE NROPAGO = ? and 1=1";

        try (Connection con = conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {

            if (con != null && !con.isClosed()) {
                System.out.println("Connection established successfully.");

                // Set parameter
                ps.setInt(1, id);

                // Set query timeout
                // ps.setQueryTimeout(30); // timeout in seconds

                // Execute the update
                int affectedRows = ps.executeUpdate();
                System.out.println("Affected Rows: " + affectedRows);

                // Show success message
                JOptionPane.showMessageDialog(null, "DPROY_PPARTIDA_MEZCLA eliminado con exito.");
            } else {
                System.out.println("Failed to establish connection.");
                JOptionPane.showMessageDialog(null, "Failed to establish connection.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLException: " + e.getMessage());
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.toString());
            System.out.println("Exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public List listarPorCodCia(int id, String tip) {
        List<DProyPPartidaMezcla> lista = new ArrayList<>();
        String sql = "SELECT * FROM DPROY_PPARTIDA_MEZCLA WHERE codcia=" + id + " AND ingegr='" + tip + "'";
        System.out.println(sql);
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                DProyPPartidaMezcla dppm = new DProyPPartidaMezcla();
                dppm.setCodCia(rs.getInt(1));
                dppm.setCodPyto(rs.getInt(2));
                dppm.setIngEgr(rs.getString(3));
                dppm.setNroVersion(rs.getInt(4));
                dppm.setCodPartida(rs.getInt(5));
                dppm.setCorr(rs.getInt(6));
                dppm.setSec(rs.getInt(7));
                dppm.settDesembolso(rs.getString(8));
                dppm.seteDesembolso(rs.getString(9));
                dppm.setNroPago(rs.getInt(10));
                dppm.settCompPago(rs.getString(11));
                dppm.seteCompPago(rs.getString(12));
                dppm.setFecDesembolso(rs.getDate(13));
                dppm.setImpDesemNeto(rs.getFloat(14));
                dppm.setImpDesemIgv(rs.getFloat(15));
                dppm.setImpDesemTotal(rs.getFloat(16));
                dppm.setSemilla(rs.getInt(17));
                lista.add(dppm);
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

    public List<DProyPPartidaMezcla> listarPorCodCia(int id, String tip, int pyto) {
        List<DProyPPartidaMezcla> lista = new ArrayList<>();
        String sql = "SELECT * FROM DPROY_PPARTIDA_MEZCLA WHERE codcia=" + id + " AND ingegr='" + tip + "' AND CODPYTO="
                + pyto;
        System.out.println(sql);
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                DProyPPartidaMezcla dppm = new DProyPPartidaMezcla();
                dppm.setCodCia(rs.getInt(1));
                dppm.setCodPyto(rs.getInt(2));
                dppm.setIngEgr(rs.getString(3));
                dppm.setNroVersion(rs.getInt(4));
                dppm.setCodPartida(rs.getInt(5));
                dppm.setCorr(rs.getInt(6));
                dppm.setSec(rs.getInt(7));
                dppm.settDesembolso(rs.getString(8));
                dppm.seteDesembolso(rs.getString(9));
                dppm.setNroPago(rs.getInt(10));
                dppm.settCompPago(rs.getString(11));
                dppm.seteCompPago(rs.getString(12));
                dppm.setFecDesembolso(rs.getDate(13));
                dppm.setImpDesemNeto(rs.getFloat(14));
                dppm.setImpDesemIgv(rs.getFloat(15));
                dppm.setImpDesemTotal(rs.getFloat(16));
                dppm.setSemilla(rs.getInt(17));
                lista.add(dppm);
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

    public DProyPPartidaMezcla listarId(int semilla, String tipo) {
        DProyPPartidaMezcla dppm = new DProyPPartidaMezcla();
        String sql = "SELECT dppm.*, p.despartida FROM DPROY_PPARTIDA_MEZCLA dppm "
                + " LEFT JOIN PROY_PPARTIDA_MEZCLA ppm ON dppm.CODCIA=ppm.CODCIA AND dppm.CODPYTO=ppm.CODPYTO AND dppm.CODPARTIDA=ppm.CODPARTIDA AND dppm.ingegr=ppm.ingegr AND dppm.nroversion=ppm.nroversion AND dppm.corr=ppm.corr"
                + " LEFT JOIN PROY_PPARTIDA pp ON pp.codcia=ppm.codcia AND pp.codpyto=ppm.codpyto AND pp.ingegr=ppm.ingegr AND pp.nroversion=ppm.nroversion AND pp.codpartida=ppm.codpartida"
                + " LEFT JOIN ppartida p on p.codcia=pp.codcia AND p.ingegr=pp.ingegr AND p.codpartida=pp.codpartida"
                + " WHERE dppm.SEMILLA=" + semilla + " AND dppm.INGEGR='" + tipo + "'";
        try {
            con = conexion.conectar();
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                dppm.setCodCia(rs.getInt(1));
                dppm.setCodPyto(rs.getInt(2));
                dppm.setIngEgr(rs.getString(3));
                dppm.setNroVersion(rs.getInt(4));
                dppm.setCodPartida(rs.getInt(5));
                dppm.setCorr(rs.getInt(6));
                dppm.setSec(rs.getInt(7));
                dppm.settDesembolso(rs.getString(8));
                dppm.seteDesembolso(rs.getString(9));
                dppm.setNroPago(rs.getInt(10));
                dppm.settCompPago(rs.getString(11));
                dppm.seteCompPago(rs.getString(12));
                dppm.setFecDesembolso(rs.getDate(13));
                dppm.setImpDesemNeto(rs.getFloat(14));
                dppm.setImpDesemIgv(rs.getFloat(15));
                dppm.setImpDesemTotal(rs.getFloat(16));
                dppm.setSemilla(rs.getInt(17));
                dppm.setDescription(rs.getString(18));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + e.toString());
            System.out.println(e.toString());
        }
        return dppm;
    }

    public int buscarSemilla(int cia, int cod, String tipo) {
        int semilla = 0;
        String sql = "SELECT SEMILLA FROM DPROY_PPARTIDA_MEZCLA WHERE CODCIA=" + cia + " AND INGEGR='" + tipo
                + "' AND CODPARTIDA=" + cod;
        try {
            con = conexion.conectar();
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                semilla = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Excepcion.\n" + e.toString());
            System.out.println(e.toString());
        }
        return semilla;
    }
}
