/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Articulos;
import Modelos.FacturasDetalles;
import Modelos.FacturasVentas;
import Utiles.Conexion;
import Utiles.Utiles;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 *
 * @author Administrator
 */
public class FacturasDetallesControlador {

    public static FacturasDetalles buscarId(int id) {
        FacturasDetalles facturadetalle = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalles_facturaventas df, "
                        + " articulos a ,facturas_ventas f where f.id_factura =df.id_factura and "
                        + " a.id_articulo=df.id_articulo and "
                        + " df.id_ventadetalle=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturadetalle = new FacturasDetalles();
                        facturadetalle.setId_ventadetalle(rs.getInt("id_ventadetalle"));
                        facturadetalle.setCantidad_venta(rs.getInt("cantidad_venta"));
                        facturadetalle.setSubtotal_venta(rs.getInt("subtotal_venta"));

                        Articulos articulo = new Articulos();
                        articulo.setId_articulo(rs.getInt("id_articulo"));
                        articulo.setNombre_articulo(rs.getString("nombre_articulo"));
                        facturadetalle.setArticulo(articulo);

                        FacturasVentas facturaventa = new FacturasVentas();
                        facturaventa.setId_factura(rs.getInt("id_factura"));
                        facturadetalle.setFacturaventa(facturaventa);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturadetalle;
    }

    public static String buscarIdFactura(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalles_facturaventas df ,"
                        + " articulos a ,facturas_ventas f where f.id_factura=df.id_factura and "
                        + "a.id_articulo=df.id_articulo and"
                        + " f.id_factura=" + id + " "
                        + "order by df.id_ventadetalle";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###.00");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_venta");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_ventadetalle") + "</td>"
                                + "<td>" + rs.getString("nombre_articulo") + "</td>"
                                + "<td>" + rs.getString("cantidad_venta") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td>" + rs.getString("subtotal_venta") + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_ventadetalle") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>" + df.format(total) + "</td></tr>";
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
public static FacturasDetalles buscarTotal(FacturasDetalles facturadetalle) {
        if (Conexion.conectar()) {
            String sql = "SELECT sum(subtotal_venta) as total from detalles_facturaventas where id_factura="+facturadetalle.getFacturaventa().getId_factura()+"";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                
                        facturadetalle.setSubtotal_venta(rs.getInt("total"));

            
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return facturadetalle;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalles_facturaventas df "
                        + "articulos a,facturas_ventas f where f.id_factura=df.id_factura and "
                        + "a.id_articulo=df.id_articulo and"
                        + " upper(a.nombre_articulo) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by df.id_ventadetalle "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_ventadetalle") + "</td>"
                                + "<td>" + rs.getString("id_articulo") + "</td>"
                                + "<td>" + rs.getString("cantidad_venta") + "</td>"
                                + "<td>" + rs.getString("subtotal_venta") + "</td>"
                                + "<td>" + rs.getInt("id_factura") + "</td>"
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

    public static boolean agregar(FacturasDetalles facturadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from pedidos_clientes pc,pedidos_detalles pd,articulos a where pc.id_pedidocliente=pd.id_pedidocliente and a.id_articulo=pd.id_articulo and pd.id_pedidocliente=" + facturadetalle.getFacturaventa().getPedidocliente().getId_pedidocliente() + "";
            System.out.println("sql" + sql);
            try {
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        int precio_venta=rs.getInt("cantidad_articulo")*rs.getInt("precio_venta");
                        String sql2 = "insert into detalles_facturaventas (id_articulo,cantidad_venta,subtotal_venta,iva,id_factura)"
                                + "values('" + rs.getInt("id_articulo") + "',"
                                + "'" + rs.getInt("cantidad_articulo") + "',"
                                + "'" + precio_venta + "',"
                                + "'" + rs.getInt("iva")+ "',"
                                + "'" + facturadetalle.getFacturaventa().getId_factura() + "')";

                        System.out.println("agregardetalle" + sql2);
                        try {
                            Conexion.getSt().executeUpdate(sql2);
                            valor = true;
                        } catch (SQLException ex) {
                            System.out.println("Error:" + ex);
                        }

                    }
                    ps.close();

                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);

            }

        }
        return valor;
    }

    public static boolean modificar(FacturasDetalles facturadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detalles_facturaventas set "
                    + "id_ventadetalle=?,"
                    + "id_articulo=?,"
                    + "cantidad_venta=?, "
                    + "subtotal_venta=?, "
                    + "id_factura=? "
                    + "where id_ventadetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetalle.getId_ventadetalle());
                ps.setInt(2, facturadetalle.getArticulo().getId_articulo());
                ps.setInt(3, facturadetalle.getCantidad_venta());
                ps.setInt(4, facturadetalle.getSubtotal_venta());
                ps.setInt(5, facturadetalle.getFacturaventa().getId_factura());
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

    public static boolean eliminar(FacturasDetalles facturadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalles_facturaventas where id_ventadetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetalle.getId_ventadetalle());
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

    public static boolean eliminarDetalleFactura(FacturasVentas factura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalles_facturaventas where id_factura=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, factura.getId_factura());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + factura.getId_factura());
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
    
     public static FacturasDetalles calculartotal(int id) {
        FacturasDetalles facturadetalle = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select sum(df.subtotal_venta) as total from "
                        + "detalles_facturaventas df,  articulos a "
                        + ",facturas_ventas f "
                        + "where f.id_factura =df.id_factura and a.id_articulo=df.id_articulo "
                        + "and  f.id_factura=?";
                
            
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturadetalle = new FacturasDetalles();
                      
                        facturadetalle.setSubtotal_venta(rs.getInt("total"));
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturadetalle;
    }
}
