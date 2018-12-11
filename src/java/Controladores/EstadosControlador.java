/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Estados;
import Utiles.Conexion;
import Utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class EstadosControlador {
    public static boolean agregar(Estados estado){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="insert into estados(nombre_estado)"
                    +"values('"+estado.getNombre_estado()+"')";
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
     public static Estados buscarId(Estados estado){
     if (Conexion.conectar()){
         String sql="Select *from estados where id_estado='" + estado.getId_estado() + "'";
         System.out.println("sql"+sql);
         try{
             ResultSet rs = Conexion.getSt().executeQuery(sql);
             if (rs.next()){
                 estado.setNombre_estado(rs.getString("nombre_estado"));
                 
             }else{
                 estado.setId_estado(0);
                 estado.setNombre_estado("");
             }
         }catch(SQLException ex){
             System.err.println("Error: " + ex);
         }
     }
     return estado;
 }
    
   public static String buscarNombre(String nombre,int pagina){
       int offset=(pagina - 1)*Utiles.REGISTROS_PAGINA;
       String valor="";
       if(Conexion.conectar()){
           try{
               String sql="select * from estados where upper (nombre_estado) like '%"+
                      nombre.toUpperCase() + "%'"
                       + " order by id_estado offset "+ offset + "limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->"+sql);
               try(PreparedStatement ps =Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs=ps.executeQuery();
                   String tabla="";
                   while (rs.next()){
                       tabla+="<tr>"
                        +"<td>"+rs.getString("id_estado")+"</td>"
                        +"<td>"+rs.getString("nombre_estado")+"</td>"
                        +"</tr>";       
                   }
                   if(tabla.equals("")){
                       tabla="<tr><td colspan=2>No existen registros...</td></tr>";
                   }
                   ps.close();
                   valor=tabla;
               }catch (SQLException ex){
                   System.err.println("Error:"+ex);
               }
               Conexion.cerrar();
           }catch (Exception ex){
               System.err.println("Error:"+ex);
           }
       }
       Conexion.cerrar();
       return valor;
   } 
   public static boolean modificar(Estados estado){
       boolean valor=false;
       if (Conexion.conectar()){
           String sql="update estados set nombre_estado= '" + estado.getNombre_estado() + "'"
                   +"where id_estado=" + estado.getId_estado();
           try{
               Conexion.getSt().executeUpdate(sql);
               valor=true;
           }catch (SQLException ex){
               System.out.println("Error: " + ex);
           }
       }
       return valor;
   }
   
   public static boolean eliminar (Estados estado){
       boolean valor= false;
       if (Conexion .conectar()){
           String sql= "delete from estados where id_estado="+ estado.getId_estado();
            System.out.println("--->"+sql);
           try{
               Conexion.getSt().executeUpdate(sql);
               valor=true;
           }catch (SQLException ex){
               System.err.println("Error:" + ex);
           }
       }
       return valor;
   }
}
