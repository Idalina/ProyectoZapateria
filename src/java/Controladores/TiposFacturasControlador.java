/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;



import Modelos.TiposFacturas;
import Utiles.Conexion;
import Utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class TiposFacturasControlador {

    public static boolean agregar(TiposFacturas tipofactura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into tiposfacturas(nombre_tipofactura)"
                    + "values('" + tipofactura.getNombre_tipofactura() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static TiposFacturas buscarId(TiposFacturas facturatipo) {
        if (Conexion.conectar()) {
            String sql = "Select *from tiposfacturas where id_tipofactura='" + facturatipo.getId_tipofactura() + "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    facturatipo.setNombre_tipofactura(rs.getString("nombre_tipofactura"));

                } else {
                    facturatipo.setId_tipofactura(0);
                    facturatipo.setNombre_tipofactura("");
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return facturatipo;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from tiposfacturas where upper (nombre_tipofactura) like '%"
                        + nombre.toUpperCase() + "%'"
                        + " order by id_tipofactura offset " + offset + "limit "
                        + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_tipofactura") + "</td>"
                                + "<td>" + rs.getString("nombre_tipofactura") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error:" + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error:" + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(TiposFacturas facturatipo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update tiposfacturas set nombre_tipofactura= '" + facturatipo.getNombre_tipofactura() + "'"
                    + "where id_tipofactura=" + facturatipo.getId_tipofactura();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(TiposFacturas facturatipo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from tiposfacturas where id_tipofactura=" + facturatipo.getId_tipofactura();
            System.out.println("--->" + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
}
