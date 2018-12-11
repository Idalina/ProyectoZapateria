/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Utiles.Conexion;
import Utiles.Utiles;
import modelos.Timbrados;
import java.sql.Date;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TimbradosControlador {
    public static boolean agregar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into timbrados (numero_timbrado, fecha_inicio_timbrado, fecha_vencimiento_timbrado, fecha_actual_timbrado)" 
                    + "values ('" + timbrado.getNumero_timbrado() + "','"
                    + timbrado.getFecha_inicio_timbrado() + "','"
                    + timbrado.getFecha_vencimiento_timbrado() + "','"
                    + timbrado.getFecha_actual_timbrado() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update timbrados set numero_timbrado='" + timbrado.getNumero_timbrado() + "', "
                    + "fecha_inicio_timbrado='" + timbrado.getFecha_inicio_timbrado() + "', "
                    + "fecha_vencimiento_timbrado='" + timbrado.getFecha_vencimiento_timbrado() + "', "
                    + "fecha_actual_timbrado='" + timbrado.getFecha_actual_timbrado() + "'"
                    + " where id_timbrado=" + timbrado.getId_timbrado();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Timbrados timbrado){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from timbrados where id_timbrado=" + timbrado.getId_timbrado();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TimbradosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Timbrados buscarId(Timbrados timbrado) {
        if (Conexion.conectar()){
            String sql = "select * from timbrados where id_timbrado ='"+timbrado.getId_timbrado()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    timbrado.setId_timbrado(rs.getInt("id_timbrado"));
                    timbrado.setNumero_timbrado(rs.getString("numero_timbrado"));
                    timbrado.setFecha_inicio_timbrado(rs.getDate("fecha_inicio_timbrado"));
                    timbrado.setFecha_vencimiento_timbrado(rs.getDate("fecha_vencimiento_timbrado"));
                    timbrado.setFecha_actual_timbrado(rs.getDate("fecha_actual_timbrado"));
                } else {
                    timbrado.setId_timbrado(0);
                    timbrado.setNumero_timbrado("");
                    timbrado.setFecha_inicio_timbrado(null);
                    timbrado.setFecha_vencimiento_timbrado(null);
                    timbrado.setFecha_actual_timbrado(null);
                }   
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return timbrado;
    }
    
    public static String buscarNumero(String numero, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(numero);
                String sql = "select * from timbrados where upper(numero_timbrado) like '%" +
                        numero.toUpperCase() + "%'"
                        + "order by id_timbrado offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_timbrado") + "</td>"
                                + "<td>" + rs.getString("numero_timbrado") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static Timbrados buscarTimbrado(Timbrados timbrado) {
        if (Conexion.conectar()){
            String sql = "select * from timbrados where numero_timbrado ='"+timbrado.getNumero_timbrado()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    timbrado.setId_timbrado(0);
                    
                } else {
                    timbrado.setId_timbrado(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return timbrado;
    }
}
