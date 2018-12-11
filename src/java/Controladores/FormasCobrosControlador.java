/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.FormasCobros;
import Utiles.Conexion;
import Utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class FormasCobrosControlador {
    public static boolean agregar(FormasCobros formacobro){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="insert into formas_cobros(nombre_formacobro)"
                    +"values('"+formacobro.getNombre_formacobro()+"')";
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
     public static FormasCobros buscarId(FormasCobros formacobro){
     if (Conexion.conectar()){
         String sql="Select *from formas_cobros where id_formacobro='" + formacobro.getId_formacobro() + "'";
         System.out.println("sql"+sql);
         try{
             ResultSet rs = Conexion.getSt().executeQuery(sql);
             if (rs.next()){
                 formacobro.setNombre_formacobro(rs.getString("nombre_formacobro"));
                 
             }else{
                 formacobro.setId_formacobro(0);
                 formacobro.setNombre_formacobro("");
             }
         }catch(SQLException ex){
             System.err.println("Error: " + ex);
         }
     }
     return formacobro;
 }
    
   public static String buscarNombre(String nombre,int pagina){
       int offset=(pagina - 1)*Utiles.REGISTROS_PAGINA;
       String valor="";
       if(Conexion.conectar()){
           try{
               String sql="select * from formas_cobros where upper (nombre_formacobro) like '%"+
                      nombre.toUpperCase() + "%'"
                       + " order by id_formacobro offset "+ offset + "limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->"+sql);
               try(PreparedStatement ps =Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs=ps.executeQuery();
                   String tabla="";
                   while (rs.next()){
                       tabla+="<tr>"
                        +"<td>"+rs.getString("id_formacobro")+"</td>"
                        +"<td>"+rs.getString("nombre_formacobro")+"</td>"
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
   public static boolean modificar(FormasCobros formacobro){
       boolean valor=false;
       if (Conexion.conectar()){
           String sql="update formas_cobros set nombre_formacobro= '" + formacobro.getNombre_formacobro() + "'"
                   +"where id_formacobro=" + formacobro.getId_formacobro();
           try{
               Conexion.getSt().executeUpdate(sql);
               valor=true;
           }catch (SQLException ex){
               System.out.println("Error: " + ex);
           }
       }
       return valor;
   }
   
   public static boolean eliminar (FormasCobros formacobro){
       boolean valor= false;
       if (Conexion .conectar()){
           String sql= "delete from formas_cobros where id_formacobro="+ formacobro.getId_formacobro();
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
