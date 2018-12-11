package Controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Utiles.Conexion;
import java.sql.ResultSet;
import Modelos.CategoriaTipoCalzados;
import Utiles.Utiles;

public class CategoriaTipoCalzadosControlador {

    public static boolean agregar(CategoriaTipoCalzados categoria_tipocalzado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into categorias_tipocalzados(nombre_categoriatipocalzado)"
                    + "values('" + categoria_tipocalzado.getNombre_categoriatipocalzado()
                    + "')";
            
            System.out.println("sql"+sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static CategoriaTipoCalzados buscarId(CategoriaTipoCalzados categoria_tipocalzado) {
        if (Conexion.conectar()) {
            String sql = "Select * from categorias_tipocalzados where id_categoriatipocalzado='" + categoria_tipocalzado.getId_categoriatipocalzado() + "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    categoria_tipocalzado.setNombre_categoriatipocalzado(rs.getString("nombre_categoriatipocalzado"));

                } else {
                    categoria_tipocalzado.setId_categoriatipocalzado(0);
                    categoria_tipocalzado.setNombre_categoriatipocalzado("");
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return categoria_tipocalzado;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from categorias_tipocalzados where upper (nombre_categoriatipocalzado) like '%"
                        + nombre.toUpperCase() + "%'"
                        + " order by id_categoriatipocalzado offset " + offset + "limit "
                        + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_categoriatipocalzado") + "</td>"
                                + "<td>" + rs.getString("nombre_categoriatipocalzado") + "</td>"
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

    public static boolean modificar(CategoriaTipoCalzados categoria_tipocalzado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update categorias_tipocalzados set nombre_categoriatipocalzado='" + categoria_tipocalzado.getNombre_categoriatipocalzado()+"'"
                    + " where id_categoriatipocalzado=" + categoria_tipocalzado.getId_categoriatipocalzado();
            System.out.println("sql"+sql);
            
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(CategoriaTipoCalzados categoria_tipocalzado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from categorias_tipocalzados where id_categoriatipocalzado=" + categoria_tipocalzado.getId_categoriatipocalzado();
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
