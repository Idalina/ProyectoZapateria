/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Colores;
import Utiles.Conexion;
import Utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class ColoresControlador {
    public static boolean agregar(Colores color){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="insert into colores(nombre_color)"
                    +"values('"+color.getNombre_color()+"')";
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
     public static Colores buscarId(Colores color){
     if (Conexion.conectar()){
         String sql="Select *from colores where id_color='" + color.getId_color() + "'";
         System.out.println("sql"+sql);
         try{
             ResultSet rs = Conexion.getSt().executeQuery(sql);
             if (rs.next()){
                 color.setNombre_color(rs.getString("nombre_color"));
                 
             }else{
                 color.setId_color(0);
                 color.setNombre_color("");
             }
         }catch(SQLException ex){
             System.err.println("Error: " + ex);
         }
     }
     return color;
 }
    
   public static String buscarNombre(String nombre,int pagina){
       int offset=(pagina - 1)*Utiles.REGISTROS_PAGINA;
       String valor="";
       if(Conexion.conectar()){
           try{
               String sql="select * from colores where upper (nombre_color) like '%"+
                      nombre.toUpperCase() + "%'"
                       + " order by id_color offset "+ offset + "limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->"+sql);
               try(PreparedStatement ps =Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs=ps.executeQuery();
                   String tabla="";
                   while (rs.next()){
                       tabla+="<tr>"
                        +"<td>"+rs.getString("id_color")+"</td>"
                        +"<td>"+rs.getString("nombre_color")+"</td>"
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
   public static boolean modificarColor(Colores color){
       boolean valor=false;
       if (Conexion.conectar()){
           String sql="update colores set nombre_color= '" + color.getNombre_color() + "'"
                   +"where id_color=" + color.getId_color();
           try{
               Conexion.getSt().executeUpdate(sql);
               valor=true;
           }catch (SQLException ex){
               System.out.println("Error: " + ex);
           }
       }
       return valor;
   }
   
   public static boolean eliminarColor (Colores color){
       boolean valor= false;
       if (Conexion .conectar()){
           String sql= "delete from colores where id_color="+ color.getId_color();
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
