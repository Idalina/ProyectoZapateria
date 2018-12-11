package Controladores;

import Modelos.Clientes;

import Modelos.FacturasVentas;
import Modelos.FormasCobros;
import Modelos.PedidosClientes;
import Modelos.TiposFacturas;
import Utiles.Conexion;
import Utiles.Utiles;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FacturasVentasControlador {

    public static FacturasVentas buscarId(int id) {
        FacturasVentas factura = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturas_ventas f, tiposfacturas t,clientes c,"
                        + " pedidos_clientes p "
                        + "where f.id_cliente=c.id_cliente and f.id_tipofactura=t.id_tipofactura and "
                        + "f.id_pedidocliente=p.id_pedidocliente and "
                        + "id_factura=?";
                System.out.println("sql"+id);
                
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        factura = new FacturasVentas();
                        factura.setId_factura(rs.getInt("id_factura"));
                        factura.setFecha_venta(rs.getDate("fecha_venta"));
                        Clientes cliente = new Clientes();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                        factura.setCliente(cliente);

                        TiposFacturas tipofactura = new TiposFacturas();
                        tipofactura.setId_tipofactura(rs.getInt("id_tipofactura"));
                        tipofactura.setNombre_tipofactura(rs.getString("nombre_tipofactura"));
                        factura.setTipofactura(tipofactura);

                        PedidosClientes pedidocliente = new PedidosClientes();
                        pedidocliente.setId_pedidocliente(rs.getInt("id_pedidocliente"));
                        factura.setPedidocliente(pedidocliente);

                        //no olvidar de devolver datos del objeto al principal//
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return factura;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturas_ventas f, "
                        + "clientes c, tiposfacturas t, pedidos_clientes p where "
                        + "c.id_cliente=f.id_cliente and t.id_tipofactura=f.id_tipofactura and "
                        + "f.id_pedidocliente=p.id_pedidocliente and "
                        + " upper(c.nombre_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by f.id_factura "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_factura") + "</td>"
                                + "<td>" + rs.getString("ruc_cliente") + "</td>"
                                + "<td>" + rs.getString("id_tipofactura") + "</td>"
                                + "<td>" + rs.getString("nombre_tipofactura") + "</td>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td>" + rs.getString("fecha_venta") + "</td>"
                                + "<td>" + rs.getString("id_pedidocliente") + "</td>"
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

    public static boolean agregar(FacturasVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
        
          //  int v3 = facturaventa.getTipofactura().getId_tipofactura();
            int v4= facturaventa.getCliente().getId_cliente();
            Date v5 = facturaventa.getFecha_venta();
            int v6 = facturaventa.getPedidocliente().getId_pedidocliente();
            int v7 = 1;

            String sql = "insert into facturas_ventas(id_cliente,"
                    + " fecha_venta,id_pedidocliente,id_tipofactura) "
                    + "values('" + v4 +"','"+ v5 +"','"+ v6 + "','"+ v7 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_factura = keyset.getInt(1);
                    facturaventa.setId_factura(id_factura);
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

    public static boolean modificar(FacturasVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturas_ventas set "
                    + "id_factura=?,"
                    +" id_tipofactura=?, id_cliente=?, "
                    + "fecha_venta=?, id_pedidocliente=? "
                    + "where id_factura=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, facturaventa.getId_factura());
                
                ps.setInt(2,facturaventa.getTipofactura().getId_tipofactura());
                ps.setDate(3,facturaventa.getFecha_venta());
                ps.setInt(4,facturaventa.getCliente().getId_cliente());
                ps.setInt(5, facturaventa.getPedidocliente().getId_pedidocliente());
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

    public static boolean eliminar(FacturasVentas facturaventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from facturas_ventas where id_factura=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturaventa.getId_factura());
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
}