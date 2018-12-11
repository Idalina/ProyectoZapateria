package Controladores;

import Modelos.Ciudades;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Utiles.Conexion;
import java.sql.ResultSet;
import Modelos.Clientes;
import Modelos.EstadosCiviles;
import Modelos.Nacionalidades;

import Utiles.Utiles;

public class ClientesControlador {

    public static boolean agregar(Clientes cliente) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into clientes(nombre_cliente,apellido_cliente,"
                    + "ci_cliente,ruc_cliente,direccion_cliente,telefono_cliente,id_nacionalidad,id_ciudad,id_estadocivil)"
                    + " values('"
                    + cliente.getNombre_cliente() + "','"
                    + cliente.getApellido_cliente() + "','"
                    + cliente.getCi_cliente() + "','"
                    + cliente.getRuc_cliente() + "','"
                    + cliente.getDireccion_cliente() + "','"
                    + cliente.getTelefono_cliente() + "','"
                    + cliente.getNacionalidad().getId_nacionalidad() + "','"
                    + cliente.getCiudad().getId_ciudad() + "','"
                    + cliente.getEstadocivil().getId_estadocivil() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Clientes buscarId(Clientes cliente) {
        if (Conexion.conectar()) {
            String sql = "select * from clientes c, nacionalidades n, ciudades p, estados_civiles e "
                    + "where c.id_nacionalidad=n.id_nacionalidad and c.id_ciudad=p.id_ciudad and c.id_estadocivil=e.id_estadocivil"
                    + " and id_cliente='" + cliente.getId_cliente() + "'";//le falto espacio antes de and
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    Nacionalidades nacionalidad = new Nacionalidades();
                    nacionalidad.setId_nacionalidad(rs.getInt("id_nacionalidad"));
                    nacionalidad.setNombre_nacionalidad(rs.getString("nombre_nacionalidad"));
                    EstadosCiviles estadocivil = new EstadosCiviles();
                    estadocivil.setId_estadocivil(rs.getInt("id_estadocivil"));
                    estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                    cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                    cliente.setApellido_cliente(rs.getString("apellido_cliente"));
                    cliente.setCi_cliente(rs.getInt("ci_cliente"));
                    cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                    cliente.setDireccion_cliente(rs.getString("direccion_cliente"));
                    cliente.setTelefono_cliente(rs.getString("telefono_cliente"));
                    cliente.setNacionalidad(nacionalidad);
                    cliente.setCiudad(ciudad);
                    cliente.setEstadocivil(estadocivil);
                } else {
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                    Nacionalidades nacionalidad = new Nacionalidades();
                    nacionalidad.setId_nacionalidad(0);
                    nacionalidad.setNombre_nacionalidad("");
                    EstadosCiviles estadocivil = new EstadosCiviles();
                    estadocivil.setId_estadocivil(0);
                    estadocivil.setNombre_estadocivil("");
                    cliente.setNombre_cliente("");
                    cliente.setApellido_cliente("");
                    cliente.setCi_cliente(0);
                    cliente.setRuc_cliente("");
                    cliente.setDireccion_cliente("");
                    cliente.setTelefono_cliente("");
                    cliente.setNacionalidad(nacionalidad);
                    cliente.setCiudad(ciudad);
                    cliente.setEstadocivil(estadocivil);
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return cliente;
    }

    public static Clientes buscarCedula(Clientes cliente) {
        if (Conexion.conectar()) {
            String sql = "select * from clientes where ci_cliente='" + cliente.getCi_cliente() + "'";

            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    cliente.setCi_cliente(0);
                } else {
                    cliente.setCi_cliente(rs.getInt("ci_cliente"));
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return cliente;
    }

    public static Clientes buscarRuc(Clientes cliente) {
        if (Conexion.conectar()) {
            String sql = "select * from clientes where ruc_cliente ='" + cliente.getRuc_cliente() + "'";

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    cliente.setId_cliente(0);

                } else {

                    cliente.setId_cliente(1);

                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return cliente;
    }

    public static Clientes buscarTelefono(Clientes cliente) {
        if (Conexion.conectar()) {
            String sql = "select * from clientes where telefono_cliente ='" + cliente.getTelefono_cliente() + "'";
            System.out.println("sql " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    cliente.setId_cliente(0);

                } else {

                    cliente.setId_cliente(1);

                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return cliente;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from clientes where upper (nombre_cliente) like '%"
                        + nombre.toUpperCase() + "%'" + " order by id_cliente offset " + offset
                        + "limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td>" + rs.getString("apellido_cliente") + "</td>"
                                + "<td>" + rs.getString("ci_cliente") + "</td>"
                                + "<td>" + rs.getString("ruc_cliente") + "</td>"
                                + "<td>" + rs.getString("telefono_cliente") + "</td>"
                                + "<td>" + rs.getString("direccion_cliente") + "</td>"
                                + "<td>" + rs.getString("id_ciudad") + "</td>"
                                + "<td>" + rs.getString("id_nacionalidad") + "</td>"
                                + "<td>" + rs.getString("id_estadocivil") + "</td>"
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

    public static boolean modificarCliente(Clientes cliente) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update clientes set nombre_cliente='" + cliente.getNombre_cliente() + "'"
                    + ", apellido_cliente='" + cliente.getApellido_cliente() + "'"
                    + ", ci_cliente='" + cliente.getCi_cliente() + "'"
                    + ", ruc_cliente='" + cliente.getRuc_cliente() + "'"
                    + ", telefono_cliente='" + cliente.getTelefono_cliente() + "'"
                    + ", id_nacionalidad='" + cliente.getNacionalidad().getId_nacionalidad() + "'"
                    + ", id_ciudad='" + cliente.getCiudad().getId_ciudad() + "'"
                    + ", id_estadocivil='" + cliente.getEstadocivil().getId_estadocivil() + "'"
                    + "where id_cliente=" + cliente.getId_cliente();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminarCliente(Clientes cliente) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from clientes where id_cliente=" + cliente.getId_cliente();
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
