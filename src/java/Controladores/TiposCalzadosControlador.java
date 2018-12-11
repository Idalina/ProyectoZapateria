package Controladores;

import Modelos.CategoriaTipoCalzados;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Utiles.Conexion;
import java.sql.ResultSet;
import Modelos.TiposCalzados;
import Utiles.Utiles;

public class TiposCalzadosControlador {

    public static boolean agregar(TiposCalzados tipo_calzado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into tipos_calzados(nombre_tipocalzado,id_categoriatipocalzado)"
                    + "values('" + tipo_calzado.getNombre_tipocalzado() + "'"
                    + ", '" + tipo_calzado.getCategoria_tipocalzado().getId_categoriatipocalzado()
                    + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static TiposCalzados buscarId(TiposCalzados tipo_calzado) {
        if (Conexion.conectar()) {
            String sql = "Select * from tipos_calzados t,categorias_tipocalzados c where t.id_categoriatipocalzado = c.id_categoriatipocalzado"
                    + " and id_tipocalzado='" + tipo_calzado.getId_tipocalzado() + "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    CategoriaTipoCalzados categoria_tipocalzado = new CategoriaTipoCalzados();
                    categoria_tipocalzado.setId_categoriatipocalzado(rs.getInt("id_categoriatipocalzado"));
                    categoria_tipocalzado.setNombre_categoriatipocalzado(rs.getString("nombre_categoriatipocalzado"));
                    tipo_calzado.setNombre_tipocalzado(rs.getString("nombre_tipocalzado"));
                    tipo_calzado.setCategoria_tipocalzado(categoria_tipocalzado);

                } else {
                    tipo_calzado.setId_tipocalzado(0);
                    tipo_calzado.setNombre_tipocalzado("");
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return tipo_calzado;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from tipos_calzados tc,categorias_tipocalzados ct "
                        + " where upper (nombre_tipocalzado) like '%"
                        + nombre.toUpperCase() + "%'"
                        + " order by id_tipocalzado offset " + offset + "limit "
                        + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_tipocalzado") + "</td>"
                                + "<td>" + rs.getString("nombre_tipocalzado") + "</td>"
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

    public static boolean modificar(TiposCalzados tipo_calzado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update tipos_calzados set nombre_tipocalzado= '" + tipo_calzado.getNombre_tipocalzado() + "'"
                    + " , id_categoriatipocalzado='" + tipo_calzado.getCategoria_tipocalzado().getId_categoriatipocalzado() + "'"
                    + " where id_tipocalzado=" + tipo_calzado.getId_tipocalzado();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(TiposCalzados tipo_calzado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from tipos_calzados where id_tipocalzado=" + tipo_calzado.getId_tipocalzado();
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
