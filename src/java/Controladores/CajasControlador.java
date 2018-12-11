/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Cajas;
import Utiles.Conexion;
import Utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ALUMNO
 */
public class CajasControlador {

    public static Cajas buscarId(int id)  {
        Cajas cajas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cajas where id_usuario=? and estado_caja= 'ABIERTO'";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        cajas = new Cajas();
                        cajas.setId_caja(rs.getInt("id_caja"));
                        cajas.setFecha_apertura(rs.getDate("fecha_apertura"));
                        cajas.setMonto_apertura(rs.getInt("monto_apertura"));
                        cajas.setEstado_caja(rs.getString("estado_caja"));

                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return cajas;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cajas "
                        + "where upper(estado_caja) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_caja "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("-->caja " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_caja") + "</td>"
                                + "<td>" + rs.getString("fecha_apertura") + "</td>"
                                + "<td>" + rs.getString("monto_apertura") + "</td>"
                                + "<td>" + rs.getString("estado_caja") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
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

    public static boolean agregar(Cajas caja)  {
        boolean valor = false;
        if (Conexion.conectar()) {

            String sql = "insert into cajas(monto_apertura, fecha_apertura,estado_caja)"
                    + "values('"
                    + caja.getFecha_apertura() + "','"
                    + caja.getMonto_apertura() + "','"
                    + caja.getEstado_caja() +  "')";

            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_caja = keyset.getInt(1);
                    caja.setId_caja(id_caja);

                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }

            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Cajas caja)  {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update cajas set "
                    + "estado_caja='" + caja.getEstado_caja() + "'"
                    + " where id_usuario=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setString(1, caja.getEstado_caja());
                ps.setInt(2, caja.getUsuario().getId_usuario());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean eliminar(Cajas caja)  {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from cajas where id_caja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, caja.getId_caja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean modificarestado(Cajas caja) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update cajas set estado_caja='CERRADO' "
                    + " where id_caja=" + caja.getId_caja();
            try {
                Conexion.getSt().executeUpdate(sql);
                Conexion.getConn().commit();
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
}
