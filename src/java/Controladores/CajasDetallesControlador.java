/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Cajas;
import Modelos.CajasDetalles;
import Modelos.FacturasVentas;
import Modelos.FormasCobros;
import Utiles.Conexion;
import Utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ALUMNO
 */
public class CajasDetallesControlador {

    public static CajasDetalles buscarId(int id) throws SQLException {
        CajasDetalles detallecaja = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cobros_detalles cd, "
                        + "cajas c,facturas_ventas fv,formas_cobros fc "
                        + "where cd.id_caja=c.id_caja and"
                        + " fv.id_factura=cd.id_factura and"
                        + " fc.id_formacobro=cd.id_formacobro"
                        + " and cd.id_cobrodetalle=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallecaja = new CajasDetalles();
                        detallecaja.setId_cobrodetalle(rs.getInt("id_cobrodetalle"));
                        detallecaja.setImporte(rs.getInt("importe"));

                        FacturasVentas facturaventa = new FacturasVentas();
                        facturaventa.setId_factura(rs.getInt("id_factura"));

                        detallecaja.setFacturaventa(facturaventa);

                        Cajas caja = new Cajas();
                        caja.setId_caja(rs.getInt("id_caja"));
                        detallecaja.setCaja(caja);

                        FormasCobros formacobro = new FormasCobros();
                        formacobro.setId_formacobro(rs.getInt("id_formacobro"));
                        formacobro.setNombre_formacobro(rs.getString("nombre_formacobro"));
                        detallecaja.setFormacobro(formacobro);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecaja;
    }

    public static String buscarIdCaja(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cobros_detalles  cd, "
                        + "factura_ventas fv ,formas_cobros fc  "
                        + " where fv.id_factura=cd.id_factura and "
                        + " fc.id_formacobro=cd.id_formacobro and "
                        + " cd.id_caja=" + id + " "
                        + "order by id_cobrodetalle";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    String tabla = "";

                    while (rs.next()) {

                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("id_formacobro") + "</td>"
                                + "<td>" + rs.getString("nombre_formacobro") + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_cobrodetalle") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cobros_detalles cd, "
                        + " cajas p,  factura_ventas fv where p.id_caja=cd.id_caja "
                        + " fv.id_factura=cd.id_factura "
                        + " upper(p.estado_caja) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_cobrodetalle "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cobrodetalle") + "</td>"
                                + "<td>" + rs.getString("fecha_venta") + "</td>"
                                + "<td>" + rs.getString("estado_caja") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(CajasDetalles detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into cobros_detalles "
                    + "(id_caja,importe,id_factura,id_formacobro) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getCaja().getId_caja());

                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(CajasDetalles detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update cobros_detalles set "
                    + "id_caja=?,"
                    + "id_factura=?,"
                    + "where id_cobrodetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
             
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(CajasDetalles detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from cobros_detalles where id_cobrodetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getId_cobrodetalle());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

   
    public static boolean eliminarVentaCaja(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from cobros_detalles where id_caja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, caja.getId_caja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + caja.getId_caja());
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

}
