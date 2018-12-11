/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Articulos;
import Modelos.Calces;
import Modelos.CategoriaCalces;
import Modelos.Colores;
import Modelos.Marcas;
import Modelos.TiposCalzados;
import Utiles.Conexion;
import Utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */
public class ArticulosControlador {

    public static boolean agregar(Articulos articulo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into articulos(nombre_articulo,codigo_articulo,precio_compra,precio_venta,iva,id_color,id_marca,id_tipocalzado,id_calce)"
                    + "values('" + articulo.getNombre_articulo() + "',"
                    + "'" + articulo.getCodigo_articulo() + "',"
                    + "'" + articulo.getPrecio_compra() + "',"
                    + "'" + articulo.getPrecio_venta() + "',"
                    + "'" + articulo.getIva() + "',"
                    + "'" + articulo.getColor().getId_color() + "',"
                    + "'" + articulo.getMarca().getId_marca() + "',"
                    + "'" + articulo.getTipocalzado().getId_tipocalzado() + "',"
                    + "'" + articulo.getCalce().getId_calce() + "')";

            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset=Conexion.getSt().getGeneratedKeys();
                if(keyset.next()){
                    int id_articulo=keyset.getInt(1);
                    articulo.setId_articulo(id_articulo);
                                      
                }
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Articulos buscarId(Articulos articulo) {
        if (Conexion.conectar()) {
            String sql = "Select * from articulos a,colores c, "
                    + " marcas m,tipos_calzados t,calces ca,"
                    + "categorias_calces cc where a.id_color=c.id_color and a.id_marca=m.id_marca and a.id_tipocalzado=t.id_tipocalzado and a.id_calce=ca.id_calce  and cc.id_categoriacalce= ca.id_categoriacalce and "
                    + "id_articulo='" + articulo.getId_articulo() + "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Colores color = new Colores();
                    color.setId_color(rs.getInt("id_color"));
                    color.setNombre_color(rs.getString("nombre_color"));
                    Marcas marca = new Marcas();
                    marca.setId_marca(rs.getInt("id_marca"));
                    marca.setNombre_marca(rs.getString("nombre_marca"));
                    TiposCalzados tipocalzado = new TiposCalzados();
                    tipocalzado.setId_tipocalzado(rs.getInt("id_tipocalzado"));
                    tipocalzado.setNombre_tipocalzado(rs.getString("nombre_tipocalzado"));
                    Calces calce = new Calces();
                    CategoriaCalces categoriacalce = new CategoriaCalces();
                    categoriacalce.setNombre_categoriacalce(rs.getString("nombre_categoriacalce"));
                    calce.setId_calce(rs.getInt("id_calce"));
                    calce.setNumero_calce(rs.getString("numero_calce"));
                    calce.setCategoriacalce(categoriacalce);
                    articulo.setNombre_articulo(rs.getString("nombre_articulo"));
                    articulo.setCodigo_articulo(rs.getString("codigo_articulo"));
                    articulo.setPrecio_compra(rs.getInt("precio_compra"));
                    articulo.setPrecio_venta(rs.getInt("precio_venta"));
                    articulo.setIva(rs.getInt("iva"));
                    articulo.setColor(color);
                    articulo.setMarca(marca);
                    articulo.setTipocalzado(tipocalzado);
                    articulo.setCalce(calce);

                } else {
                    articulo.setId_articulo(0);
                    articulo.setNombre_articulo("");
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return articulo;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from articulos where upper (nombre_articulo) like '%"
                        + nombre.toUpperCase() + "%'"
                        + " order by id_articulo offset " + offset + "limit "
                        + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_articulo") + "</td>"
                                + "<td>" + rs.getString("nombre_articulo") + "</td>"
                                + "<td>" + rs.getString("codigo_articulo") + "</td>"
                                + "<td>" + rs.getString("precio_compra") + "</td>"
                                + "<td>" + rs.getString("precio_venta") + "</td>"
                                + "<td>" + rs.getString("iva") + "</td>"
                                + "<td>" + rs.getString("id_color") + "</td>"
                                + "<td>" + rs.getString("id_marca") + "</td>"
                                + "<td>" + rs.getString("id_tipocalzado") + "</td>"
                                + "<td>" + rs.getString("id_calce") + "</td>"
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

    public static boolean modificarArticulo(Articulos articulo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update articulos set nombre_articulo= '" + articulo.getNombre_articulo() + "'"
                    + ", codigo_articulo='" + articulo.getCodigo_articulo() + "'"
                    + ", precio_compra='" + articulo.getPrecio_compra() + "'"
                    + ", precio_venta='" + articulo.getPrecio_venta() + "'"
                    + ", iva='" + articulo.getIva() + "'"
                    + ", id_color='" + articulo.getColor().getId_color() + "'"
                    + ", id_marca='" + articulo.getMarca().getId_marca() + "'"
                    + ", id_tipocalzado='" + articulo.getTipocalzado().getId_tipocalzado() + "'"
                    + ", id_calce='" + articulo.getCalce().getId_calce() + "'"
                    + "where id_articulo=" + articulo.getId_articulo();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminarArticulo(Articulos articulo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from articulos where id_articulo=" + articulo.getId_articulo();
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
