package Controladores;

import Modelos.Clientes;
import Modelos.Estados;
import Modelos.PedidosClientes;
import Utiles.Conexion;
import Utiles.Utiles;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PedidosClientesControlador {

    public static PedidosClientes buscarId(int id) {
        PedidosClientes pedidos = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidos_clientes p, estados e,clientes c "
                        + "where p.id_cliente=c.id_cliente and p.id_estado=e.id_estado and "
                        + "id_pedidocliente=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        pedidos = new PedidosClientes();
                        pedidos.setId_pedidocliente(rs.getInt("id_pedidocliente"));
                        pedidos.setFecha_pedido(rs.getDate("fecha_pedido"));
                        Clientes cliente = new Clientes();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        pedidos.setCliente(cliente);
                        Estados estado = new Estados();
                        estado.setId_estado(rs.getInt("id_estado"));
                        estado.setNombre_estado(rs.getString("nombre_estado"));
                        pedidos.setCliente(cliente);
                        pedidos.setEstado(estado);//no olvidar de devolver datos del objeto al principal//

                    } else {
                        pedidos = new PedidosClientes();
                        pedidos.setId_pedidocliente(0);
                        java.sql.Date fecha_presupuesto = new java.sql.Date(new java.util.Date().getTime());
                        pedidos.setFecha_pedido(fecha_presupuesto);
                        Clientes cliente = new Clientes();
                        cliente.setId_cliente(0);
                        cliente.setNombre_cliente("");
                        pedidos.setCliente(cliente);
                        Estados estado = new Estados();
                        estado.setId_estado(0);
                        estado.setNombre_estado("");
                        pedidos.setCliente(cliente);
                        pedidos.setEstado(estado);
                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return pedidos;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidos_clientes p, "
                        + "clientes c, estados e where "
                        + "c.id_cliente=p.id_cliente and p.id_estado=e.id_estado and "
                        + " upper(nombre_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_pedidocliente "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pedidocliente") + "</td>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("fecha_pedido") + "</td>"
                                + "<td>" + rs.getString("id_estado") + "</td>"
                                + "<td>" + rs.getString("nombre_estado") + "</td>"
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

    public static boolean agregar(PedidosClientes pedidos) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = pedidos.getCliente().getId_cliente();
            Date v2 = pedidos.getFecha_pedido();
            int v3 = pedidos.getEstado().getId_estado();

            String sql = "insert into pedidos_clientes(id_cliente, fecha_pedido,id_estado) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_pedido = keyset.getInt(1);
                    pedidos.setId_pedidocliente(id_pedido);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(PedidosClientes pedidos) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pedidos_clientes set id_cliente=? "
                    + "where id_pedidocliente=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, pedidos.getCliente().getId_cliente());
                ps.setInt(2, pedidos.getId_pedidocliente());
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

    public static boolean eliminar(PedidosClientes pedidos) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from pedidos_clientes where id_pedidocliente=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pedidos.getId_pedidocliente());
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
}
