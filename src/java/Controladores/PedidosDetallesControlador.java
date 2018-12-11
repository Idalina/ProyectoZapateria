/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Articulos;
import Modelos.PedidosClientes;
import Modelos.Pedidosdetalles;
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
public class PedidosDetallesControlador {

    public static Pedidosdetalles buscarId(int id) {
        Pedidosdetalles pedidodetalle = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidos_detalles pd, "
                        + " pedidos_clientes p, articulos a  where p.id_pedidocliente=pd.id_pedidocliente and "
                        + " a.id_articulo=pd.id_articulo and "
                        + " pd.id_pedidodetalle=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        pedidodetalle = new Pedidosdetalles();
                        pedidodetalle.setId_pedidodetalle(rs.getInt("id_pedidodetalle"));
                        pedidodetalle.setCantidad_articulo(rs.getInt("cantidad_articulo"));
                       

                        Articulos articulo = new Articulos();
                        articulo.setId_articulo(rs.getInt("id_articulo"));
                        articulo.setNombre_articulo(rs.getString("nombre_articulo"));
                        articulo.setPrecio_venta(rs.getInt("precio_venta"));
                        pedidodetalle.setArticulo(articulo);

                        PedidosClientes pedido = new PedidosClientes();
                        pedido.setId_pedidocliente(rs.getInt("id_pedidocliente"));
                        pedidodetalle.setPedidocliente(pedido);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return pedidodetalle;
    }

    public static String buscarIdPedido(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidos_detalles pd ,"
                        + " pedidos_clientes p, articulos a where p.id_pedidocliente=pd.id_pedidocliente and "
                        + "a.id_articulo=pd.id_articulo and"
                        + " pd.id_pedidocliente=" + id + " "
                        + "order by id_pedidodetalle";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    BigDecimal total2 = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_articulo");
                        BigDecimal precio = rs.getBigDecimal("precio_venta");
                        total = precio.multiply(cantidad);
                        total2 = total2.add(total);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pedidodetalle") + "</td>"
                                + "<td>" + rs.getString("id_articulo") + "</td>"
                                + "<td>" + rs.getString("nombre_articulo") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>" + df.format(precio) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_pedidodetalle") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"
                                
                                + "<td class='centrado'>"+ df.format(total2) +"</td>";
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

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidos_detalles pd "
                        + "pedidos_clientes p ,articulos a where p.id_pedidocliente=pd.id_pedidocliente and "
                        + "a.id_articulo=pd.id_articulo and"
                        + " upper(a.nombre_articulo) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_pedidodetalle "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallepedido") + "</td>"
                                + "<td>" + rs.getString("id_pedido") + "</td>"
                                + "<td>" + rs.getString("id_articulo") + "</td>"
                                + "<td>" + rs.getString("nombre_articulo") + "</td>"
                                + "<td>" + rs.getInt("cantidad_articulopedido") + "</td>"
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

    public static boolean agregar(Pedidosdetalles pedidodetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into pedidos_detalles "
                    + "(id_pedidocliente,id_articulo,cantidad_articulo) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pedidodetalle.getPedidocliente().getId_pedidocliente());
                ps.setInt(2, pedidodetalle.getArticulo().getId_articulo());
                ps.setInt(3, pedidodetalle.getCantidad_articulo());
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

    public static boolean modificar(Pedidosdetalles pedidodetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pedidos_detalles set "
                    + "id_pedidocliente=?,"
                    + "id_articulo=?,"
                    + "cantidad_articulo=? "
                    + "where id_pedidodetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pedidodetalle.getPedidocliente().getId_pedidocliente());
                ps.setInt(2, pedidodetalle.getArticulo().getId_articulo());
                ps.setInt(3, pedidodetalle.getCantidad_articulo());
                ps.setInt(4, pedidodetalle.getId_pedidodetalle());
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

    public static boolean eliminar(Pedidosdetalles pedidodetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from pedidos_detalles where id_pedidodetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pedidodetalle.getId_pedidodetalle());
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

    public static boolean eliminarArticuloPedido(Pedidosdetalles pedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from pedidos_detalles where id_pedidocliente=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pedido.getId_pedidodetalle());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + pedido.getId_pedidodetalle());
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
